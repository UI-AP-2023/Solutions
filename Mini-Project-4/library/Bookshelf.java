package library;

public class Bookshelf {
    final private Category category;
    final private Book[][] books;

    final private int shelfCount;
    final private int shelfSize;
    final private int maximumBookCount;
    private int currentBookCount;
    final private int[] numberOfBooksInShelves;

    public Bookshelf(Category category, int shelfCount, int shelfSize) {
        this.category = category;
        this.books = new Book[shelfCount][shelfSize];
        this.shelfCount = shelfCount;
        this.shelfSize = shelfSize;

        this.maximumBookCount = shelfCount * shelfSize;
        this.currentBookCount = 0;
        this.numberOfBooksInShelves = new int[shelfSize];
    }

    public Category getCategory() {
        return category;
    }

    public Book[][] getBooks() {
        return books;
    }

    public int getShelfCount() {
        return shelfCount;
    }

    public int getShelfSize() {
        return shelfSize;
    }

    public int getMaximumBookCount() {
        return maximumBookCount;
    }

    public int getCurrentBookCount() {
        return currentBookCount;
    }

    public void setCurrentBookCount(int currentBookCount) {
        this.currentBookCount = currentBookCount;
    }

    public int[] getNumberOfBooksInShelves() {
        return numberOfBooksInShelves;
    }

    public void addBookInPlace(Book book, int shelfIndex, int index) {
        getBooks()[shelfIndex][index] = book;
        setCurrentBookCount(getCurrentBookCount() + 1);
        getNumberOfBooksInShelves()[shelfIndex]++;
    }
}
