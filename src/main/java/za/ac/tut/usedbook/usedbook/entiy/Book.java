package za.ac.tut.usedbook.usedbook.entiy;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by gracem on 2017/09/24.
 */
@Entity
@Table(name = "BOOK")
@EntityListeners(AuditingEntityListener.class)
public class Book /*implements Serializable */{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "edition")
    private String edition;
    @Column(name = "language")
    private String language;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private String status;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "created_at")
    private String createdAt;

    public Book() {}

    public Book(Integer id, String title, String author, String isbn, String edition, String language, String category, double price, String status, String additionalInfo) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.edition = edition;
        this.language = language;
        this.category = category;
        this.price = price;
        this.status = status;
        this.additionalInfo = additionalInfo;
    }

    public Book(String title, String author, String isbn, String edition, String language, String category, double price, String status, String additionalInfo) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.edition = edition;
        this.language = language;
        this.category = category;
        this.price = price;
        this.status = status;
        this.additionalInfo = additionalInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
