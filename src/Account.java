import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is the super class for all the account types. It gives them all
 * the basic functionality they will need for performing the specific situations
 * 
 * @Group5 
 *
 */
public class Account implements Runnable {
	private double balance;
	private int accountNo;
	private String accountHolder;
	private double interest;
	public Lock cashLock;
	public Condition cashAvailableCondition;
	public boolean stillWaiting;
		
	public Account(int accNo, String accHolder) {
		balance = 0.0;
		accountNo = accNo;
		accountHolder = accHolder;
		cashLock = new ReentrantLock();
		cashAvailableCondition = cashLock.newCondition();
		stillWaiting = true;
	}

	public void setBalance(double value) {
		this.balance = value;
	}
	
	public void printBalance() {
		System.out.println("The balance of " + this.getAccHolder() + " is £" + this.getBalance() + ".");
	}

	public double getBalance() {
		return this.balance;
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

	public String getAccHolder() {
		return this.accountHolder;
	}

	public boolean deposit(double value) {
		cashLock.lock();
		try {
			if (value > 0) {
				balance += value;
				cashAvailableCondition.signalAll();
				return true;
			}
		}
		finally {
			cashLock.unlock();
		}
		System.out.println("The value has to be above zero.");
		return false;
	}

	public boolean withdraw(double value) {
		cashLock.lock();
		try {
			if(balance >= value){
				if (!stillWaiting) {
					Thread.currentThread().interrupt();
					stillWaiting = cashAvailableCondition.await(10, TimeUnit.SECONDS);
				}
				balance -= value;
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			cashLock.unlock();
		}
		System.out.println("There are insufficient funds available in the account to withdraw the amount selected.");
		return false;
	}

	public void run() {
		
	}

}

