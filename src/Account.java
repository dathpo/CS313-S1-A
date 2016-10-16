/**
 * This class is the super class for all the account types. It gives them all
 * the basic functionality they will need for performing the specific situations
 * 
 * @author 
 *
 */
public class Account {
	private double balance;
	private int accountNo;
	private String accountHolder;
	private double interest;
	
	
	public Account(int accNo, String accHolder){
		accountNo = accNo;
		accountHolder = accHolder;
	}

	public void setBalance(double value) {
		this.balance = value;
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

	public void printBalance() {
		System.out.println(balance);
	}

	public boolean deposit(double value) {
		balance += value;
		return true;
	}

	public boolean withdraw(double value) {
		if(balance >= value){
			balance -= value;
			return true;
		}
		return false;
	}

}
