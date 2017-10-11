package za.ac.tut.usedbook.usedbook.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;
import za.ac.tut.usedbook.usedbook.TestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by gracem on 2017/09/29.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class LoginServiceTest extends TestBase{

    private LoginService service;

    @Autowired
    private StudentRepository studentRepoMock;


    @Before
    public void setUp() {
        this.studentRepoMock = Mockito.mock(StudentRepository.class);
        this.service = new LoginService(studentRepoMock);
    }

    @Ignore
    @Test
    public void checkStudentExistUsingStudentId() throws Exception {

        Student student = getExistingStudent();
        when(studentRepoMock.exists(student.getStudentId())).thenReturn(true);

        Boolean result = service.validateStudentExist(student.getStudentId());
        assertThat(result).isTrue();


    }

    @Test
    public void shouldThrowExceptionWhenNonExistStudentByStudentId() throws Exception {

//        Student student = getNonExistingStudent();
//        when(studentRepoMock.exists(student.getStudentId())).thenReturn(false);
//
//        try{
//            service.validateStudentExist(student.getStudentId());
//            fail("Did not throw StudentDoNotExistException");
//        } catch (LoginService.StudentDoNotExistException e){
//            assertThat(e.getMessage()).isEqualTo("Student does not exist!");
//        }

    }

    @Test
    public void enabledShouldBeTrueWhenStudentHasNotLoggedIn() throws Exception {
        Student student = getExistingStudent();
        Integer studentId = student.getStudentId();
        when(studentRepoMock.findByStudentId(studentId)).thenReturn(student);

//        Boolean result = service.isStudentAccountEnabled(student);
//        assertThat(result).isTrue();
    }

    @Test
    public void sessionKeyShouldBeNullWhenStudentHasNotLoggedInReturnTrue() throws Exception {
        Student student = getExistingStudent();

        Integer studentId = student.getStudentId();
        when(studentRepoMock.findByStudentId(studentId)).thenReturn(student);

        Boolean result = service.findSessionKey(student);
        assertThat(result).isTrue();
    }


    /**
     * when the student is already logged in somewhere else
     * @throws Exception
     */
    @Test
    public void sessionKeyShouldExistWhenStudentHadLoggedInReturnFalse() throws Exception {
        Student student = getExistingStudent();
        student.setSessionKey("thwy688h");

        Integer studentId = student.getStudentId();
        when(studentRepoMock.findByStudentId(studentId)).thenReturn(student);

        Boolean result = service.findSessionKey(student);
        assertThat(result).isFalse();
    }


    @Test
    public void whenStudentIsSuspendShouldThrowException() throws Exception {
//       Student student= getExistingStudent();
//        student.setLoggedIn(false);
////        Integer studentId = student.getStudent().getStudentId();
////        when(studentRepoMock.findByStudent(studentId)).thenReturn(student);
//
//
//        try{
//            service.isStudentAccountEnabled(student);
//            fail("Did not throw StudentDoNotExistException");
//        } catch (LoginService.StudentSuspendedExistException e){
//            assertThat(e.getMessage()).isEqualTo("Student can't login, student has been suspended");
//        }
    }
//    sessionKeyShouldBeNullWhenStudentLogin
    @Ignore
    @Test
    public void shouldReturnTruePasswordIsCorrect() throws Exception {

        Student student = getExistingStudent();
        student.setPassword(getHashPasswordToHexFormat(student.getPassword()));
        String correctPassword = "pass01";

        when(studentRepoMock.findByStudentId(student.getStudentId())).thenReturn(student);

        Boolean result = service.isPasswordCorrect(student.getPassword(), correctPassword);
        assertThat(result).isTrue();

    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsIncorrect() throws Exception {

        Student student = getExistingStudent();
        student.setPassword(getHashPasswordToHexFormat(student.getPassword()));
        String incorrectPassword = "pass11";

        when(studentRepoMock.findByStudentId(student.getStudentId())).thenReturn(student);

        try{
            service.isPasswordCorrect(student.getPassword(), incorrectPassword);
            fail("Did not throw PasswordDoNotMatchException");
        } catch (LoginService.PasswordDoNotMatchException e){
            assertThat(e.getMessage()).isEqualTo("Password is incorrect!");
        }

    }

    @Test
    public void shouldFindStudentByStudentname() throws Exception {
        Student existingStudent = getExistingStudent();

        when(studentRepoMock.findByStudentId(existingStudent.getStudentId())).thenReturn(existingStudent);

        Student result = service.findStudentByUsername(existingStudent.getStudentId());
        assertThat(result.getName()).isEqualTo(existingStudent.getName());
        assertThat(result.getSurname()).isEqualTo(existingStudent.getSurname());
        assertThat(result.getFunds()).isEqualTo(existingStudent.getFunds());

    }

    /**
     * Test Whole Login
     * @throws Exception
     */
    @Ignore
    @Test
    public void validateStudent_correctValues() throws Exception {
        Student validStudent = getExistingStudent();
        Integer studentId = validStudent.getStudentId();
        Student expectedStudent = getExistingStudent();

        when(studentRepoMock.exists(studentId)).thenReturn(true);
//        when(studentRepoMock.findByStudent(studentId)).thenReturn(student);
//        when(studentRepoMock.findByStudent(studentId)).thenReturn(student);
//        when(studentRepoMock.findByStudent(student.getStudentId())).thenReturn(student);
//        when(studentRepoMock.findByStudentId(existingStudent.getStudentId())).thenReturn(existingStudent);

//        Student student = service.validateStudentExist(2100L,"pass01");
        verify(studentRepoMock, times(1));
//        assertThat(student).isEqualTo(expectedStudent);
    }


//    private Student getValidCredentialAndInvalidStudent(){
//        Student student = getNonExistingStudent();
//        return new Student(1L,student, "pass01",false, "USER", null);
//    }
//
//    private Student getValidCredentialAndInvalidPassword(){
//        Student student = getExistingStudent();
//        return new Student(1L,student, "pass00",true, "USER", null);
//    }
//
//    private Student getInvalidCredentialAndInvalidStudent(){
//        Student student = getNonExistingStudent();
//        return new Student(1L,student, "pas00",true, "USER", null);
//    }


    private Student getNonExistingStudent(){
        return  new Student(19003, "Thabo", "Mmala", 400.80);
    }



}
