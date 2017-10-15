package za.ac.tut.usedbook.usedbook.viewModel;

import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Payment;
import za.ac.tut.usedbook.usedbook.entiy.Student;

/**
 * Created by gracem on 2017/10/15.
 */
public class PaymentViewModel {

    private String paymentReference;
    private String paymentDate;
    private UserViewModel buyer;
    private UserViewModel seller;
    private Book soldBook;


    public PaymentViewModel(Payment payment, Student buyer, Student seller, Book soldBook) {

        this.paymentReference = payment.getPaymentReference();
        this.paymentDate = payment.getPaymentDate();
        this.buyer = new UserViewModel(buyer);
        this.seller = new UserViewModel(seller);
        this. soldBook = soldBook;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public UserViewModel getBuyer() {
        return buyer;
    }

    public void setBuyer(UserViewModel buyer) {
        this.buyer = buyer;
    }

    public UserViewModel getSeller() {
        return seller;
    }

    public void setSeller(UserViewModel seller) {
        this.seller = seller;
    }

    public Book getSoldBook() {
        return soldBook;
    }

    public void setSoldBook(Book soldBook) {
        this.soldBook = soldBook;
    }
}
