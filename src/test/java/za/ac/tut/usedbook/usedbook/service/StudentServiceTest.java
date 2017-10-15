package za.ac.tut.usedbook.usedbook.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.tut.usedbook.usedbook.TestBase;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;
import za.ac.tut.usedbook.usedbook.validation.Helper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * Created by gracem on 2017/09/28.
 */
//@Ignore
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@Ignore
public class StudentServiceTest extends TestBase{

    private StudentService service;

    @Autowired
    private StudentRepository repoMock;


    @Before
    public void setUp() {
        this.repoMock = Mockito.mock(StudentRepository.class);
        this.service = new StudentService(repoMock);
    }

    @Test
    public void shouldUpdateFundsAfterSuccessfulTransactions() throws Exception {

        Student student = getAStudent();
        Double tranc = student.getFunds()- 40.1;
        student.setFunds(tranc);
        when(repoMock.exists(student.getStudentId())).thenReturn(true);
        when(repoMock.save(student)).thenReturn(student);
        Student result = service.update(student);

        assertThat(result.getFunds()).isEqualTo(tranc);

    }

    @Ignore
    @Test
    public void shouldFindStudentByStudentId() throws Exception {

        Student student = getAStudent();

        when(repoMock.exists(student.getStudentId())).thenReturn(true);
        when(repoMock.findByStudentId(student.getStudentId())).thenReturn(student);
//        Student result = service.findbyStudentId(student.getStudentId());

//        assertThat(result.getName()).isEqualTo(student.getName());
//        assertThat(result.getSurname()).isEqualTo(student.getSurname());
//        assertThat(result.getFunds()).isEqualTo(student.getFunds());
    }

    @Ignore
    @Test
    public void shouldThrowNonExistedStudentException() throws Exception {

        Student nonExistingStudent = getNonExistedStudent();
        when(repoMock.exists(nonExistingStudent.getStudentId())).thenReturn(false);

        //FOR: Update method
        try{
            service.update(nonExistingStudent);
            fail("Did not throw NonExistedStudentException");
        } catch (StudentService.NonExistedStudentException e){
            assertThat(e.getMessage()).isEqualTo("Student does not exist.");
        }

        //FOR: FindByStudentId method
        try{
//            service.findbyStudentId(nonExistingStudent.getStudentId());
            fail("Did not throw NonExistedStudentException");
        } catch (StudentService.NonExistedStudentException e){
            assertThat(e.getMessage()).isEqualTo("Student does not exist.");
        }


    }

    private Student getAStudent() {
        return new Student(210345,"name","surname",100.90);
    }

    private Student getNonExistedStudent() {
        return new Student(210005,"name","surname",10.90);
    }

    private List<Book> getListOfBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(1,"Java", "Thomas","isbn-1","7th","lang","TPG",7.9, "SOLD","additionalIno", getExistingStudent().getStudentId()));
        books.add(new Book(2,"C#", "Thomas","isbn-2","7th","lang","tpg",7.9, "ON-SALE","additionalIno", getExistingStudent().getStudentId()));
        return books;
    }

    /**
     * UserServiceTest
     */


    @Ignore
    @Test
    public void shouldEncodeUUID() throws Exception {
//        Student student = getExistingStudent();
        Student student = getExistingStudent();
        String newUUID = Helper.generateUUID();
        String encodedID = getEncodeToBAse64(newUUID);

//        when(userRepoMock.findByStudent(student.getStudentId())).thenReturn(getValidCredentialAndExistingStudent());
//        String result = service.base64UUID(newUUID, student.getStudentId());
//        verify(userRepoMock, times(1)).save(user);
//        assertThat(result).isEqualTo(encodedID);
    }

    @Test
    public void findUserBySessionKey() throws Exception {
        String key = Helper.generateUUID();
        String encodedKey = Helper.encodeStringToBase64(key);

        Student existingStudent = getExistingStudent();
        existingStudent.setSessionKey(key);
        when(repoMock.findBySessionKey(key)).thenReturn(existingStudent);

        Student student= service.findBySessionKey(encodedKey);

        assertThat(student.getSessionKey()).isEqualTo(key);

    }

//    @Test
//    public void shouldThrowExceptionWhenSessionKeyDontMatch() throws Exception {
//        String key = Helper.generateUUID() + "false";
//        String encodedKey = Helper.encodeStringToBase64(key);
//
//        User user = getValidCredentialAndExistingStudent();
//        when(userRepoMock.findBySessionKey(key)).thenReturn(user);
//
//
//        try{
//            service.findBySessionKey(encodedKey);
//            fail("Did not throw UnauthorisedUserException");
//        } catch (LoginService.PasswordDoNotMatchException e){
//            assertThat(e.getMessage()).isEqualTo("User is not authorised");
//        }
//
//    }
}
