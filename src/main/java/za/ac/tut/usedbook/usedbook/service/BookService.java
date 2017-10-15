package za.ac.tut.usedbook.usedbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.repository.BookRepository;
import za.ac.tut.usedbook.usedbook.validation.Helper;
import za.ac.tut.usedbook.usedbook.validation.ValidateISBN;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gracem on 2017/09/26.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) throws BookISBNException {

        ValidateISBN.isISBN_valid(book.getIsbn());
        if(bookRepository.findByIsbn(book.getIsbn())!= null){
            throw new BookISBNException();
        }
        book.setStatus("ON-SALE");
        book.setCreatedAt(Helper.currentDate());
        return bookRepository.save(book);
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByCategory(String category) {
        List<Book> results = new ArrayList();
        for(Book book: findAll()) {
            if(book.getCategory().equalsIgnoreCase(category))
                results.add(book);
        }
        return results;

    }
    public List<Book> findByTitle(String title) {
        List<Book> results = new ArrayList();
        for(Book book: findAll()) {
            if(book.getTitle().equalsIgnoreCase(title))
                results.add(book);
        }
        return results;

    }



    public Book update(Book book) {
        book.setStatus("SOLD");
        return save(book);
    }

    public class BookISBNException extends RuntimeException{
        private final String message = "This ISBN number already exist. ISBN should be unique";
        @Override
        public String getMessage() {
            return message;
        }
    }
}
