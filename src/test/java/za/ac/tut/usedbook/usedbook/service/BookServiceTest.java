package za.ac.tut.usedbook.usedbook.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by gracem on 2017/09/28.
 */
//@Ignore
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class BookServiceTest {

    private BookService service;

    @Autowired
    private BookRepository repoMock;


    @Before
    public void setUp() {
        this.repoMock = Mockito.mock(BookRepository.class);
        this.service = new BookService(repoMock);
    }

    @Ignore
    @Test
    public void shouldCallRepoMethodOnce() throws Exception {

        Book book = getSingleBook();
        when(repoMock.save(book)).thenReturn(book);
        service.save(book);
        verify(repoMock, times(1)).save(book);

        try{
            when(repoMock.findByIsbn(book.getIsbn())).thenReturn(getSingleBook());
            service.save(book);
            fail("Did not throw BookISBNException");
        } catch (BookService.BookISBNException e){
            assertThat(e.getMessage()).isEqualTo("This ISBN number already exist. ISBN should be unique");
        }

    }


    @Test
    public void shouldReturnBookById() throws Exception {
        Book book = getSingleBook();
        when(repoMock.findById(1)).thenReturn(book);
        Book results = service.findById(1);
        assertThat(results).isEqualTo(book);


    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        when(repoMock.findAll()).thenReturn(getListOfBooks());

        List<Book> result = service.findAll();

        assertThat(result.size()).isEqualTo(getListOfBooks().size());

    }

    @Test
    public void shouldReturnABookByISBN() throws Exception {
        //by isbn
        when(repoMock.findByIsbn("isbn-1")).thenReturn(getSingleBook());
        Book results =  service.findByIsbn("isbn-1");
        assertThat(results.getIsbn()).isEqualTo(getSingleBook().getIsbn());
        assertThat(results.getTitle()).isEqualTo(getSingleBook().getTitle());

        when(repoMock.findByIsbn("isbn-5")).thenReturn(null);
        results =  service.findByIsbn("isbn-5");
        assertThat(results).isNull();

    }

    @Test
    public void shouldReturnABookByCategory() throws Exception {
        List<Book> expectedList = getListOfBooks();
        when(repoMock.findAll()).thenReturn(expectedList);

        List<Book> result1 =  service.findByCategory("TPG");
        List<Book> result2 =  service.findByCategory("tpg");

        assertThat(result1.size()).isEqualTo(getListOfBooks().size());
        assertThat(result2.size()).isEqualTo(getListOfBooks().size());

    }

    @Test
    public void shouldUpdateStatusWhenPurchasedIsSuccessful() throws Exception {
        Book book = getSingleBook();
        book.setStatus("SOLD");
        when(repoMock.save(book)).thenReturn(book);
        Book results =  service.update(book);
        assertThat(results.getStatus()).isEqualTo(book.getStatus());
        assertThat(results.getTitle()).isEqualTo(book.getTitle());

    }

    /**
     * save a book #
     * get 1 b #
     * get all books #
     * get book<> by title, category, isbn
     * update a book status
     */
    private Book getSingleBook() {
        return new Book(1,"Java", "Thoma JR","ISBN-13: 978-0-596-52068-7","7th","lang","TPG",7.9, "ON-SALE","additionalIno");
    }
    private List<Book> getListOfBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(1,"Java", "Thoma JR","ISBN-13: 978-0-596-52068-7","7th","lang","TPG",7.9, "SOLD","additionalIno"));
        books.add(new Book(2,"C#","Thomas", "ISBN-13: 978-0-596-52061-7","7th","lang","tpg",7.9, "ON-SALE","additionalIno"));
        return books;
    }
}
