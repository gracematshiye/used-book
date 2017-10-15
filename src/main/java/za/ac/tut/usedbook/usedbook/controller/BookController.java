package za.ac.tut.usedbook.usedbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.service.BookService;
import za.ac.tut.usedbook.usedbook.service.LoginService;
import za.ac.tut.usedbook.usedbook.validation.Helper;
import za.ac.tut.usedbook.usedbook.viewModel.ListBookViewModel;
import za.ac.tut.usedbook.usedbook.viewModel.NewBookViewModel;
import za.ac.tut.usedbook.usedbook.viewModel.UserViewModel;

import java.util.List;

/**
 * Created by gracem on 2017/09/24.
 */
@RestController
@RequestMapping(path = "/api")
@EnableAutoConfiguration
public class BookController {

    private LoginService loginService;

    private BookService bookService;

    @Autowired
    public BookController(LoginService loginService, BookService bookService) {
        this.loginService = loginService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity home (@RequestHeader HttpHeaders headers){
        try {
            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
            loginService.isStudentLoggedOn(uuid);

            return new ResponseEntity("Spring REST Dinesh on Java!!!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity createBook(@RequestBody Book book, @RequestHeader HttpHeaders headers) {

        try {
            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
            loginService.isStudentLoggedOn(uuid);
            Student student = loginService.findStudentBySessionKey(uuid);

            Book savedBook = bookService.save(book);

            NewBookViewModel viewModel = new NewBookViewModel(student,savedBook);

            return new ResponseEntity(viewModel, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity listAllBooks (@RequestHeader HttpHeaders headers) {
        try {
            String uuid = Helper.decodeBase64ToString(headers.get("Authorization").get(0));
            loginService.isStudentLoggedOn(uuid);
            Student student = loginService.findStudentBySessionKey(uuid);

            List<Book> books = bookService.findAll();

            UserViewModel userViewModel = new UserViewModel(student);
            if (books.isEmpty()) {
                return new ResponseEntity(userViewModel, HttpStatus.NO_CONTENT);
            }
            ListBookViewModel viewModel = new ListBookViewModel(userViewModel, books);
            return new ResponseEntity(viewModel, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);

        }
    }

//    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
//    public ResponseEntity getBookById(@PathVariable("id") Integer id) {
//        //TODO: check student logged in
////        logger.info("Fetching User with id {}", id);
//        //TODO:call the book service
//        Book book = bookService.findById(id);
//        if (book == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(book, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/book/isbn", method = RequestMethod.GET)
//    public ResponseEntity getBookByISBN(@RequestHeader HttpHeaders headers) {
//        //TODO: check student logged in
////        logger.info("Fetching User with id {}", id);
//
//        String isbn = headers.get("isbn").get(0);
//        //TODO:call the book service
//        Book book = bookService.findByIsbn(isbn);
//        if (book == null) {
////            logger.error("User with id {} not found.", id);
//            return new ResponseEntity("A book with ISBN number: " + isbn + ", is not found", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(book, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/book/title", method = RequestMethod.GET)
//    public ResponseEntity getBookByTitle(@RequestHeader HttpHeaders headers) {
//        //TODO: check student logged in
////        logger.info("Fetching User with id {}", id);
//
//        //TODO:call the book service
//        String title = headers.get("title").get(0);
//        List<Book> books = bookService.findByTitle(title);
//        if (books.isEmpty()) {
////            logger.error("User with id {} not found.", id);
//            return new ResponseEntity("Books with title " + title + ", are not found", HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity(books, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/book/category", method = RequestMethod.GET)
//    public ResponseEntity getBookByCategory(@RequestHeader HttpHeaders headers) {
//        //TODO: check student logged in
////        logger.info("Fetching User with id {}", id);
//        //TODO:call the book service
//        String category = headers.get("category").get(0);
//        List<Book> books = bookService.findByCategory(category);
//        if (books.isEmpty()) {
////            logger.error("User with id {} not found.", id);
//            return new ResponseEntity("Books under this category " + category + ", are not found", HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity(books, HttpStatus.OK);
//    }

    private String getHeaderString(HttpHeaders headers) {
        return Helper.decodeBase64ToString((headers.get("Authorization").get(0)));
    }
}
