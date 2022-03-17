package catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AudioFeatures implements Feature {

    private String title;
    private int length;
    private List<String> performers = new ArrayList<>();
    private List<String> composer = new ArrayList<>();

    public AudioFeatures(String title, int length, List<String> performers) {
        if (isAudioFeaturesValid(title, length, performers)) {
            this.title = title;
            this.length = length;
            this.performers = performers;
        }
    }

    public AudioFeatures(String title, int length, List<String> performers, List<String> composer) {
        if (isAudioFeaturesValid(title, length, performers)) {
            this.title = title;
            this.length = length;
            this.performers = performers;
        }
        if (Validators.isEmpty(composer)) {
            throw new IllegalArgumentException("Empty title");
        }
        this.composer = composer;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<String> getContributors() {
        List<String> result = new ArrayList<>(composer);
        result.addAll(performers);
        return result;
    }

    public List<String> getComposer() {
        return composer;
    }

    private boolean isAudioFeaturesValid(String title, int length, List<String> performers) {

        if (Validators.isBlank(title)) {
            throw new IllegalArgumentException("Empty title");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("invalid page number");
        }
        if (Validators.isEmpty(performers)) {
            throw new IllegalArgumentException("Empty list");
        }
        return true;
    }
}
