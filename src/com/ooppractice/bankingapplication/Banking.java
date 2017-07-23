package com.ooppractice.bankingapplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Banking {

	static double AccNumber;

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		Utility utl = new Utility();
		UserOptions uo = new UserOptions();

		Scanner input = new Scanner(System.in);

		String userDecision = "";

		while (!userDecision.equalsIgnoreCase("n")) {
			System.out.println("Enter the Account number");
			AccNumber = input.nextInt();

			System.out.println("Checking the details from database......\n");

			Thread.sleep(1000);
			new Thread() {
				public void run() {
					try {
						utl.getDetails(AccNumber);

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}.start();

			Thread.sleep(1000);
			uo.chooseOptions(utl);

			System.out.println("Do you want to continue? Y/N");
			userDecision = input.next();

		}
		input.close();
	}
}
