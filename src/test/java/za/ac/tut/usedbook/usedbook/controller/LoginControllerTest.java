package za.ac.tut.usedbook.usedbook.controller;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.tut.usedbook.usedbook.TestBase;
import za.ac.tut.usedbook.usedbook.entiy.Book;

/**
 * Created by gracem on 2017/09/30.
 */
public class LoginControllerTest extends TestBase {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

        new Book();
    }

//    @Test
//    public void testGetSignupForm() throws Exception {
//
//        mockMvc.perform(
//                post("/api/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", getEncodeToBAse64("21025:" + "pass01")))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }


}
