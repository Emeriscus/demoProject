package catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog {

    public List<CatalogItem> catalogItems = new ArrayList<>();

    public void addItem(CatalogItem catalogItem) {
        catalogItems.add(catalogItem);
    }

    public double averagePageNumberOver(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Page number must be positive");
        }
        int sum = catalogItems.stream()
                .mapToInt(catalogItem -> catalogItem.numberOfPagesAtOneItem())
                .filter(o -> o > limit)
                .sum();

        long count = catalogItems.stream()
                .mapToInt(catalogItem -> catalogItem.numberOfPagesAtOneItem())
                .filter(o -> o > limit)
                .count();
        if (count == 0) {
            throw new IllegalArgumentException("No page");
        }
        return sum / (double) count;
    }

    public void deleteItemByRegistrationNumber(String regNumber) {
        catalogItems.removeIf(catalogItem -> catalogItem.getRegistrationNumber() == regNumber);
    }

    public List<CatalogItem> findByCriteria(SearchCriteria searchCriteria) {

        if (searchCriteria.hasTitle() && !searchCriteria.hasContributor()) {
            return catalogItems.stream()
                    .filter(catalogItem -> catalogItem.getTitles().contains(searchCriteria.getTitle()))
                    .toList();
        } else {
            if (!searchCriteria.hasTitle() && searchCriteria.hasContributor()) {
                return catalogItems.stream()
                        .filter(catalogItem -> catalogItem.getContributors().contains(searchCriteria.getContributor()))
                        .toList();
            } else {
                return catalogItems.stream()
                        .filter(catalogItem -> catalogItem.getTitles().contains(searchCriteria.getTitle())
                                && catalogItem.getContributors().contains(searchCriteria.getContributor()))
                        .toList();
            }

//        List<CatalogItem> result = new ArrayList<>();
//        for (CatalogItem actual : catalogItems) {
//            if (actual.getTitles().contains(searchCriteria.getTitle())) {
//                result.add(actual);
//            }
//        }
//        return result;
        }
    }

    public int getAllPageNumber() {
        return catalogItems.stream()
                .mapToInt(o -> o.numberOfPagesAtOneItem())
                .sum();
    }

    public List<CatalogItem> getAudioLibraryItems() {
        return catalogItems.stream()
                .filter(catalogItem -> catalogItem.hasAudioFeature())
                .toList();
    }

    public int getFullLength() {
        return catalogItems.stream()
                .mapToInt(o -> o.fullLengthAtOneItem())
                .sum();
    }

    public List<CatalogItem> getPrintedLibraryItems() {
        return catalogItems.stream()
                .filter(catalogItem -> catalogItem.hasPrintedFeature())
                .toList();
    }
}
