package com.ooppractice.bankingapplication;

public class SavingsAccount extends Account {

	private double AccNumber;
	private String User;

	public SavingsAccount(double AccNumber, String User, double Balance) {
		super(Balance);
		this.AccNumber = AccNumber;
		this.User = User;
	}

	public double getAccNumber() {
		return AccNumber;
	}

	public void setAccNumber(double accNumber) {
		AccNumber = accNumber;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	@Override
	public void interest(double Amount, double year) {

		if (year <= 2) {
			interest = (2 * Amount * year) / 100;
		} else if (year <= 5 && year > 2) {
			interest = (5 * Amount * year) / 100;
		} else {
			interest = (8 * Amount * year) / 100;
		}
	}

	@Override
	public String toString() {
		return "SavingsAccount [AccNumber=" + AccNumber + ", User=" + User + ", Amount=" + super.getAmount1() + ", Balance="
				+ super.getBalance() + "]";
	}

}