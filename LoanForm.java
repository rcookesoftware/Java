import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanForm extends JFrame {

    public LoanForm() {
        // Window title
        setTitle("Loan Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        // Inputs
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 25));
        
        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(50, 25));
        
        JTextField amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(100, 25));
        

        String[] loanTypes = {"Car Loan", "Home Loan", "Personal Loan"};
        JComboBox<String> loanTypeBox = new JComboBox<>(loanTypes);

        JButton calculateBtn = new JButton("Calculate");

        JLabel resultLabel = new JLabel("Monthly Payment: ");

        // Add everything to the window
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Loan Amount:"));
        add(amountField);
        add(new JLabel("Loan Type:"));
        add(loanTypeBox);
        add(new JLabel()); // empty cell
        add(calculateBtn);
        add(resultLabel);
        add(new JLabel()); // filler

        // Button logic
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float amount = Float.parseFloat(amountField.getText());
                    float rate = 6.0f; // just a fixed example rate
                    float monthlyRate = (rate / 100f) / 12f;
                    int months = 60; // fixed 5 years

                    float payment = (amount * monthlyRate) / (1 - (float)Math.pow(1 + monthlyRate, -months));
                    resultLabel.setText(String.format("Monthly Payment: €%.2f", payment));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoanForm();
    }
}

