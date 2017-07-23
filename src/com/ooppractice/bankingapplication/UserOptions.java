package com.ooppractice.bankingapplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserOptions {

	Scanner input = new Scanner(System.in);

	double amount;
	double newAccNumber;
	double amountToTransfer;
	static String choice;

	public void chooseOptions(Utility utl) throws SQLException, ClassNotFoundException, IOException {

		System.out.println("Please choose your option from below....\n");
		System.out.println(
				"\t 1) Cash Deposit \n\t 2) Cash Withdrawal \n\t 3) Check Balance \n\t 4) Fixed Deposit \n\t 5) Transfer Money \n\t 6) View Statement \n\t 7) Print Statement to File");
		choice = input.next();

		switch (choice) {

		case "1": {

			System.out.println("Enter the amount that you want to deposit");
			amount = input.nextDouble();

			utl.sacc.deposit(amount);
			System.out.println(utl.sacc);
			utl.setDetails(Banking.AccNumber);
			System.out.println("Amount Deposited \n");
			break;
		}
		case "2": {

			System.out.println("Enter the amount that you want to withdraw");
			amount = input.nextDouble();

			utl.sacc.withdrawl(amount);
			System.out.println(utl.sacc);
			utl.setDetails(Banking.AccNumber);
			System.out.println("Amount Withdrawl \n");
			break;
		}
		case "3": {
			utl.sacc.checkBalance();
			break;
		}
		case "4": {

			System.out.println("Enter the amount that you want to deposit");
			amount = input.nextDouble();

			System.out.println("Enter the year that you want to deposit your amount");
			double year = input.nextDouble();

			utl.sacc.interest(amount, year);
			utl.sacc.deposit(utl.sacc.getInterest());
			utl.setDetails(Banking.AccNumber);
			System.out.println("Fixed deposit completed.. \n");
			break;
		}
		case "5": {

			System.out.println("Enter the account number that you want to transfer the money");
			newAccNumber = input.nextDouble();

			System.out.println("Enter the amount that you want to transfer");
			amountToTransfer = input.nextDouble();

			if (newAccNumber == utl.sacc.getAccNumber()) {
				System.out.println(" * Please enter a different account number...to Transfer your Amount.. * \n");
			} else {
				utl.getDetails(newAccNumber);

				System.out.println("Before deposit.. " + utl.sacc1 + "\n");
				utl.sacc1.deposit(amountToTransfer);
				System.out.println("After deposit.. " + utl.sacc1 + "\n");

				System.out.println("Before withdraw.. " + utl.sacc + "\n");
				utl.sacc.withdrawl(amountToTransfer);
				System.out.println("After withdraw.. " + utl.sacc + "\n");

				utl.setDetails(newAccNumber);
				utl.setDetails(utl.sacc.getAccNumber());
				utl.CloseConnection();
				break;
			}
		}
		case "6": {
			utl.transactionDetails(Banking.AccNumber);
			break;
		}

		default:
			System.out.println("Please enter a valid option");
		}

	}

	public String transactionType() {
		if (choice.equals("1"))
			return "Deposit";
		if (choice.equals("2"))
			return "Withdrawal";
		if (choice.equals("4"))
			return "Fixed Deposit";
		if (choice.equals("5"))
			return "Transfer Money";

		return "Wrong Choice!!";

	}
}
