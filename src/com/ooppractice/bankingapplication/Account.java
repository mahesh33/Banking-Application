package com.ooppractice.bankingapplication;

public abstract class Account {

	private double balance;
	protected double interest;
	private double amount1;

	public Account(double balance) {
		super();
		this.balance = balance;
	}

	public void deposit(double Amount) {
		this.setAmount1(Amount);
		balance = balance + Amount;
	}

	public void checkBalance() {
		System.out.println("The current balance is:: " + balance + "\n");
	}

	public void withdrawl(double Amount) {
		this.setAmount1(Amount);
		balance -= Amount;
	}

	abstract void interest(double Amount, double year);

	public double getBalance() {
		return balance;
	}

	public double getInterest() {
		return interest;
	}

	public double getAmount1() {
		return amount1;
	}

	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}

}
