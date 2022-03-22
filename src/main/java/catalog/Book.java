package catalog;

import java.util.ArrayList;
import java.util.List;

public class Book implements LibraryItem {

    private long id;
    private String title;
    private List<String> authors;
    private int yearOfPublication;
    private int quantity;

    public Book(String title, List<String> authors, int yearOfPublication, int quantity) {
        this.title = title;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
        this.quantity = quantity;
    }

    public Book(long id, String title, List<String> authors, int yearOfPublication, int quantity) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
        this.quantity = quantity;
    }

    public Book(String title, List<String> authors, int yearOfPublication) {
        this.title = title;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getContributors() {
        return new ArrayList<>(authors);
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", yearOfPublication=" + yearOfPublication +
                ", quantity=" + quantity +
                '}';
    }
}
