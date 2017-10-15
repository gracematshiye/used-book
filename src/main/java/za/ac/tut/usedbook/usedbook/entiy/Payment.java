package za.ac.tut.usedbook.usedbook.entiy;

import javax.persistence.*;

/**
 * Created by gracem on 2017/10/12.
 */
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "buyer_id")
    private String buyerId;
    @Column(name = "seller_id")
    private String sellerId;
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "payment_date")
    private String paymentDate;

    public Payment(String buyerId, String sellerId, String bookId, double amount, String paymentDate) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
