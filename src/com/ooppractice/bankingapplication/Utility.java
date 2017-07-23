package com.ooppractice.bankingapplication;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utility {

	Banking b;
	UserOptions o = new UserOptions();
	WriteTransactionsToFile tf;
	SavingsAccount sacc;
	SavingsAccount sacc1;

	// Use util.Calendar to get the date and time.
	Calendar calendar = Calendar.getInstance();

	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

	/*
	 * Create ArrayList of type WriteTransactionsToFile class, to store the
	 * Transactions from database
	 */
	List<WriteTransactionsToFile> writeTransToFileList = new ArrayList<>();

	/*
	 * Use PrintWriter and BufferedWriter class to write Transactions
	 * details/history to a file
	 */
	PrintWriter pw;
	BufferedWriter bw;

	// Database Connection
	ConnectionToDatabase connToDatabase = new ConnectionToDatabase();

	Connection conn;

	PreparedStatement pst;
	PreparedStatement pst1;
	PreparedStatement pst2;
	PreparedStatement pst3;

	ResultSet rs;
	ResultSet rs1;
	ResultSet rs3;

	/**
	 * 
	 * @param AccNumber
	 *            Here, this method accepts two Account Numbers (from Banking
	 *            and UserOptions class). So, to get the details there are some
	 *            conditions inside this method where the logic is different.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void getDetails(double AccNumber) throws ClassNotFoundException, SQLException {

		if (AccNumber == Banking.AccNumber) {
			String select1 = "select * from Account where accountnumber = ?";

			conn = connToDatabase.getConnection();
			System.out.println("Connected to database successfully!!! \n");

			pst = conn.prepareStatement(select1);

			pst.setDouble(1, AccNumber);
			rs = pst.executeQuery();

			while (rs.next()) {
				sacc = new SavingsAccount(rs.getDouble(1), rs.getString(2), rs.getDouble(3));
			}
		} else {

			String select1 = "select * from Account where accountnumber = ?";
			pst1 = conn.prepareStatement(select1);

			pst1.setDouble(1, AccNumber);
			rs1 = pst1.executeQuery();

			while (rs1.next()) {
				sacc1 = new SavingsAccount(rs1.getDouble(1), rs1.getString(2), rs1.getDouble(3));
			}
		}
	}

	/**
	 * 
	 * @param AccNumber
	 *            Here, this method accepts two Account Numbers (from Banking
	 *            and UserOptions class). So, to set the details there are some
	 *            conditions inside this method where the logic is different.
	 * @throws SQLException
	 */
	public void setDetails(double AccNumber) throws SQLException {

		if (AccNumber == Banking.AccNumber) {

			double newBalance = sacc.getBalance();

			String update_tbl = "update Account set Balance = ? where accountnumber = ?";

			pst1 = conn.prepareStatement(update_tbl);

			pst1.setDouble(1, newBalance);
			pst1.setDouble(2, AccNumber);

			pst1.executeUpdate();
		} else {
			double newBalance = sacc1.getBalance();

			String update_tbl = "update Account set Balance = ? where accountnumber = ?";

			pst1 = conn.prepareStatement(update_tbl);

			pst1.setDouble(1, newBalance);
			pst1.setDouble(2, AccNumber);

			pst1.executeUpdate();

		}

		/*
		 * Insert the details every time in the Transaction table when updating
		 * the Account table.
		 */
		this.insertDetails(AccNumber);

		/*
		 * if (AccNumber == Banking.AccNumber) { this.insertDetails(AccNumber);
		 * 
		 */
	}

	/**
	 * 
	 * @param AccNumber
	 *            Two types of Account Numbers. One is from Banking class and
	 *            the other one is the new Account Number that comes from the
	 *            new object created at User Option "5".
	 * @throws SQLException
	 */
	public void insertDetails(double AccNumber) throws SQLException {

		String insert_tbl = "insert into Transaction (accnumber, amount, type, dateandtime, totalbalance) values (?,?,?,?,?)";

		pst2 = conn.prepareStatement(insert_tbl);

		pst2.setDouble(1, AccNumber);
		pst2.setString(3, o.transactionType());
		pst2.setTimestamp(4, currentTimestamp);

		if (AccNumber == Banking.AccNumber) {
			pst2.setDouble(2, sacc.getAmount1());
			pst2.setDouble(5, sacc.getBalance());
		} else {
			pst2.setDouble(2, sacc1.getAmount1());
			pst2.setDouble(5, sacc1.getBalance());
		}

		pst2.executeQuery();
	}

	/*
	 * Execute its operations when user choice is "6" and at the same time it
	 * writes the transaction details in a file
	 */
	public void transactionDetails(double AccountNumber) throws SQLException, FileNotFoundException {

		String select_tbl = "select * from transaction where accnumber = ?";

		pst3 = conn.prepareStatement(select_tbl);

		pst3.setDouble(1, AccountNumber);

		rs3 = pst3.executeQuery();

		while (rs3.next()) {
			System.out.println("ID: " + rs3.getDouble(1) + "\tAccount Nmuber: " + rs3.getDouble(2)
					+ "\tTransaction Amount: " + rs3.getDouble(3) + "\tTransaction Type: " + rs3.getString(4)
					+ "\tDate: " + rs3.getTimestamp(5) + "\tTotal Balance: " + rs3.getDouble(6));
			tf = new WriteTransactionsToFile(rs3.getDouble(1), rs3.getDouble(3), rs3.getString(4), rs3.getTimestamp(5),
					rs3.getDouble(6));
			writeTransToFileList.add(tf);

			PrintWriter pw = new PrintWriter(new FileOutputStream("D:\\java IO\\writeTransaction.txt"));

			for (WriteTransactionsToFile t : writeTransToFileList)
				pw.println(t.toString());
			pw.close();

		}

	}

	public void CloseConnection() throws SQLException {
		conn.close();
	}
}
