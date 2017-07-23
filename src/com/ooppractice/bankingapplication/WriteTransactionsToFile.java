package com.ooppractice.bankingapplication;

import java.sql.Timestamp;

public class WriteTransactionsToFile {

	private double id;
	private double amount;
	private String type;
	private Timestamp dateAndTime;
	private double totalBalance;

	/**
	 * 
	 * @param id
	 * @param amount
	 * @param type
	 * @param dateAndTime
	 * @param totalBalance
	 */
	public WriteTransactionsToFile(double id, double amount, String type, Timestamp dateAndTime, double totalBalance) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.dateAndTime = dateAndTime;
		this.totalBalance = totalBalance;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Timestamp dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	@Override
	public String toString() {
		return "TransactionsToFile [id=" + id + ", amount=" + amount + ", type=" + type + ", dateAndTime=" + dateAndTime
				+ ", totalBalance=" + totalBalance + "]";
	}

}
