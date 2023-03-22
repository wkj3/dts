package login;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        String username;
        String password;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your username: ");
        username = input.nextLine();

        System.out.print("Enter your password: ");
        password = input.nextLine();

        DBConnect db = new DBConnect();
        if (db.authenticateUser(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password!");
        }
    }
}