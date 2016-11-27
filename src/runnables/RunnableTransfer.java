package runnables;

import bank_accounts.Account;

public class RunnableTransfer implements Runnable {
	private static final int DELAY = 1;
	private Account account;
	private Account recipient;
	private double value;

	public RunnableTransfer(Account a, Account r, double v) {
		account = a;
		recipient = r;
		value = v;
	}

	public void run() {
		try {
			account.transfer(value, recipient);
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			System.out.println("Thread with ID " + Thread.currentThread().getId()
					+ " (TR): There are no impending balance-increasing operations, the thread has timed out.");
		}

	}
}
