package za.ac.tut.usedbook.usedbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.tut.usedbook.usedbook.service.BookService;
import za.ac.tut.usedbook.usedbook.service.LoginService;

/**
 * Created by gracem on 2017/10/12.
 */
@RestController
@RequestMapping(path = "/api")
public class PaymentController {

    private LoginService loginService;

    private BookService bookService;

    @Autowired
    public PaymentController(LoginService loginService, BookService bookService) {
        this.loginService = loginService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/pay/{book_id}", method = RequestMethod.GET)
    public ResponseEntity makePayment (/*@RequestHeader HttpHeaders headers*/){
        try {
//            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
//            loginService.isStudentLoggedOn(uuid);

            return new ResponseEntity("Spring REST Dinesh on Java!!!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
