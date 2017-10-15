package za.ac.tut.usedbook.usedbook.service;

import org.springframework.stereotype.Service;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.BookRepository;
import za.ac.tut.usedbook.usedbook.repository.PaymentRepository;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;

/**
 * Created by gracem on 2017/09/30.
 */
@Service
public class PaymentService {

    private BookRepository bookRepository;
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
//
//    @Autowired
//    public PaymentService(BookRepository bookRepository, StudentRepository studentRepository, PaymentRepository paymentRepository) {
//        this.bookRepository = bookRepository;
//        this.studentRepository = studentRepository;
//        this.paymentRepository = paymentRepository;
//    }


    public void processPayment() {
        Student buyer;
        Student seller;
        Book book;

        //TODO: save all this instance
        //TODO: return payment object
    }

//1
    public boolean isBookONSALE(Book book) {
        if(book.getStatus().equals("SOLD")){
            throw new BookStatusException();
        }
        return true;
    }
//2
    public boolean isFundsSufficient(Student student, Book book) {
        if(Double.compare(student.getFunds(), book.getPrice()) < 0){
            throw new InsufficientFundsException();
        }
        return true;
    }
//3
    public Student deductFunds(Student student, Book book) {
        student.setFunds(student.getFunds() - book.getPrice());
        return student;
    }
//4
    public Student increaseFunds(Student student, Book book) {
        student.setFunds(student.getFunds() + book.getPrice());
        return student;
    }
//5
    public Book changeBookStatus(Book book) {
        book.setStatus("SOLD");
        return book;
    }



    public class InsufficientFundsException extends RuntimeException{
        private final String message = "You have insufficient Funds";
        @Override
        public String getMessage() {
            return message;
        }
    }

    public class BookStatusException extends RuntimeException{
        private final String message = "The book you're about to buy is already SOLD.";
        @Override
        public String getMessage() {
            return message;
        }
    }
}
