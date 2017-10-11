package za.ac.tut.usedbook.usedbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;
import za.ac.tut.usedbook.usedbook.validation.Helper;

import java.security.NoSuchAlgorithmException;

/**
 * Created by gracem on 2017/09/29.
 */
@Service
public class LoginService {

    private StudentRepository studentRepository;

    @Autowired
    LoginService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     *
     * Logging in is done
     */
    public Student validateUser(int username, String password) throws NoSuchAlgorithmException {

        //TODO: check user exit
        validateStudentExist(username);

        //TODO: check if the user is logged in
        Student student = findStudentByUsername(username);

        //TODO: check if user has logged in sessionKey currently
        if (student.getLoggedIn() == false && !findSessionKey(student)){

            //TODO: false then DELETE/KILL/REMOVE the sessionKey
            student.setSessionKey(null);
            studentRepository.save(student);
        }

        //TODO: check password is correct
        isPasswordCorrect(student.getPassword(),password);

        //TODO: set a sesssion Key/ token
        student.setSessionKey(Helper.generateUUID());
        student.setLoggedIn(true);
        studentRepository.save(student);

        //TODO: getStudent by username
        return studentRepository.findByStudentId(username);
    }

    /**
        DONE : Check Student logged on already
     */
    public Boolean isStudentLoggedOn(String uuid) {

        //TODO: check if the user is not suspended
        Student student = findStudentBySessionKey(uuid);
        if(student == null || !student.getLoggedIn()) {
            throw new LoginException();
        }
        return true;
    }

    /**
        DONE: Check Student logging out
     */
    public Boolean studentLogOut(String uuid) {
        //TODO: check if the uuid exist and the student logged in
        if(!isStudentLoggedOn(uuid)){
            throw new LoginException();
        }

        Student student = findStudentBySessionKey(uuid);
        student.setSessionKey(null);
        student.setLoggedIn(false);

        studentRepository.save(student);
        return true;
    }

    public Student findStudentBySessionKey(String uuid) {
        return studentRepository.findBySessionKey(uuid);
    }

    public Boolean validateStudentExist(Integer studentId) throws UserDoNotExistException{
        if(!studentRepository.exists(studentId)) {
            throw new UserDoNotExistException();
        }
        return true;
    }

    public Boolean isPasswordCorrect(String fromDB, String fromWeb) throws NoSuchAlgorithmException {
        String hashPassword = Helper.hashPassword(fromWeb);

        if(!fromDB.equals(hashPassword)) {
            throw new PasswordDoNotMatchException();
        }
        return true;
    }

    public Student findStudentByUsername(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Boolean findSessionKey(Student student) {
        return (student.getSessionKey() == null);
    }

    public class UserDoNotExistException extends RuntimeException{
        private final String message = "User does not exist!";
        @Override
        public String getMessage() {
            return message;
        }
    }

    public class LoginException extends RuntimeException{
        private final String message = "User have not logged-in. Please login!!";
        @Override
        public String getMessage() {
            return message;
        }
    }

    public class PasswordDoNotMatchException extends RuntimeException{
        private final String message = "Password is incorrect!";
        @Override
        public String getMessage() {
            return message;
        }
    }
}
