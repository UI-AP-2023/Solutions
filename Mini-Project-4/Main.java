import administration.Admin;
import administration.Request;
import administration.SuperAdmin;
import library.Book;
import library.Bookshelf;
import library.Category;
import library.Library;
import user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner stdin = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Library> libraries = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        User loggedInUser = null;
        Admin loggedInAdmin = null;
        SuperAdmin superAdmin = new SuperAdmin();

        System.out.println("ADDING A NEW USER");
        User newUser = registerUser(users);
        users.add(newUser);
        // To log in a user, we just have to loop through the user array and check the credentials
        loggedInUser = newUser;

        System.out.println("---- SUPER ADMIN TIME ----");
        // CREATING A NEW LIBRARY
        Library library = createLibrary(superAdmin, libraries);
        String allLibrariesInfo = superAdmin.getAllLibrariesInfo(libraries);
        System.out.println(allLibrariesInfo);

        // ADDING NEW ADMIN
        Admin admin = createNewAdmin(superAdmin, libraries, users);

        // ADDING A NEW BOOK
        System.out.println("---- ADMIN TIME ----");
        addNewBook(admin, libraries);


        System.out.println("---- USER TIME ----");
        // SEE ALL LIBRARIES
        String allLibraries = loggedInUser.getAllLibrariesInfo(libraries);
        System.out.println(allLibraries);

        // SEARCHING
        System.out.println("### SEARCHING ###");
        System.out.print("Enter search query: ");
        String searchQuery = stdin.nextLine();
        ArrayList<Book> searchResult = loggedInUser.searchABook(libraries, searchQuery);
        for (Book book : searchResult) System.out.println(book);


        // BORROWING
        borrowABook(loggedInUser, libraries);

        // MANAGING REQUESTS
        manageRequests(admin, library);
    }

    static User registerUser(ArrayList<User> users) {
        System.out.println("### New user ###");

        String username = null;
        boolean usernameExists = true;
        while (usernameExists) {
            System.out.print("username: ");
            username = stdin.nextLine();
            usernameExists = false;
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Username already exists");
                    usernameExists = true;
                    break;
                }
            }
        }
        System.out.print("password: ");
        String password = stdin.nextLine();
        System.out.print("full name: ");
        String fullName = stdin.nextLine();

        User newUser = new User(username, password, fullName);
        users.add(newUser);

        return newUser;
    }


    static Library createLibrary(SuperAdmin superAdmin, ArrayList<Library> libraries) {
        System.out.println("### CREATING A NEW LIBRARY ###");

        String libraryName = null;
        boolean libraryNameExists = true;
        while (libraryNameExists) {
            System.out.print("Enter library's name: ");
            libraryName = stdin.nextLine();
            libraryNameExists = false;
            for (Library library : libraries) {
                if (library.getName().equals(libraryName)) {
                    System.out.println("Library name exists");
                    libraryNameExists = true;
                    break;
                }
            }
        }

        System.out.print("How many bookshelves does this library have?: ");
        int bookshelfCount = stdin.nextInt();
        stdin.nextLine();

        Category[] categories = new Category[bookshelfCount];
        int[] shelvesCount = new int[bookshelfCount];
        int[] shelvesSize = new int[bookshelfCount];

        for (int i = 0; i < bookshelfCount; i++) {
            System.out.print("Enter bookshelf #" + (i + 1) + "'s category name: ");

            categories[i] = Category.valueOf(stdin.nextLine());

            System.out.print("Enter the shelves count and their sizes (2 integers): ");
            shelvesCount[i] = stdin.nextInt();
            shelvesSize[i] = stdin.nextInt();
            stdin.nextLine();
        }
        int returnCode = superAdmin.createNewLibrary(libraries, libraryName, bookshelfCount, categories, shelvesCount, shelvesSize);
        if (returnCode == -1) System.out.println("Library name exists!");
        else if (returnCode < 0) System.out.println("Couldn't create library!");
        else System.out.println("Library was created successfully");

        return libraries.get(libraries.size() - 1);
    }

    static Admin createNewAdmin(SuperAdmin superAdmin, ArrayList<Library> libraries, ArrayList<User> users) {
        System.out.println("### ADDING A NEW ADMIN ###");

        System.out.print("Enter admin's username: ");
        String adminUsername = stdin.nextLine();
        System.out.print("Enter admin's password: ");
        String adminPassword = stdin.nextLine();
        System.out.print("Enter the library name you want to add this admin to: ");
        ArrayList<String> libraryNamesToAddAdminTo = new ArrayList<>();
        String libraryName = stdin.nextLine();
        libraryNamesToAddAdminTo.add(libraryName);
        Admin newAdmin = superAdmin.addAnAdmin(libraries, adminUsername, adminPassword, libraryNamesToAddAdminTo);
        if (newAdmin == null) {
            System.out.println("Couldn't add admin!");
            return null;
        } else System.out.println("Admin was added successfully");

        String allLibrariesInfo = superAdmin.getAllLibrariesInfo(libraries);
        System.out.println(allLibrariesInfo);

        return newAdmin;
    }

    static void addNewBook(Admin admin, ArrayList<Library> libraries) {
        System.out.println("### ADDING A NEW BOOK ###");

        System.out.print("Enter library name: ");
        String libraryName = stdin.nextLine();
        Library library = null;
        for (Library tempLibrary : libraries) {
            if (tempLibrary.getName().equals(libraryName)) {
                library = tempLibrary;
                break;
            }
        }
        if (library == null) {
            System.out.println("Library name doesn't exist");
            return;
        }

        System.out.print("Enter book's title: ");
        String bookTitle = stdin.nextLine();
        System.out.print("Enter book's author: ");
        String bookAuthor = stdin.nextLine();
        System.out.println("Available categories:");
        for (Bookshelf bookshelf : library.getBookshelves())
            System.out.print(bookshelf.getCategory() + ", ");
        System.out.print("\nEnter book's category: ");
        Category bookCategory = Category.valueOf(stdin.nextLine());
        System.out.print("Enter book's publish year: ");
        int publishYear = stdin.nextInt();
        stdin.nextLine();
        System.out.print("Enter book's page count: ");
        int pageCount = stdin.nextInt();
        stdin.nextLine();
        int returnCode = admin.addANewBook(library, bookTitle, bookAuthor, bookCategory, publishYear, pageCount);
        if (returnCode < 0) System.out.println("Couldn't add book.");
        else System.out.println("Book was added successfully");
    }

    static void borrowABook(User user, ArrayList<Library> libraries) {
        System.out.println("### BORROWING ###");

        System.out.print("Enter book ID to borrow: ");
        String bookIDToBorrow = stdin.nextLine();
        System.out.print("Enter library's name: ");
        String libraryName = stdin.nextLine();
        Library library = null;
        for (Library tempLibrary : libraries) {
            if (tempLibrary.getName().equals(libraryName)) {
                library = tempLibrary;
                break;
            }
        }
        if (library == null) {
            System.out.println("Library name doesn't exist");
            return;
        }
        int statusCode = user.sendBookBorrowingRequest(library, bookIDToBorrow);
        if (statusCode < 0)
            System.out.println("Couldn't borrow book.");
        else System.out.println("Book borrowing request was sent successfully.");
    }

    static void manageRequests(Admin admin, Library library) {
        System.out.println("### MANAGING BORROW REQUESTS ###");
        int i = 0;
        for (Request request : library.getRequests()) {
            System.out.println("Req #" + i + ") " + request.getUser().getUsername() + " requests to borrow " + request.getBook().getId());
        }
        System.out.print("Enter request index to manage (-1 to exit): ");
        int requestIndex = stdin.nextInt();
        stdin.nextLine();
        if (requestIndex == -1) return;
        System.out.print("Enter y to accept and n to reject: ");
        String acceptReject = stdin.nextLine();
        int statusCode = admin.manageRequests(library, requestIndex, acceptReject);

        if (statusCode < 0) System.out.println("Couldn't lend the book.");
        else if (statusCode == 0) System.out.println("Request was accepted. Book was lent.");
        else if (statusCode == 1) System.out.println("Request rejected.");
    }
}

