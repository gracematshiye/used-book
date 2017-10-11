package za.ac.tut.usedbook.usedbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.tut.usedbook.usedbook.entiy.Book;

import java.util.List;

/**
 * Created by gracem on 2017/09/26.
 */
//@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book save(Book book);
    Book findById(Integer id);
    List<Book> findAll();
    Book findByIsbn(String isbn);
//    List<Book>  findByCategory(String keyWord);
//    void save(Book book);
//    void update(Book book);
//    Book getBookById(long id);
}
