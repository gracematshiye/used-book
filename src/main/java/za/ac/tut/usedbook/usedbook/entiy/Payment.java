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
    private Integer buyerId;
    @Column(name = "seller_id")
    private Integer sellerId;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "payment_date")
    private String paymentDate;
    @Column(name = "payment_reference")
    private String paymentReference;

    public Payment(Integer buyerId, Integer sellerId, Integer bookId, double amount, String paymentDate, String paymentReference) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentReference = paymentReference;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
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

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}
