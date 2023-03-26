package user;

import administration.Admin;
import administration.Request;
import library.Book;
import library.Bookshelf;
import library.Library;

import java.util.ArrayList;

public class User {
    final static String USER_CONST = "USR_";
    final private String username;
    final private String password;
    final private String fullName;
    final private ArrayList<Book> borrowedBooks;

    public User(String username, String password, String fullName) {
        this.username = USER_CONST + username;
        this.password = password;
        this.fullName = fullName;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getAllLibrariesInfo(ArrayList<Library> libraries) {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (Library library : libraries) {
            stringBuilder.append("Library #").append(i).append("\n");
            stringBuilder.append("Name:").append(library.getName()).append("\n");
            stringBuilder.append("Admins: ").append("\n");
            for (Admin admin : library.getAdmins())
                stringBuilder.append("\t").append(admin.getUsername());

            i++;
        }

        return stringBuilder.toString();
    }

    public ArrayList<Book> searchABook(ArrayList<Library> libraries, String searchQuery) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Library library : libraries) {
            for (Bookshelf bookshelf : library.getBookshelves()) {
                for (int i = 0; i < bookshelf.getShelfCount(); i++)
                    for (int j = 0; j < bookshelf.getShelfSize(); j++) {
                        Book book = bookshelf.getBooks()[i][j];
                        if (book != null && (book.getTitle().contains(searchQuery)
                                || book.getAuthor().contains(searchQuery)
                                || book.getCategory().name().contains(searchQuery))) {
                            searchResult.add(book);
                        }
                    }
            }
        }
        return searchResult;
    }

    public int sendBookBorrowingRequest(Library library, String bookId) {
        Book book = library.getBookByID(bookId);
        if (book == null) {
            return -1;
        }

        Request request = new Request(this, book, library.getName());
        library.getRequests().add(request);
        return 0;
    }

    public String getBorrowedBooksInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : borrowedBooks) {
            stringBuilder.append(book.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
