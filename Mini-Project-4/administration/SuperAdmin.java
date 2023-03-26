package administration;

import library.Bookshelf;
import library.Category;
import library.Library;

import java.util.ArrayList;

public class SuperAdmin {
    final private String username = "superadmin";
    final private String password = "superadmin";

    public int createNewLibrary(ArrayList<Library> libraries, String libraryName, int bookshelfCount, Category[] categories, int[] shelvesCount, int[] shelvesSize) {
        for (Library library : libraries)
            if (library.getName().equals(libraryName)) {
                return -1;
            }

        Library newLibrary = new Library(libraryName, bookshelfCount);

        for (int i = 0; i < bookshelfCount; i++)
            newLibrary.getBookshelves()[i] = new Bookshelf(categories[i], shelvesCount[i], shelvesSize[i]);

        libraries.add(newLibrary);
        return 0;
    }

    public Library getLibraryByName(ArrayList<Library> libraries, String libraryName) {
        for (Library library : libraries) if (library.getName().equals(libraryName)) return library;
        return null;
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

    public Admin addAnAdmin(ArrayList<Library> libraries, String adminUsername, String adminPassword, ArrayList<String> libraryNames) {
        for (Library library : libraries) {
            for (Admin admin : library.getAdmins()) {
                if (admin.getUsername().equals(adminUsername))
                    return null;
            }
        }

        Admin newAdmin = new Admin(adminUsername, adminPassword);


        for (String libraryName : libraryNames) {
            Library library = getLibraryByName(libraries, libraryName);

            if (library == null) return null;

            library.getAdmins().add(newAdmin);
            newAdmin.getLibraries().add(library);
        }

        return newAdmin;
    }

    public int removeAnAdmin(ArrayList<Library> libraries, String libraryName, String adminUsername) {
        Library library = getLibraryByName(libraries, libraryName);

        if (library == null) return -1;

        for (Admin admin : library.getAdmins()) {
            if (admin.getUsername().equals(adminUsername)) {
                library.getAdmins().remove(admin);
                admin.getLibraries().remove(library);
                break;
            }
        }

        return 0;
    }
}
