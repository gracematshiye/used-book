package za.ac.tut.usedbook.usedbook.viewModel;

import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Student;

/**
 * Created by gracem on 2017/10/15.
 */
public class NewBookViewModel {

    private UserViewModel userViewModel;
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String edition;
    private String category;
    private String language;
    private double price;
    private String status;
    private String additionalInfo;
    private String createdAt;

    public NewBookViewModel(Student student, Book book) {
        this.userViewModel = new UserViewModel(student);
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.edition = book.getEdition();
        this.language = book.getLanguage();
        this.category = book.getCategory();
        this.price = book.getPrice();
        this.status = book.getStatus();
        this.additionalInfo = book.getAdditionalInfo();
        this.createdAt = book.getCreatedAt();
        
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEdition() {
        return edition;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Integer getId() {
        return id;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
