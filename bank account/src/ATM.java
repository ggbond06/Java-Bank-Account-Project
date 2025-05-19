import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {

    private final ArrayList<Account> accounts = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    private Account loginAcc;

    public void start() {
        while(true){
            System.out.println("===Welcome to ATM system===");
            System.out.println("1. Log in");
            System.out.println("2. Create an account");
            System.out.print("Enter your option: ");
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    logIn();
                    break;

                case 2:
                    createAccount();
                    break;

                default:
                    System.out.println("Invalid option.");
        }
        }
    }

    private void showUserCommand(){
        while(true){
            System.out.println("===" + loginAcc.getUserName() + ", you can choose the following functions to proceed===");
            System.out.println("1. Check Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");
            System.out.println("7. Delete Current Account");

            int commend = scanner.nextInt();

            switch (commend){
                case 1:
                    showLoginAccount();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println(loginAcc.getUserName() + " You have successfully logged out");
                    return;
                case 7:
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private void showLoginAccount(){
        System.out.println("===Your current account information: ===");
        System.out.println("Card ID: " + loginAcc.getCardId());
        System.out.println("Card Holder: " + loginAcc.getUserName());
        System.out.println("Balance: " + loginAcc.getMoney());
        System.out.println("Cash Withdraw Limit: " + loginAcc.getLimit());
    }

    private void logIn(){
        System.out.println("===Log in===");
        if (accounts.size() == 0){
            System.out.println("Current system has no account.");
            return;
        }
        while(true){
            System.out.print("Please enter your card ID: ");
            String ID = scanner.next();
            Account acc = checkCardID(ID);

            if (acc == null){
                System.out.println("Invalid card ID.");
            } else{
                while(true){
                    System.out.print("Please enter your password: ");
                    String pass = scanner.next();
                    if (acc.getPassWord().equals(pass)){
                        loginAcc = acc;
                        System.out.println(acc.getUserName() + ", you've successfully logged in. Your card number is: " + acc.getCardId());
                        showUserCommand();
                        return; // exit the login menu
                    }else{
                        System.out.println("Incorrect password.");
                    }
                }
            }
        }
    }

    private void createAccount(){
        Account acc = new Account();

        System.out.print("Please enter your user name: ");
        String name = scanner.next();
        acc.setUserName(name);

        while(true){
            System.out.print("Please enter your sex: ");
            char sex = scanner.next().toLowerCase().charAt(0);
            if (sex == 'm' || sex == 'f'){
                acc.setSex(sex);
                break;
            }
            else{
                System.out.println("Invalid sex. Please enter m or f");
            }
        }

        while(true){
            System.out.print("Please enter your account password: ");
            String password = scanner.next();
            System.out.print("Please confirm your account password: ");
            String okPassword = scanner.next();
            if (password.equals(okPassword)){
                acc.setPassWord(okPassword);
                break;
            }
            else{
                System.out.println("The passwords you've entered don't match.");
                System.out.println("Please re-enter your password.");
            }
        }

        System.out.print("Please enter the amount you want to withdraw: ");
        double amount = scanner.nextDouble();
        acc.setLimit(amount);

        String newCardID = createCardID();
        acc.setCardId(newCardID);

        accounts.add(acc);

        System.out.println(acc.getUserName() + ", you've successfully opened an account!");
        System.out.println("Your Card Number is: " + newCardID);

    }

    private String createCardID(){
        while(true){
            String cardID = "";
            Random random = new Random();
            for (int i = 0; i < 8; i++){
                int number = random.nextInt(10);
                cardID += number;
            }
            if (checkCardID(cardID) == null){
                return cardID;
            }
        }
    }

    private Account checkCardID(String cardID){
        for (int i = 0; i < accounts.size(); i++){
            Account acc = accounts.get(i);
            if (acc.getCardId().equals(cardID)){
                return acc;
            }
        }
        return null;
    }
}
