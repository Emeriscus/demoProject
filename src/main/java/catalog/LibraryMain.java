package catalog;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;

public class LibraryMain {

    public static void main(String[] args) {

        MariaDbDataSource dataSource = new MariaDbDataSource();

        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/library-catalog?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot reach database", se);
        }

        Flyway flyway = Flyway.configure().locations("db/migration").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        AudioRepository audioRepository = new AudioRepository(dataSource);
        BookRepository bookRepository = new BookRepository(dataSource);
        LibraryItemRepository libraryItemRepository = new LibraryItemRepository(dataSource);

        LibraryService libraryService = new LibraryService(bookRepository, audioRepository, libraryItemRepository);
        Menu menu = new Menu(libraryService);

        libraryService.addLibraryItem(
                (new Book("Conan a barbár", List.of("Robert E. Howard"), 1932, 3)));

        libraryService.addLibraryItem(
                (new Audio("Illegális bál", List.of("Aurora"), List.of("Vigi, Pusztai Zoltán"), 1997, 4)));

        libraryService.addLibraryItem(
                (new Book("A tűzhegy varázslója", List.of("Steve Jackson", "Ian Livingstone"), 1982, 6)));

        libraryService.addLibraryItem(
                (new Audio("Viperagarzon", List.of("Másfél"), List.of("Másfél"), 1996, 1)));

        libraryService.addLibraryItem(
                (new Book("Tüskevár", List.of("Fekete István"), 1957, 3)));

        libraryService.addLibraryItem(
                (new Audio("A kopaszkutya", List.of("Deák Bill Gyula", "Földes László"), List.of("Földes László"), 1993, 2)));

//        System.out.println(bookRepository.getBookById(3));
//        System.out.println();
//
//        System.out.println(bookRepository.getTitleById(2));
//        System.out.println();
//
//        System.out.println(libraryItemRepository.getLibraryItemIdByTitle("Tüskevár"));
//        System.out.println();
//
//        System.out.println(libraryItemRepository.getLibraryItemTypeById(5));
//        System.out.println();
//
//        System.out.println(libraryService.getLibraryItemByTitle("A tűzhegy varázslója"));
//        System.out.println(libraryService.getLibraryItemByTitle("A kopaszkutya"));


        menu.runMenu();
    }
}