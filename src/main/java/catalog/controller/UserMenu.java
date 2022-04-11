package catalog.controller;

import catalog.classes.Admin;
import catalog.service.LibraryService;
import catalog.service.UserService;
import catalog.utils.PasswordUtils;
import catalog.utils.Validators;

import java.util.Scanner;

public class UserMenu {

    private Menu menu;
    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private UserService userService;

    public UserMenu(UserService userService, LibraryService libraryService) {
        this.userService = userService;
        this.menu = new Menu(libraryService);
    }

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
        System.out.println();
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
        System.out.println("Please type the email and press Enter");
        String email = scanner.nextLine();
        if (userService.isExistingUser(email)) {
            isValidPassword(email);
            menu.runMenu();
            return;
        }
        choice = 0;
        System.out.println("Press Enter and choose another e-mail!");
        scanner.nextLine();
    }

    private boolean isValidPassword(String email) {
        boolean isValidPassword = false;
        int count = 0;
        while (!isValidPassword && count < 3) {
            System.out.println("Please type the password and press Enter");
            String password = scanner.nextLine();
            String storedPassword = userService.getStoredPasswordByEmail(email);
            String salt = userService.getStoredSaltByEmail(email);
            isValidPassword = PasswordUtils.verifyUserPassword(password, storedPassword, salt);
            count++;
        }
        return isValidPassword;
    }

    private void register() {
        System.out.println();
        System.out.println("Please type the e-mail and press Enter: ");
        String email = scanner.nextLine();
        if (Validators.isValidEmail(email)) {
            if (userService.isEmailTaken(email)) {
                System.out.println("Press Enter and choose another e-mail!");
                scanner.nextLine();
            } else {
                System.out.println("Please type the password and press Enter: ");
                String password = scanner.nextLine();
                String salt = PasswordUtils.getSalt();
                String securePassword = PasswordUtils.generateSecurePassword(password, salt);
                userService.addUser(new Admin(email, salt, securePassword));
                System.out.println("The registration was successfull! Please press Enter!");
                scanner.nextLine();
            }
        } else {
            System.out.println("Not valid email address. Please Press Enter!");
            scanner.nextLine();
        }
        choice = 0;
    }
}

