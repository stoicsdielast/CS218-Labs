import java.util.Scanner;
import java.text.DecimalFormat;
/*
A program to calculate an employee's gross pay, superannuation deduction,and net pay, including time-and-
a-half overtime for hours worked over 40.
*/
public class Lab2 {
    public static void main(String[] args) {
// Create a Scanner object to read user input from the console
        Scanner input = new Scanner(System.in);
// Create a DecimalFormat object to format output to two decimal places for currency
        DecimalFormat df = new DecimalFormat("#.00");
// --- Get user input ---
        System.out.println("--- Employee Payroll Calculator ---");
        System.out.print("Enter employee's First Name: ");
        String firstName = input.nextLine();
        System.out.print("Enter employee's Last Name: ");
        String lastName = input.nextLine();
        System.out.print("Enter hourly rate ($): ");
        double rate = input.nextDouble();
        System.out.print("Enter hours worked: ");
        double hours = input.nextDouble();
// Close the scanner to prevent resource leaks
        input.close();
// --- Calculation Logic ---
        final int OVERTIME_THRESHOLD = 40;
        final double DEDUCTION_RATE = 0.08;
        double grossPay;
        double deduction;
        double netPay;
        double overtimePay = 0;
// Check if hours worked is greater than 40 for overtime calculation
        if (hours > OVERTIME_THRESHOLD) {
// Calculate pay for the first 40 hours
            double normalPay = OVERTIME_THRESHOLD * rate;
// Calculate overtime hours and pay at 1.5 times the normal rate
            double overtimeHours = hours - OVERTIME_THRESHOLD;
            overtimePay = overtimeHours * (rate * 1.5);
// Total gross pay is the sum of normal pay and overtime pay
            grossPay = normalPay + overtimePay;
        } else {
// No overtime, so gross pay is a simple multiplication
            grossPay = hours * rate;
        }
        //tax deduction after gross pay
        final double TAX_RATE = 0.05;
        double tax = grossPay * TAX_RATE;

// Calculate the 8% superannuation deduction
        deduction = grossPay * DEDUCTION_RATE;
// Calculate the final net pay
        netPay = grossPay - deduction;
// --- Display Results ---
        System.out.println("\n--- Payment Summary for " + firstName + " " + lastName + " ---");
        System.out.println("Hourly Rate: $" + df.format(rate));
        System.out.println("Hours Worked: " + df.format(hours));
        System.out.println("Gross Pay: $" + df.format(grossPay));
// Display overtime pay only if there was any
        if (overtimePay > 0) {
            System.out.println("Overtime Pay: $" + df.format(overtimePay));
        }
        System.out.println("Superannuation Deduction (8%): $" + df.format(deduction));
        System.out.println("Tax Deduction (8%): " + df.format(tax));
        System.out.println("----------------------------------------");
        System.out.println("Net Pay: $" + df.format(netPay));
    }
}
