package catalog.controller;

import catalog.classes.User;
import catalog.service.LibraryService;
import catalog.service.UserService;
import catalog.utils.PasswordGenerator;
import catalog.utils.Validators;

import java.util.Scanner;

public class LoginMenu {

    private AdminMainMenu adminMainMenu;
    private UserMainMenu userMainMenu;
    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private UserService userService;

    public LoginMenu(UserService userService, LibraryService libraryService) {
        this.userService = userService;
        this.adminMainMenu = new AdminMainMenu(libraryService);
        this.userMainMenu = new UserMainMenu(libraryService);
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
        if (userService.isExistingUser(email) && isCorrectPassword(email)) {
            if (userService.HasAdminRightByEmail(email)) {
                adminMainMenu.runMenu();
            } else {
                userMainMenu.runMenu();
            }
            return;
        }
        choice = 0;
        System.out.println("Press Enter and choose another e-mail!");
        scanner.nextLine();
    }

    private boolean isCorrectPassword(String email) {
        boolean isValidPassword = false;
        int count = 0;
        while (!isValidPassword && count < 3) {
            System.out.println("Please type the password and press Enter");
            String password = scanner.nextLine();
            String storedPassword = userService.getStoredPasswordByEmail(email);
            String salt = userService.getStoredSaltByEmail(email);
            isValidPassword = PasswordGenerator.verifyUserPassword(password, storedPassword, salt);
            count++;
        }
        return isValidPassword;
    }

    private void register() {
        System.out.println();
        System.out.println("Please type the e-mail and press Enter: ");
        String email = scanner.nextLine();
        if (Validators.isValidEmail(email)) {
            checkEmailAndPassword(email);
        } else {
            System.out.println("Not valid email address. Please Press Enter!");
            scanner.nextLine();
        }
        choice = 0;
    }

    private void checkEmailAndPassword(String email) {
        if (userService.isEmailTaken(email)) {
            System.out.println("Press Enter and choose another e-mail!");
            scanner.nextLine();
        } else {
            System.out.println("Please type the password and press Enter: ");
            String password = scanner.nextLine();
            boolean isValidPassword = Validators.isValidPassword(password);
            while (!isValidPassword) {
                System.out.println("The password must contains at least 8 and at most 20 characters!");
                System.out.println("The password must contains at least one digit," +
                        " one upper case alphabet, one lower case alphabet and one special character!");
                System.out.println("Please type again the password and press Enter: ");
                password = scanner.nextLine();
                isValidPassword = Validators.isValidPassword(password);
            }
            saveEmailAndPassword(email, password);
        }
    }

    private void saveEmailAndPassword(String email, String password) {
        String salt = PasswordGenerator.getSalt();
        String securePassword = PasswordGenerator.generateSecurePassword(password, salt);
        userService.addUser(new User(email, salt, securePassword));
        System.out.println("The registration was successful! Please press Enter!");
        scanner.nextLine();
    }
}

