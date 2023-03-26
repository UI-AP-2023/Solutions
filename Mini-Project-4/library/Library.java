package library;

import administration.Admin;
import administration.Request;
import user.User;

import java.util.ArrayList;


public class Library {
    final private String name;
    final private Bookshelf[] bookshelves;
    final private ArrayList<Admin> admins;
    final private ArrayList<Request> requests;
    final private ArrayList<Request> acceptedRequests;
    final private ArrayList<Book> lentBooks;

    public Library(String name, int bookshelfCount) {
        this.bookshelves = new Bookshelf[bookshelfCount];
        this.name = name;
        this.admins = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.acceptedRequests = new ArrayList<>();
        this.lentBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Bookshelf[] getBookshelves() {
        return bookshelves;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<Request> getAcceptedRequests() {
        return acceptedRequests;
    }

    public ArrayList<Book> getLentBooks() {
        return lentBooks;
    }

    public User getUserByUsername(ArrayList<User> users, String username) {
        for (User user : users) if (user.getUsername().equals(username)) return user;
        return null;
    }

    public int insertBook(Book book) {
        Bookshelf foundBookshelf = null;
        for (Bookshelf bookshelf : bookshelves)
            if (book.getCategory() == bookshelf.getCategory() && bookshelf.getCurrentBookCount() < bookshelf.getMaximumBookCount())
                foundBookshelf = bookshelf;

        if (foundBookshelf == null) return -1;

        for (int i = 0; i < foundBookshelf.getShelfCount(); i++) {
            int numberOfBooksInShelf = foundBookshelf.getNumberOfBooksInShelves()[i];
            if (numberOfBooksInShelf < foundBookshelf.getShelfSize()) {
                foundBookshelf.addBookInPlace(book, i, numberOfBooksInShelf);
                return 0;
            }
        }
        return -1;
    }

    public int borrowBook(Book book, User user) {
        if (user.getBorrowedBooks().size() >= 3) return -1;
        user.getBorrowedBooks().add(book);
        book.setLent(true);
        getLentBooks().add(book);
        return 0;
    }

    public Book getBookByID(String ID) {
        for (Bookshelf bookshelf : getBookshelves()) {
            for (int i = 0; i < bookshelf.getShelfCount(); i++) {
                for (Book book : bookshelf.getBooks()[i]) {
                    if (book.getId().equals(ID)) return book;
                }
            }
        }
        return null;
    }
}

