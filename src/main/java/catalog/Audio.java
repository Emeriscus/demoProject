package catalog;

import java.util.ArrayList;
import java.util.List;

public class Audio implements LibraryItem {

    private long id;
    private String title;
    private List<String> performers = new ArrayList<>();
    private List<String> composers = new ArrayList<>();
    private int yearOfPublication;
    private int quantity;

    public Audio(String title, List<String> performers, List<String> composers, int yearOfPublication) {
        this.title = title;
        this.performers = performers;
        this.composers = composers;
        this.yearOfPublication = yearOfPublication;
    }

    public Audio(String title, List<String> performers, List<String> composers, int yearOfPublication, int quantity) {
        this.title = title;
        this.performers = performers;
        this.composers = composers;
        this.yearOfPublication = yearOfPublication;
        this.quantity = quantity;
    }

    public Audio(long id, String title, List<String> performers, List<String> composers, int yearOfPublication, int quantity) {
        this.id = id;
        this.title = title;
        this.performers = performers;
        this.composers = composers;
        this.yearOfPublication = yearOfPublication;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getPerformers() {
        return new ArrayList<>(performers);
    }

    public List<String> getComposers() {
        return new ArrayList<>(composers);
    }

    @Override
    public List<String> getContributors() {
        List<String> result = new ArrayList<>(composers);
        result.addAll(performers);
        return result;
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
        return "Audio{" +
                "title='" + title + '\'' +
                ", performers=" + performers +
                ", composers=" + composers +
                ", yearOfPublication=" + yearOfPublication +
                ", quantity=" + quantity +
                '}';
    }

}
