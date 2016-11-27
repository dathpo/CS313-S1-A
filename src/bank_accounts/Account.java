package bank_accounts;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is the super class for all the account types. It gives them all
 * the basic functionality they will need for performing the specific situations
 * 
 * @Group6
 */

public class Account implements Runnable {
	private double balance;
	private int accountNo;
	private String accountName;
	private double interest;
	
	private int standingOrderNumber = 1;
	
	public Lock cashLock;
	public Condition cashAvailableCondition;
	public Condition noImpendingOpsCondition;
	public boolean stillWaiting;

	public Account(int accNo, String accName) {
		balance = 0.0;
		accountNo = accNo;
		accountName = accName;
		cashLock = new ReentrantLock();
		cashAvailableCondition = cashLock.newCondition();
		noImpendingOpsCondition = cashLock.newCondition();
		stillWaiting = true;
	}

	public void setBalance(double value) {
		if (value >= 0) {
			this.balance = value;
			System.out.println("The balance on " + getAccName() + " has been set to £" + this.balance + ".");
		} else {
			System.out.println("The value must be above zero.");
		}
	}

	public double getBalance() {
		return this.balance;
	}

	public void printBalance() throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + " (PB): Trying to print the balance on " +getAccName()+ "...");
		System.out.println("Thread with ID " + Thread.currentThread().getId() + " (PB): Checking for any possible concurrent balance-changing operations to have terminated...");
		noImpendingOpsCondition.await(5, TimeUnit.SECONDS);
		try {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (PB): There are no impending balance-changing operations.");
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (PB): The balance on " + getAccName() + " is £" + getBalance() + ".");
			stillWaiting = noImpendingOpsCondition.await(5, TimeUnit.SECONDS);			
		} finally {
			cashLock.unlock();
		}
	}

	public void setInterest(double rate) {
		this.interest = rate;
	}

	public double getInterest() {
		return this.interest;
	}

	public int getAccNo() {
		return this.accountNo;
	}

	public String getAccName() {
		return this.accountName;
	}

	public void deposit(double value) throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + " (DE): Trying to deposit £" + value + " on " + getAccName() + "...");
		try {
			if (value > 0) {
				balance += value;
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (DE): £" + value + " have been deposited on " + getAccName() + ".");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (DE): The balance on " + getAccName() + " is now £" + getBalance() + ".");
				cashAvailableCondition.signalAll();
				noImpendingOpsCondition.signalAll();
			} else {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (DE): The value must be above zero.");
			}
		} finally {
			cashLock.unlock();
		}
	}

	public void withdraw(double value) throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + " (WI): Trying to withdraw £" + value + " from " + getAccName() + "...");
		try {
			if (value > 0) {
				while (balance < value) {
					System.out.println("Thread with ID " + Thread.currentThread().getId() + " (WI): There are currently insufficient funds available in the account to withdraw the amount selected.");
					System.out.println("Thread with ID " + Thread.currentThread().getId() + " (WI): Checking for any possible balance-increasing operations...");
					if (!stillWaiting) {
						Thread.currentThread().interrupt();
					}
					stillWaiting = cashAvailableCondition.await(2, TimeUnit.SECONDS);
				}
				balance -= value;
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (WI): £" + value + " have been withdrawn from " + getAccName() + ".");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (WI): The balance on " + getAccName() + " is now £" + getBalance() + ".");
				noImpendingOpsCondition.signalAll();
			}
			else {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (WI): The value must be above zero.");
			}
		} finally {
			cashLock.unlock();
		}
	}
	
	public void transfer(double value, Account recipient) throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): Trying to transfer £" + value + " from " + getAccName() + " to " + recipient.getAccName() + "...");
		try {
			if (value > 0) {
			while (balance < value) {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): There are currently insufficient funds available in the account to transfer the amount selected.");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): Checking for any possible balance-increasing operations...");
				if (!stillWaiting) {
					Thread.currentThread().interrupt();
				}
					stillWaiting = cashAvailableCondition.await(2, TimeUnit.SECONDS);
			}
			recipient.balance += value;
			this.balance -= value;
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): £" + value + " have been transferred from " + getAccName() + " to " + recipient.getAccName() + ".");
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): The balance on " + getAccName() + " is now £" + getBalance() + ".");
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): The balance on " + recipient.getAccName() + " is now £" + recipient.getBalance() + ".");
			noImpendingOpsCondition.signalAll();
			}
			else {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (TR): The value must be above zero.");
			}
		} finally {
			cashLock.unlock();
		}
	}

	public void createStandingOrder(Account recipient, double value, int occurrence) throws InterruptedException {
		cashLock.lock();
		if (getStandingOrderNumber() == 1) {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): Trying to set up a standing order of £"
					+ value + " occurring " + occurrence + " time(s) from " + getAccName() + " to "
					+ recipient.getAccName() + "...");
		}
		try {
			if (value > 0) {
				while (balance < value) {
					System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): There are currently insufficient funds available in the account to set up the standing order.");
					System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): Checking for any possible balance-increasing operations...");
					if (!stillWaiting) {
						Thread.currentThread().interrupt();
					}
					stillWaiting = cashAvailableCondition.await(2, TimeUnit.SECONDS);
				}
				recipient.balance += value;
				this.balance -= value;
				if (getStandingOrderNumber() == 1) {System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): A standing order of £"
						+ value + " occurring " + occurrence + " time(s) has been set up from " + getAccName() + " to "
						+ recipient.getAccName() + ".");
				}
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): Standing order #" + getStandingOrderNumber() + " has been carried out: £" + value + " have been transferred from " + getAccName() + " to " + recipient.getAccName() + ".");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): The balance on " + getAccName() + " is now £" + getBalance() + ".");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): The balance on " + recipient.getAccName() + " is now £" + recipient.getBalance() + ".");
				noImpendingOpsCondition.signalAll();
				standingOrderNumber++;	
			}
			else {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): The value must be above zero.");
			}
		} finally {
			cashLock.unlock();
		}
	}

	public int getStandingOrderNumber() {
		return this.standingOrderNumber;
	}

	public void run() {

	}
}
