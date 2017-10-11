package za.ac.tut.usedbook.usedbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;
import za.ac.tut.usedbook.usedbook.validation.Helper;

/**
 * Created by gracem on 2017/09/28.
 */
@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student update(Student student) throws NonExistedStudentException{
        if(studentRepository.exists(student.getStudentId()) == false) {
            throw new NonExistedStudentException();
        }
        return studentRepository.save(student);
    }

    public Student findbyStudentId(Integer studentId) {
        if(studentRepository.exists(studentId) == false) {
            throw new NonExistedStudentException();
        }
        return studentRepository.findByStudentId(studentId);
    }

    public class NonExistedStudentException extends RuntimeException{
        private final String message = "Student does not exist.";
        @Override
        public String getMessage() {
            return message;
        }
    }

    /**
     * From UserService
     */

    public String base64UUID(String uuid, Integer username) {
        //TODO: Verify the User exist
        if (studentRepository.exists(username)) {
            Student student = studentRepository.findByStudentId(username);

            //TODO: set and save new UUID
            setAndSaveUUID(uuid, student);
        }
        return Helper.encodeStringToBase64(uuid);

    }

    private void setAndSaveUUID(String uuid, Student student) {
        student.setSessionKey(uuid);
        studentRepository.save(student);
    }

    public Student findBySessionKey(String encodedKey) {
        String key = Helper.decodeBase64ToString(encodedKey);
        return studentRepository.findBySessionKey(key);
    }

    public class UnauthorisedUserException extends RuntimeException{
        private final String message = "User is not authorised";
        @Override
        public String getMessage() {
            return message;
        }
    }
}
