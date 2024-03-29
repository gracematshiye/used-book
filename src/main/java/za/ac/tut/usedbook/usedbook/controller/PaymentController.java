package za.ac.tut.usedbook.usedbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Payment;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.sendingmail.MailSender;
import za.ac.tut.usedbook.usedbook.service.BookService;
import za.ac.tut.usedbook.usedbook.service.LoginService;
import za.ac.tut.usedbook.usedbook.service.PaymentService;
import za.ac.tut.usedbook.usedbook.service.StudentService;
import za.ac.tut.usedbook.usedbook.validation.Helper;
import za.ac.tut.usedbook.usedbook.viewModel.PaymentViewModel;
import za.ac.tut.usedbook.usedbook.viewModel.UserViewModel;

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

//    @RequestMapping(value = "/pay/{book_id}",
//                    method = RequestMethod.GET,
//                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity makePayment (@PathVariable("book_id") Integer bookId, @RequestHeader HttpHeaders headers) throws Exception {
//        try {
//            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
//            loginService.isStudentLoggedOn(uuid);
//
//            Book book = bookService.findById(bookId);
//            Student buyer = loginService.findStudentBySessionKey(uuid);
//            Student seller = loginService.findStudentByUsername(book.getSellerId());
//
//            Payment payment = paymentService.processPayment(book, buyer);
//
//
//            MailSender.sendEmail(payment, buyer, seller, book);
//            PaymentViewModel paymentViewModel = new PaymentViewModel(new UserViewModel(buyer),payment);
//            return new ResponseEntity(paymentViewModel, HttpStatus.OK);
////            return new ResponseEntity(payment, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
//        }
//    }



    @RequestMapping(value = "/pay/book",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity makePayment (@RequestHeader HttpHeaders headers) throws Exception {
        try {
            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
            Integer bookId = Integer.valueOf(headers.get("book-id").get(0));
            loginService.isStudentLoggedOn(uuid);

            Book book = bookService.findById(bookId);
            Student buyer = loginService.findStudentBySessionKey(uuid);
            Student seller = loginService.findStudentByUsername(book.getSellerId());

            Payment payment = paymentService.processPayment(book, buyer);


            MailSender.sendEmail(payment, buyer, seller, book);
            PaymentViewModel paymentViewModel = new PaymentViewModel(new UserViewModel(buyer),payment);
            return new ResponseEntity(paymentViewModel, HttpStatus.OK);
//            return new ResponseEntity(payment, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


}
