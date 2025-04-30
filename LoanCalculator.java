import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoanCalculator {
    public static String input, name, address, ppsn;
    public static int age;
    public static String RECORDS_FILE = "records.txt";
    public static boolean member;
    public static float amount, monthlyRate, years, monthlyPayment;

    public static void main(String[] args) {
        if (isMember()) {
            onRecords();
        } else {
            createRecord();
        }
    }

    public static boolean isMember() {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE));
            ppsn = JOptionPane.showInputDialog("PPS Number: ");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(ppsn)) {
                    member = true;
                    break;
                }
            }

            return member;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error accessing file", "File Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void onRecords() {
        try {
            String line;
            String[] parts;
            BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE));

            while ((line = reader.readLine()) != null) {
                parts = line.split(",");
                if (parts[0].equals(ppsn)) {
                    name = parts[1];
                    age = Integer.parseInt(parts[2].trim());
                    address = parts[3];

                    JOptionPane.showMessageDialog(null, "Name: " + name + "\nAge: " + age + "\nAddress: " + address,
                            "Are these your details?", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            assignRate();
            loanApply();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error accessing file", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void createRecord() {
        try {
            name = JOptionPane.showInputDialog("Please enter your name");
    
            try {
                input = JOptionPane.showInputDialog("Please enter your age");
                age = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid entry for age", "Invalid Age", JOptionPane.ERROR_MESSAGE);
                return; // stop if age is invalid
            }
    
            address = JOptionPane.showInputDialog("Please enter your address");
            // Don't ask for PPSN again – it's already set in isMember()
    
            String record = ppsn + "," + name + "," + age + "," + address;
    
            FileWriter writer = new FileWriter(RECORDS_FILE, true); // true = append
            writer.write("\n" + record);
            writer.close();
    
            JOptionPane.showMessageDialog(null, "Record created successfully!");
            assignRate();
            loanApply();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving record", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void loanApply(){
        String[] options = {"Car Loan", "Home Loan", "Personal Loan"};
        String loanType = (String) JOptionPane.showInputDialog(
            null,
            "Choose a loan type:",
            "Loan Type",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (loanType) {
            case "Car Loan":
                carLoan();
                break;
            case "Home Loan":
                homeLoan();
                break;
            case "Personal Loan":
                personalLoan();
                break;
        }        
    }

    public static void carLoan(){
        try{
            input = JOptionPane.showInputDialog("Please enter the amount");
            amount = Float.parseFloat(input);
            input = JOptionPane.showInputDialog("Please enter the duration of loan (in years)");
            years = Float.parseFloat(input);
            float months = years * 12;

            monthlyPayment = (amount * monthlyRate) / (1 - (float)Math.pow(1 + monthlyRate, -months));
            String formattedPayment = String.format("€%.2f", monthlyPayment);
            JOptionPane.showMessageDialog(null, "Monthly Payment: " + formattedPayment, "Monthly Payment", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid Entry", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void homeLoan(){
        try{
            input = JOptionPane.showInputDialog("Please enter the amount");
            amount = Float.parseFloat(input);
            input = JOptionPane.showInputDialog("Please enter the duration of loan (in years)");
            years = Float.parseFloat(input);
            float months = years * 12;

            monthlyPayment = (amount * monthlyRate) / (1 - (float)Math.pow(1 + monthlyRate, -months));
            String formattedPayment = String.format("€%.2f", monthlyPayment);
            JOptionPane.showMessageDialog(null, "Monthly Payment: " + formattedPayment, "Monthly Payment", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid Entry", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void personalLoan(){
        try{
            input = JOptionPane.showInputDialog("Please enter the amount");
            amount = Float.parseFloat(input);
            input = JOptionPane.showInputDialog("Please enter the duration of loan (in years)");
            years = Float.parseFloat(input);
            float months = years * 12;

            monthlyPayment = (amount * monthlyRate) / (1 - (float)Math.pow(1 + monthlyRate, -months));
            String formattedPayment = String.format("€%.2f", monthlyPayment);
            JOptionPane.showMessageDialog(null, "Monthly Payment: " + formattedPayment, "Monthly Payment", JOptionPane.INFORMATION_MESSAGE);            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid Entry", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void assignRate() {
        if (age < 25) {
            monthlyRate = 7.5f;
        } else if (age < 60) {
            monthlyRate = 5.5f;
        } else {
            monthlyRate = 6.0f;
        }
        monthlyRate = (monthlyRate / 100f) / 12f;
    }
}
