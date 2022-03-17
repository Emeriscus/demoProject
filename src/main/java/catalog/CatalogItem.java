package catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CatalogItem {

    private String registrationNumber;
    private int price;
    private List<Feature> features = new ArrayList<>();

    public CatalogItem(String registrationNumber, int price, Feature... features) {
        this.registrationNumber = registrationNumber;
        this.price = price;
        this.features = Arrays.asList(features);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getPrice() {
        return price;
    }

    public List<Feature> getFeatures() {
        return new ArrayList<>(features);
    }

    public List<String> getContributors() {
//        return features.stream()
//                .flatMap(Feature::getContributors)
//                .flatMap(o -> o.toString())
//                .toList();
        List<String> result = new ArrayList<>();
        for (Feature actual : features) {
            result.addAll(actual.getContributors());
        }
        return result;
    }

    public List<String> getTitles() {
        return features.stream()
                .map(Feature::getTitle)
                .toList();
//        List<String> result = new ArrayList<>();
//        for (Feature actual : features) {
//            result.add(actual.getTitle());
//        }
//        return result;
    }


    public boolean hasAudioFeature() {
        return features.stream()
                .anyMatch(feature -> feature instanceof AudioFeatures);

//        for (Feature actual : features) {
//            if (actual instanceof AudioFeatures) {
//                return true;
//            }
//        }
//        return false;
    }

    public boolean hasPrintedFeature() {
        return features.stream()
                .anyMatch(feature -> feature instanceof PrintedFeatures);

//        for (Feature actual : features) {
//            if (actual instanceof PrintedFeatures) {
//                return true;
//            }
//        }
//        return false;
    }

    public int fullLengthAtOneItem() {
        return features.stream()
                .filter(feature -> feature instanceof AudioFeatures)
                .mapToInt(o -> ((AudioFeatures) o).getLength())
                .sum();
//        int sum = 0;
//        for (Feature actual : features) {
//            if (actual instanceof AudioFeatures) {
//                sum += ((AudioFeatures) actual).getLength();
//            }
//        }
//        return sum;
    }

    public int numberOfPagesAtOneItem() {
        return features.stream()
                .filter(feature -> feature instanceof PrintedFeatures)
                .mapToInt(o -> ((PrintedFeatures) o).getNumberOfPages())
                .sum();
//        int sum = 0;
//        for (Feature actual : features) {
//            if (actual instanceof PrintedFeatures) {
//                sum += ((PrintedFeatures) actual).getNumberOfPages();
//            }
//        }
//        return sum;
    }
}
