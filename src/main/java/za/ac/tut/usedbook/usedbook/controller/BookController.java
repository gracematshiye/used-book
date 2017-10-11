package za.ac.tut.usedbook.usedbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.service.BookService;
import za.ac.tut.usedbook.usedbook.service.LoginService;
import za.ac.tut.usedbook.usedbook.validation.Helper;

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
    public @ResponseBody ResponseEntity createBook(@RequestBody Book book/*, UriComponentsBuilder ucBuilder*/) {

        Book savedBook = bookService.save(book);
//        book.setCreatedAt(date);
//        service.save(book);
//        logger.info("Creating User : {}", user);

//        TODO: check if the smae does not post an exiting book on the use's account
//        if (userService.isUserExist(user)) {
//            logger.error("Unable to save. A User with name {} already exist", user.getName());
//            return new ResponseEntity(new CustomErrorType("Unable to save. A User with name " +
//                    user.getName() + " already exist."),HttpStatus.CONFLICT);
//        }

//        TODO: call service to save book
//        userService.saveUser(user);

//        TODO: UriComponentsBuilder is for what????
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());


        return new ResponseEntity(savedBook, HttpStatus.CREATED);
    }


//
//    @RequestMapping(value = "/books", method = RequestMethod.GET)
//    public @ResponseBody ResponseEntity listAllBooks () {
//        //TODO: check student logged in
//
//        //TODO:call the book service
//        List<Book> books = bookService.findAll();
//
////        TODO: check if the list of books is not empty
//        if (books.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
////             You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity(books, HttpStatus.OK);
//    }
//
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

}
