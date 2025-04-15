import java.io.*;
import java.time.LocalDateTime;

import javax.swing.*;

public class ATM {
    static double balance = readBalance();
    static final String BALANCE_FILE = "balance.txt";
    static final String PIN_FILE = "pin.txt";
    static final String LOG_FILE = "log.txt";
    public static void main(String[] args) {
        String options[] = {"Lodge", "Withdraw", "Check Balance", "Change Pin", "Exit"};

        if (authenticate()) {
            balance = readBalance();
            boolean exit = false;

            while (!exit) {
                int choice = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "ATM Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                switch (choice) {
                    case 0: lodgeMoney(); break;
                    case 1: withdrawMoney(); break;
                    case 2: checkBalance(); break;
                    case 3: changePin(); break;
                    case 4:{
                        JOptionPane.showMessageDialog(null, "Exiting...");
                        exit = true;
                        break;
                    }
                    default: JOptionPane.showMessageDialog(null, "No option selected.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Authentication failed. Exiting...");
        }
    }

    static double readBalance(){
        File file = new File(BALANCE_FILE);
        try{
            if(!file.exists()){
                writeBalance(0.0);
                return 0.0;
            }else{
                BufferedReader reader = new BufferedReader(new FileReader(BALANCE_FILE));
                String line = reader.readLine();
                reader.close();
                return line != null ? Double.parseDouble(line):0.0;
            }
        }catch(IOException | NumberFormatException e){
            return 0.0;
        }
    }

    static void writeBalance(double balance){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCE_FILE))){
            writer.write(String.valueOf(balance));
            writer.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Saving Balance");
        }
    }

    static boolean authenticate() {
        File file = new File(PIN_FILE);
        try {
            if (!file.exists()) {
                while (true) {
                    String newPin = JOptionPane.showInputDialog(null, "Set your 4-digit PIN:", "Create PIN", JOptionPane.PLAIN_MESSAGE);
                    if (newPin == null) return false;  // User cancelled
                    if (newPin.matches("\\d{4}")) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(newPin);
                        writer.close();
                        JOptionPane.showMessageDialog(null, "PIN created successfully!");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN. Must be 4 digits.");
                    }
                }
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String storedPin = reader.readLine();
            reader.close();

            JPasswordField pf = new JPasswordField();
            int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter your PIN", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (okCxl == JOptionPane.OK_OPTION) {
                String enteredPin = new String(pf.getPassword());
                return enteredPin.equals(storedPin);
            } else {
                return false;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error accessing PIN file.");
            return false;
        }
    }

    static void lodgeMoney(){
        String input = JOptionPane.showInputDialog(null, "Enter amount to lodge", "Lodge Money", JOptionPane.PLAIN_MESSAGE);
        if(input != null)
        try{
            double amount = Double.parseDouble(input);
            if(amount <= 0){
                JOptionPane.showMessageDialog(null, "Please enter an amount greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                balance += amount;
                writeBalance(balance);
                logTransaction(String.valueOf("Lodged: " + balance));
                JOptionPane.showMessageDialog(null, "€" + amount + " lodged successfully\nNew Balance: " + balance, "Success", JOptionPane.PLAIN_MESSAGE);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Transaction Cancelled", "Cancelled", JOptionPane.WARNING_MESSAGE);
        }
    }

    static void withdrawMoney(){
        String input = JOptionPane.showInputDialog(null, "Enter amount to withdraw", "Withdraw Money", JOptionPane.PLAIN_MESSAGE);
        if(input != null)
        try{
            double amount = Double.parseDouble(input);
            if(amount > balance){
                JOptionPane.showMessageDialog(null, "Cannot enter an amount greater than balance", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                balance -= amount;
                writeBalance(balance);
                logTransaction(String.valueOf("Withdrew: " + balance));
                JOptionPane.showMessageDialog(null, "€" + amount + " withdrawn successfully\nNew Balance: " + balance, "Success", JOptionPane.PLAIN_MESSAGE);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Transaction Cancelled", "Cancelled", JOptionPane.WARNING_MESSAGE);
        }
    }

    static void checkBalance(){
        JOptionPane.showMessageDialog(null, "Current Balance: €" + balance);
    }

    static boolean changePin(){
        File file = new File(PIN_FILE);
        try{
            String newPin = JOptionPane.showInputDialog(null, "Set your 4-digit PIN:", "Create PIN", JOptionPane.PLAIN_MESSAGE);
            if (newPin == null) return false;  // User cancelled
                    if (newPin.matches("\\d{4}")) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(newPin);
                        writer.close();
                        JOptionPane.showMessageDialog(null, "PIN changed successfully!");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN. Must be 4 digits.");
                        return false;
                    }
        }catch(IOException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error accessing PIN file.");
            return false;
        }
    }

    static void logTransaction(String message){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))){
            String timeStamp = LocalDateTime.now().toString();
            writer.write("[" + timeStamp + "] " + message + "\n");
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to log transaction.");
        }
    }
}
