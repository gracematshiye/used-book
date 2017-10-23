package za.ac.tut.usedbook.usedbook.service;

import org.junit.Before;
import org.junit.Test;
import za.ac.tut.usedbook.usedbook.TestBase;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.validation.Helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by gracem on 2017/10/01.
 */
public class PaymentServiceTest extends TestBase{

    private PaymentService paymentService;

    @Before
    public void setUp() throws Exception {
        paymentService = new PaymentService();

    }

    @Test
    public void shouldReturnTrueWhenBookStatusIsNotSOLD() throws Exception {

        Book book = getSingleBook();
        book.setStatus("ON-SALE");

        boolean result = paymentService.isBookONSALE(book);
        assertThat(result).isTrue();

    }

    @Test
    public void shouldThrowExceptionWhenBookStatusIsSOLD() throws Exception {

        Book book = getSingleBook();
        book.setStatus("SOLD");

        try{
            paymentService.isBookONSALE(book);
            fail("Did not throw BookStatusException");
        } catch (PaymentService.BookStatusException e){
            assertThat(e.getMessage()).isEqualTo("The book you're about to buy is already SOLD.");
        }
    }


    @Test
    public void shouldReturnTrueWhenStudentBuyerHaveSufficientFund() throws Exception {

        Student student = getExistingStudent();
        Book book = getSingleBook();

        boolean result = paymentService.isFundsSufficient(student, book);
        assertThat(result).isTrue();

        student.setFunds(320.00);
        book.setPrice(310.00);
        result = paymentService.isFundsSufficient(student, book);
        assertThat(result).isTrue();
        System.out.println(book.getPrice());
        System.out.println(student.getFunds());
    }

    @Test
    public void shouldThrowExceptionWhenStudentBuyerHaveInsufficientFund() throws Exception {

        Student student = getExistingStudent();
        student.setFunds(10.70);
        Book book = getSingleBook();

        System.out.println(book.getPrice());
        System.out.println(student.getFunds());
        try{
            paymentService.isFundsSufficient(student, book);
            fail("Did not throw InsufficientFundsException");
        } catch (PaymentService.InsufficientFundsException e){
            assertThat(e.getMessage()).isEqualTo("You have insufficient Funds");
        }
    }

    @Test
    public void substractBuyersFunds() throws Exception {
        Student student = getExistingStudent();
        Book book = getSingleBook();

        double expected = student.getFunds() - book.getPrice();

        student = paymentService.deductFunds(student, book);
        assertThat(student.getFunds()).isEqualTo(Helper.getDoubleForm(expected));

    }


    @Test
    public void addSellersFunds() throws Exception {
        Student student = getExistingStudent();
        Book book = getSingleBook();

        double expected = student.getFunds() + book.getPrice();

        student = paymentService.increaseFunds(student, book);
        assertThat(student.getFunds()).isEqualTo(expected);

    }

    @Test
    public void shouldChangeBookStatusAfterSold() throws Exception {

        Book book = getSingleBook();

        book = paymentService.changeBookStatus(book);
        assertThat(book.getStatus()).isEqualTo("SOLD");

    }
}
