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
	
	private double standingOrderAmount;
	private int daysTillPayment;
	
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
		System.out.println("Thread with ID " + Thread.currentThread().getId() + ": Trying to print the balance...");
		System.out.println("Thread with ID " + Thread.currentThread().getId() + ": Checking if all other impending operations are terminated...");
		noImpendingOpsCondition.await(3, TimeUnit.SECONDS);
		try {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + ": The balance on " + getAccName() + " is £" + getBalance() + ".");
			stillWaiting = noImpendingOpsCondition.await(3, TimeUnit.SECONDS);			
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
		System.out.println("Thread with ID " + Thread.currentThread().getId() + ": Trying to deposit...");
		try {
			if (value > 0) {
				balance += value;
				System.out.println("Thread with ID " + Thread.currentThread().getId() + ": £" + value + " have been deposited on " + getAccName() + ".");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + ": The balance on " + getAccName() + " is now £" + getBalance() + ".");
				cashAvailableCondition.signalAll();
				noImpendingOpsCondition.signalAll();
			} else {
				System.out.println("The value must be above zero.");
			}
		} finally {
			cashLock.unlock();
		}
	}

	public void withdraw(double value) throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + ": Trying to withdraw...");
		try {
			while (balance < value) {
				if (!stillWaiting) {
					System.out.println("There are insufficient funds available in the account to withdraw the amount selected.");
					Thread.currentThread().interrupt();
				}
					stillWaiting = cashAvailableCondition.await(1, TimeUnit.SECONDS);
			}
			balance -= value;
			System.out.println("Thread with ID " + Thread.currentThread().getId() + ": £" + value + " have been withdrawn from " + getAccName() + ".");
			System.out.println("Thread with ID " + Thread.currentThread().getId() + ": The balance on " + getAccName() + " is now £" + getBalance() + ".");
			noImpendingOpsCondition.signalAll();
		} finally {
			cashLock.unlock();
		}
	}
	
	public void transfer(double value, Account recipient) throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + ": Trying to transfer...");
		try {
			while (balance < value) {
				if (!stillWaiting) {
					System.out.println("There are insufficient funds available in the account to transfer the amount selected.");
					Thread.currentThread().interrupt();
				}
					stillWaiting = cashAvailableCondition.await(1, TimeUnit.SECONDS);
			}
			recipient.balance += value;
			this.balance -= value;
			System.out.println("Thread with ID " + Thread.currentThread().getId() + ": £" + value + " have been transferred from " + getAccName() + " to " + recipient.getAccName() + ".");
			System.out.println("Thread with ID " + Thread.currentThread().getId() + ": The balance on " + getAccName() + " is now £" + getBalance() + ".");
			System.out.println("Thread with ID " + Thread.currentThread().getId() + ": The balance on " + recipient.getAccName() + " is now £" + recipient.getBalance() + ".");
			noImpendingOpsCondition.signalAll();
		} finally {
			cashLock.unlock();
		}
	}

	public void setDaysTillPayment(int value) {
		this.daysTillPayment = value;
	}

	public int getDaysTillPayment() {
		return this.daysTillPayment;
	}

	public void createStandingOrder(Account recipient, double value, int days) throws InterruptedException {
		cashLock.lock();
		System.out.println("Thread with ID " + Thread.currentThread().getId() + ": Trying to create a standing order.");
		try {
			if (value < this.getBalance()) {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + ": A standing order of £"
						+ value + " occuring every " + days + " days has been set up from " + getAccName() + " to "
						+ recipient.getAccName() + ".");
				standingOrderAmount += value;
				this.withdraw(value);
				System.out.println("Thread with ID " + Thread.currentThread().getId() + ": £" + value + " have been transferred from " + getAccName() + " to " + recipient.getAccName() + ".");
				System.out.println("Thread with ID " + Thread.currentThread().getId() + ": The balance on " + getAccName() + " is now £" + getBalance() + ".");
			} else {
				System.out.println("The value must be above zero.");
			}
		} finally {
			cashLock.unlock();
		}
	}

	public double getStandingOrderAmount() {
		return this.standingOrderAmount;
	}

	public void run() {

	}
}
