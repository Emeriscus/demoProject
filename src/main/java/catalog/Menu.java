package catalog;

import java.io.IOException;
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
        System.out.println("6. List all library items by title");
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
                    addLibraryItem(scanner.nextLine());
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
                    System.out.println("This feature is under development!");
                    System.out.println("Please press Enter and choose another number!");
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
                    System.out.println("This feature is under development!");
                    System.out.println("Please press Enter and choose another number!");
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("This feature is under development!");
                    System.out.println("Please press Enter and choose another number!");
                    scanner.nextLine();
                    break;
                case 7:
                    System.out.println("This feature is under development!");
                    System.out.println("Please press Enter and choose another number!");
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.println("Please type the title: ");
                    libraryService.borrowLibraryItemByTitle(scanner.nextLine());
                    System.out.println();
                    //  System.out.println("Borrow this item? (y/n) ");
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

    private void addLibraryItem(String ItemType) {
        if (ItemType.equals("Book")) {
            System.out.println("title ");
            String title = scanner.nextLine();
            System.out.println("authors separated ', ' ");
            List<String> authors = new ArrayList<>(StringListConverters.StringToList(scanner.nextLine()));
            System.out.println("year ");
            int yearOfProduction = scanner.nextInt();
            System.out.println("quantity ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Book book = new Book(title, authors, yearOfProduction, quantity);
            libraryService.addLibraryItem(book);
            System.out.println("Add item was successful!");
            System.out.println("Continue with Enter");
            scanner.nextLine();
        }
        if (ItemType.equals("Audio")) {
            System.out.println("title ");
            String title = scanner.nextLine();
            System.out.println("performers separated ', ' ");
            List<String> performers = new ArrayList<>(StringListConverters.StringToList(scanner.nextLine()));
            System.out.println("composers separated ', ' ");
            List<String> composers = new ArrayList<>(StringListConverters.StringToList(scanner.nextLine()));
            System.out.println("year ");
            int yearOfProduction = scanner.nextInt();
            System.out.println("quantity ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Audio audio = new Audio(title, performers, composers, yearOfProduction, quantity);
            libraryService.addLibraryItem(audio);
            System.out.println("Add item was successful!");
            System.out.println("Continue with Enter");
            scanner.nextLine();
        }
    }

//    public static void clearScreen() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }

//    public static void clearConsole() {
//        try {
//            if (System.getProperty("os.name").contains("Windows")) {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            }
//            else {
//                System.out.print("\033\143");
//            }
//        } catch (IOException | InterruptedException ex) {
//            throw new IllegalStateException("Error during screen clean", ex);
//        }
//    }

//    public static void clearConsole() {
//        try {
//            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
//
//            if (operatingSystem.contains("Windows")) {
//                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
//                Process startProcess = pb.inheritIO().start();
//                ;
//                startProcess.waitFor();
//            } else {
//                ProcessBuilder pb = new ProcessBuilder("clear");
//                Process startProcess = pb.inheritIO().start();
//
//                startProcess.waitFor();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
