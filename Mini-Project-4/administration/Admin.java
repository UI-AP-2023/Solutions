package administration;

import library.Book;
import library.Category;
import library.Library;
import user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    final static String ADMIN_CONST = "ADM_";
    final private String username;
    final private String password;
    final private ArrayList<Library> libraries;


    public Admin(String username, String password) {
        this.username = ADMIN_CONST + username;
        this.password = password;
        this.libraries = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Library> getLibraries() {
        return libraries;
    }

    String printLibraries() {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (Library library : getLibraries())
            stringBuilder.append("#").append(i).append(") ").append(library.getName());

        return stringBuilder.toString();
    }

    public int addANewBook(Library library, String title, String author, Category category, int publishYear, int pageCount) {
        Book newBook = new Book(title, author, category, publishYear, pageCount);

        return library.insertBook(newBook);
    }

    public int manageRequests(Library library, int requestIndex, String command) {
        boolean adminHasAccess = false;
        for (Admin tempAdmin : library.getAdmins()) {
            if (tempAdmin == this) {
                adminHasAccess = true;
                break;
            }
        }
        if (!adminHasAccess) return -3;

        Request request = library.getRequests().get(requestIndex);

        User user = request.getUser();

        if (command.equals("y")) {
            int statusCode = library.borrowBook(request.getBook(), user);
            if (statusCode < 0) return statusCode;
            else {
                library.getAcceptedRequests().add(request);
                library.getRequests().remove(request);
                return 0;
            }
        } else if (command.equals("n")) {
            library.getRequests().remove(request);
            return 1;
        } else return -2;
    }
}
