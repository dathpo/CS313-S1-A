package runnables;

import bank_accounts.Account;

public class RunnablePrintBalance implements Runnable {
	private static final int DELAY = 1;
	private Account account;

	public RunnablePrintBalance(Account a) {
		account = a;
	}

	public void run() {
		try {
			account.printBalance();
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (PB): The thread has timed out.");
		}

	}
}
