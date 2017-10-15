package za.ac.tut.usedbook.usedbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.service.LoginService;
import za.ac.tut.usedbook.usedbook.validation.Helper;
import za.ac.tut.usedbook.usedbook.viewModel.CredentialModel;
import za.ac.tut.usedbook.usedbook.viewModel.UserViewModel;

import java.security.NoSuchAlgorithmException;

/**
 * Created by gracem on 2017/09/30.
 */
@RestController
@RequestMapping(path = "/api")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity login(@RequestBody CredentialModel credentialModel) throws NoSuchAlgorithmException {

        try {
            int username = credentialModel.getUsername();
            String password = credentialModel.getPassword();

            Student student = loginService.validateUser(username, password);

            UserViewModel viewModel = new UserViewModel(student);

            return new ResponseEntity( viewModel, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity logout(@RequestHeader HttpHeaders httpHeaders) throws NoSuchAlgorithmException {
        try {
            String uuid = getHeaderString(httpHeaders);
            loginService.studentLogOut(uuid);

            return new ResponseEntity("log-on", HttpStatus.OK);

        }
        catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    private String getHeaderString(HttpHeaders headers) {
        return Helper.decodeBase64ToString((headers.get("Authorization").get(0)));
    }
}
