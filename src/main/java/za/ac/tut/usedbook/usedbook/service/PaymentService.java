package za.ac.tut.usedbook.usedbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Payment;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.BookRepository;
import za.ac.tut.usedbook.usedbook.repository.PaymentRepository;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;
import za.ac.tut.usedbook.usedbook.validation.Helper;

/**
 * Created by gracem on 2017/09/30.
 */
@Service
public class PaymentService {

    private BookRepository bookRepository;
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(BookRepository bookRepository, StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    public PaymentService() {

    }


    public Payment processPayment(Book book, Student buyer) throws Exception {
        Student seller = studentRepository.findByStudentId(book.getSellerId());
        isBookONSALE(book);
        isFundsSufficient(buyer, book);
        isSellerBuying(book,buyer);

        buyer = deductFunds(buyer, book);
        seller = increaseFunds(seller, book);
        book = changeBookStatus(book);

        Payment payment = new Payment(buyer.getStudentId(),
                seller.getStudentId(),book.getId(),
                book.getPrice(), Helper.currentDate(),
                Helper.generatePaymentReference());
        //TODO: save all changes
        studentRepository.save(seller);
        studentRepository.save(buyer);
        bookRepository.save(book);

//        MailSender.sendEmail();
        //TODO: return payment object
        return paymentRepository.save(payment);

    }

    public Payment findByPaymentReference(String paymentRef) throws NonExistedPaymentException {
        Payment payment = paymentRepository.findByPaymentReference(paymentRef);
//        if(payment == null) {
//            throw new NonExistedPaymentException();
//        }
        return payment;
    }

    public boolean isSellerBuying(Book book, Student buyer) {
        if(book.getSellerId().equals(buyer.getStudentId())){
            throw new PaymentException();
        }
        return true;
    }

    //1
    public boolean isBookONSALE(Book book) {
        if(book.getStatus().equals("SOLD")){
            throw new BookStatusException();
        }
        return true;
    }
//2
    public boolean isFundsSufficient(Student student, Book book) throws Exception {
        if(Double.compare(student.getFunds(), book.getPrice()) < 0){
            throw new InsufficientFundsException();
        }
        return true;
    }
//3
    public Student deductFunds(Student student, Book book) throws Exception {
        student.setFunds(student.getFunds() - book.getPrice());
        return student;
    }
//4
    public Student increaseFunds(Student student, Book book) throws Exception {
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

    public class PaymentException extends RuntimeException {
        private final String message = "Student can't purchase his or her own book posted";
        @Override
        public String getMessage() {
            return message;
        }
    }

    public class NonExistedPaymentException extends Throwable {
        private final String message = "Payment reference does not exist.";
        @Override
        public String getMessage() {
            return message;
        }
    }
}
