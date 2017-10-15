package za.ac.tut.usedbook.usedbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.tut.usedbook.usedbook.service.BookService;
import za.ac.tut.usedbook.usedbook.service.LoginService;
import za.ac.tut.usedbook.usedbook.service.PaymentService;
import za.ac.tut.usedbook.usedbook.service.StudentService;

/**
 * Created by gracem on 2017/10/12.
 */
@RestController
@RequestMapping(path = "/api")
public class PaymentController {

    private LoginService loginService;

    private BookService bookService;

    private StudentService studentService;
    private PaymentService paymentService;

    @Autowired
    public PaymentController(LoginService loginService, BookService bookService, StudentService studentService, PaymentService paymentService) {
        this.loginService = loginService;
        this.bookService = bookService;
        this.studentService = studentService;
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/pay/{book_id}", method = RequestMethod.GET)
    public ResponseEntity makePayment (@PathVariable("book_id") Integer bookId/*@RequestHeader HttpHeaders headers*/){
        try {
//            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
//            loginService.isStudentLoggedOn(uuid);
//            Student student = studentService.findBySessionKey(uuid);
//            Book book = bookService.findById(bookId);
//            paymentService.processPayment();




            return new ResponseEntity("Spring REST Dinesh on Java!!!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
