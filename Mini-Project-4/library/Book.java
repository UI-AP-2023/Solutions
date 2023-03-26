package library;

public class Book {
    final private String title;
    final private String author;
    final private Category category;
    final private int publishYear;
    final private int pageCount;
    private boolean isLent;
    final private String id;

    public Book(String title, String author, Category category, int publishYear, int pageCount) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishYear = publishYear;
        this.pageCount = pageCount;
        this.isLent = false;

        StringBuilder stringBuilder = new StringBuilder(); // Can be replaced with String tho...

        if (title.length() > 4)
            stringBuilder.append(title, 0, 4);
        else
            stringBuilder.append(title);
        stringBuilder.append("-");
        if (author.length() > 3)
            stringBuilder.append(author, 0, 3);
        else
            stringBuilder.append(author);
        stringBuilder.append("-");
        stringBuilder.append(category.name());
        stringBuilder.append("-");
        stringBuilder.append(publishYear);

        this.id = stringBuilder.toString();
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isLent() {
        return isLent;
    }

    public void setLent(boolean lent) {
        isLent = lent;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category=" + category +
                ", publishYear=" + publishYear +
                ", pageCount=" + pageCount +
                ", isLent=" + isLent +
                ", id='" + id + '\'' +
                '}';
    }
}
