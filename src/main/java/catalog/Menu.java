package catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private int choice;
    private LibraryService libraryService;

    public Menu(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void printMenu() {
        System.out.println("1. Add library item");
        System.out.println("2. Find library item by title");
        System.out.println("3. Find library item by ID");
        System.out.println("4. Delete library item by title");
        System.out.println("5. Delete library item by ID");
        System.out.println("6. List all library item");
        System.out.println("7. List all library items by title fragment");
        System.out.println("8. Borrowing a library item");
        System.out.println("9. Exit");
        System.out.print("Please choose a number and press Enter: ");
        String input = scanner.nextLine();
        if (Validators.isNumber(input)) {
            choice = Integer.parseInt(input);
        }
    }

    public void runMenu() {
        do {
            printMenu();
            switch (choice) {
                case 1:
                    System.out.println("What type of library item would you like add? (Book, Audio) ");
                    String input = scanner.nextLine();
                    if (Validators.isBlank(input)) {
                        throw new IllegalArgumentException("The type cannot be empty");
                    }
                    if (!input.equals("Book") && !input.equals("Audio")) {
                        throw new IllegalArgumentException("The type must be Book or Audio");
                    }
                    addLibraryItem(input);
                    break;
                case 2:
                    System.out.print("Please type the title to search for: ");
                    System.out.println();
                    System.out.println(libraryService.getLibraryItemByTitle(scanner.nextLine()));
                    System.out.println();
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Please type the ID to search for: ");
                    System.out.println();
                    input = scanner.nextLine();
                    if (Validators.isNumber(input)) {
                        System.out.println(libraryService.getLibraryItemById(Integer.parseInt(input)));
                    }
                    System.out.println();
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Please type the title: ");
                    System.out.println();
                    libraryService.deleteLibraryItemByTitle(scanner.nextLine());
                    System.out.println();
                    System.out.println("The delete was successful");
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.print("Please type the id: ");
                    System.out.println();
                    input = scanner.nextLine();
                    if (Validators.isNumber(input)) {
                        libraryService.deleteLibraryItemById(Integer.parseInt(input));
                    }
                    System.out.println();
                    System.out.println("The delete was successful");
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println();
                    System.out.println(libraryService.getAllLibraryItem());
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 7:
                    System.out.print("Please type the title: ");
                    System.out.println();
                    System.out.println(libraryService.getAllLibraryItemByTitleFragment(scanner.nextLine()));
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.println("Please type the title: ");
                    libraryService.borrowLibraryItemByTitle(scanner.nextLine());
                    System.out.println();
                    System.out.println("The borrow was successful");
                    System.out.println("Continue with Enter");
                    scanner.nextLine();
                    break;
                case 9:
                default:
                    break;
            }
        } while (choice != 9);
    }

    private void addLibraryItem(String itemType) {
        if (itemType.equals("Book")) {
            System.out.println("title ");
            String title = scanner.nextLine();
            if (Validators.isBlank(title)) {
                throw new IllegalArgumentException("The title cannot be empty");
            }
            System.out.println("authors separated ', ' ");
            List<String> authors = new ArrayList<>(StringListConverters.stringToList(scanner.nextLine()));
            System.out.println("year ");
            int yearOfProduction = scanner.nextInt();
            if (!Validators.isValidYear(yearOfProduction)) {
                throw new IllegalArgumentException("The year must be between 1000 and the current year");
            }
            System.out.println("quantity ");
            int quantity = scanner.nextInt();
            if (!Validators.isValidQuantity(quantity)) {
                throw new IllegalArgumentException("The quantity must not above 0");
            }
            scanner.nextLine();
            Book book = new Book(title, authors, yearOfProduction, quantity);
            libraryService.addLibraryItem(book);
            System.out.println("Add item was successful!");
            System.out.println("Continue with Enter");
            scanner.nextLine();
        }
        if (itemType.equals("Audio")) {
            System.out.println("title ");
            String title = scanner.nextLine();
            System.out.println("performers separated ', ' ");
            List<String> performers = new ArrayList<>(StringListConverters.stringToList(scanner.nextLine()));
            System.out.println("composers separated ', ' ");
            List<String> composers = new ArrayList<>(StringListConverters.stringToList(scanner.nextLine()));
            System.out.println("year ");
            int yearOfProduction = scanner.nextInt();
            if (!Validators.isValidYear(yearOfProduction)) {
                throw new IllegalArgumentException("The year must be between 1000 and the current year");
            }
            System.out.println("quantity ");
            int quantity = scanner.nextInt();
            if (!Validators.isValidQuantity(quantity)) {
                throw new IllegalArgumentException("The quantity must not above 0");
            }
            scanner.nextLine();
            Audio audio = new Audio(title, performers, composers, yearOfProduction, quantity);
            libraryService.addLibraryItem(audio);
            System.out.println("Add item was successful!");
            System.out.println("Continue with Enter");
            scanner.nextLine();
        }
    }
}
