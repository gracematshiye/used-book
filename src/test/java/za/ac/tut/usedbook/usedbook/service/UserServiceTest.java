//package za.ac.tut.usedbook.usedbook.service;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import za.ac.tut.usedbook.usedbook.TestBase;
//import za.ac.tut.usedbook.usedbook.entiy.User;
//import za.ac.tut.usedbook.usedbook.repository.UserRepository;
//import za.ac.tut.usedbook.usedbook.validation.Helper;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
///**
// * Created by gracem on 2017/10/01.
// */
//public class UserServiceTest extends TestBase{
//
//    private UserService service;
//
//    @Autowired
//    private UserRepository userRepoMock;
//
//    @Before
//    public void setUp() {
//        this.userRepoMock = Mockito.mock(UserRepository.class);
//        this.service = new UserService(userRepoMock);
//    }
//
//    @Test
//    public void shouldEncodeUUID() throws Exception {
////        Student student = getExistingStudent();
//        User user = getValidCredentialAndExistingStudent();
//        String newUUID = Helper.generateUUID();
//        String encodedID = getEncodeToBAse64(newUUID);
//
////        when(userRepoMock.findByStudent(student.getStudentId())).thenReturn(getValidCredentialAndExistingStudent());
//        String result = service.base64UUID(newUUID, user.getStudent().getStudentId());
////        verify(userRepoMock, times(1)).save(user);
//        assertThat(result).isEqualTo(encodedID);
//    }
//
//    @Test
//    public void findUserBySessionKey() throws Exception {
//        String key = Helper.generateUUID();
//        String encodedKey = Helper.encodeStringToBase64(key);
//
//        User existingUser = getValidCredentialAndExistingStudent();
//        existingUser.setSessionKey(key);
//        when(userRepoMock.findBySessionKey(key)).thenReturn(existingUser);
//
//        User user = service.findBySessionKey(encodedKey);
//
//        assertThat(user.getSessionKey()).isEqualTo(key);
//
//    }
//
////    @Test
////    public void shouldThrowExceptionWhenSessionKeyDontMatch() throws Exception {
////        String key = Helper.generateUUID() + "false";
////        String encodedKey = Helper.encodeStringToBase64(key);
////
////        User user = getValidCredentialAndExistingStudent();
////        when(userRepoMock.findBySessionKey(key)).thenReturn(user);
////
////
////        try{
////            service.findBySessionKey(encodedKey);
////            fail("Did not throw UnauthorisedUserException");
////        } catch (LoginService.PasswordDoNotMatchException e){
////            assertThat(e.getMessage()).isEqualTo("User is not authorised");
////        }
////
////    }
//
//}
