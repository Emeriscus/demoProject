package catalog.controller;

import catalog.service.LibraryService;
import catalog.utils.Validators;

import java.util.Scanner;

public class AdminMainMenu {

    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private MenuItems menuItems;

    public AdminMainMenu(LibraryService libraryService) {
        menuItems = new MenuItems(libraryService);
    }

    public void runMenu() {
        do {
            printMenu();
            switch (choice) {
                case 1:
                    menuItems.runMenuItemOne();
                    choice = 0;
                    break;
                case 2:
                    menuItems.runMenuItemTwo();
                    choice = 0;
                    break;
                case 3:
                    menuItems.runMenuItemThree();
                    choice = 0;
                    break;
                case 4:
                    menuItems.runMenuItemFour();
                    choice = 0;
                    break;
                case 5:
                    menuItems.runMenuItemFive();
                    choice = 0;
                    break;
                case 6:
                    menuItems.runMenuItemSix();
                    choice = 0;
                    break;
                case 7:
                    menuItems.runMenuItemSeven();
                    choice = 0;
                    break;
                case 8:
                    menuItems.runMenuItemEight();
                    choice = 0;
                    break;
                case 9:
                default:
                    break;
            }
        } while (choice != 9);
    }

    public void printMenu() {
        System.out.println();
        System.out.println("--------------Welcome to the Library Catalog Project Admin Menu--------------");
        System.out.println();
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
        } else {
            System.out.println("Not a number! Please press Enter and choose a number!");
            scanner.nextLine();
        }
    }
}
