package catalog.controller;

import catalog.service.LibraryService;
import catalog.utils.Validators;

import java.util.Scanner;

public class UserMainMenu {

    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private MenuItems menuItems;

    public UserMainMenu(LibraryService libraryService) {
        menuItems = new MenuItems(libraryService);
    }

    public void runMenu() {
        do {
            printMenu();
            switch (choice) {
                case 1:
                    menuItems.runMenuItemTwo();
                    choice = 0;
                    break;
                case 2:
                    menuItems.runMenuItemThree();
                    choice = 0;
                    break;
                case 3:
                    menuItems.runMenuItemSix();
                    choice = 0;
                    break;
                case 4:
                    menuItems.runMenuItemSeven();
                    choice = 0;
                    break;
                case 5:
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
        System.out.println("--------------Welcome to the Library Catalog Project User Menu--------------");
        System.out.println();
        System.out.println("1. Find library item by title");
        System.out.println("2. Find library item by ID");
        System.out.println("3. List all library item");
        System.out.println("4. List all library items by title fragment");
        System.out.println("5. Borrowing a library item");
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
