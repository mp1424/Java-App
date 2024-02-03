import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, result;
    private int operator;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating buttons
        textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(300, 100)); 
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4)); 
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                " ", " ", " ", "C" 
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "=":
                num2 = Double.parseDouble(textField.getText());
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                num1 = Double.parseDouble(textField.getText());
                operator = command.equals("+") ? 1 :
                           command.equals("-") ? 2 :
                           command.equals("*") ? 3 : 4;
                textField.setText("");
                break;
            case "C":  
                textField.setText("");
                break;
            default:
                textField.setText(textField.getText() + command);
        }
    }

    //calculate function
    private void calculate() {
        switch (operator) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    textField.setText("Error");
                    return;
                }
                break;
        }
        textField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp());
    }
}
