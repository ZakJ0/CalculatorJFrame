package com.example.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton clearButton, percentageButton, divideButton, commaButton, deleteButton, equalsButton;
    private JPanel buttonPanel;

    private double num1 = 0, num2 = 0;
    private char operator = ' ';

    public Calculator() {
        setTitle("Custom Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); // Added spacing between buttons

        // First row buttons: C, %, Comma, Divide
        buttonPanel.add(createButton("C"));
        buttonPanel.add(createButton("%"));
        buttonPanel.add(createButton(","));
        buttonPanel.add(createButton("/"));

        // Second row buttons: 7, 8, 9, Multiply
        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(createButton(Integer.toString(i)));
        }
        buttonPanel.add(createButton("*"));

        // Third row buttons: 4, 5, 6, Subtract
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(createButton(Integer.toString(i)));
        }
        buttonPanel.add(createButton("-"));

        // Fourth row buttons: 1, 2, 3, Add
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(createButton(Integer.toString(i)));
        }
        buttonPanel.add(createButton("+"));

        // Fifth row buttons: 0, Comma, Delete, Equals
        buttonPanel.add(createButton("0"));
        buttonPanel.add(createButton("."));
        buttonPanel.add(createButton("DEL"));
        buttonPanel.add(createButton("="));

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Character.isDigit(command.charAt(0))) {
            display.setText(display.getText() + command);
        } else if (command.equals(",")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = calculateResult(num1, num2, operator);
            display.setText(Double.toString(result));
        } else if (command.equals("C")) {
            display.setText("");
        } else if (command.equals("DEL")) {
            String currentText = display.getText();
            if (currentText.length() > 0) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (command.equals("%")) {
            num1 = Double.parseDouble(display.getText());
            double result = num1 / 100;
            display.setText(Double.toString(result));
        }
    }

    private double calculateResult(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0)
                    return num1 / num2;
                else {
                    JOptionPane.showMessageDialog(this, "Error! Division by zero.");
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
