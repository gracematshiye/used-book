package za.ac.tut.usedbook.usedbook.viewModel;

import za.ac.tut.usedbook.usedbook.entiy.Book;

import java.util.List;

/**
 * Created by gracem on 2017/10/15.
 */
public class ListBookViewModel {
    private UserViewModel userViewModel;
    private List<Book> books;

    public ListBookViewModel(UserViewModel userViewModel, List<Book> books) {

        this.userViewModel = userViewModel;
        this.books = books;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
