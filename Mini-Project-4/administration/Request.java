package administration;

import library.Book;
import user.User;

public class Request {
    final private User user;
    final private Book book;
    final private String libraryName;

    public Request(User user, Book book, String libraryName) {
        this.user = user;
        this.book = book;
        this.libraryName = libraryName;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public String getLibraryName() {
        return libraryName;
    }
}
