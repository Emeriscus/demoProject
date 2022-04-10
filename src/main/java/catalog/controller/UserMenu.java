package catalog.controller;

import catalog.service.LibraryService;
import catalog.service.UserService;
import catalog.utils.Validators;

import java.util.Scanner;

public class UserMenu {

    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private UserService userService;

    public void runUserMenu() {
        do {
            printUserMenu();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 9:
                default:
                    break;
            }
        } while (choice != 9);
    }

    public void printUserMenu() {
        System.out.println("----------------Welcome to the Library Catalog Projekt----------------");
        System.out.println();
        System.out.println("Please login or register!");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Register");
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


    private void login() {
        System.out.println();
        boolean isValidEmail = false;
        while (isValidEmail) {
            System.out.println("Please type the username and press Enter");
            String name = scanner.nextLine();
            isValidEmail = userService.isExistingUser(name);
        }


    }

    private void register() {
    }


}

