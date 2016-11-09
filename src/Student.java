import java.util.concurrent.TimeUnit;

/**
 * This class is the student account type and has the addition features of a
 * standing order functionality and transfer
 * 
 * @Group5
 *
 */
public class Student extends Account implements Runnable {

	private double standingOrderAmount;
	private int daysTillPayment;
	public Student(int accNo, String accHolder) {
		super(accNo, accHolder);
	}

	public boolean transfer(double value, Account recipient) {
		cashLock.lock();
		try {
			if (value <= this.getBalance()) { 
				if (!stillWaiting) {
					Thread.currentThread().interrupt();
					stillWaiting = cashAvailableCondition.await(10, TimeUnit.SECONDS);
				}
				recipient.deposit(value);
				this.withdraw(value);
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			cashLock.unlock();
		}
		System.out.println("There are insufficient funds available in the account to transfer the amount selected.");
		return false;
	}
	
	public void setDaysTillPayment(int value) {
		this.daysTillPayment = value;
	}

	public int getDaysTillPayment() {
		return this.daysTillPayment;
	}

	public void createStandingOrder(double value, int period) {
		this.standingOrderAmount = value;
	}
	
	public double getStandingOrderAmount() {
		return this.standingOrderAmount;
	}
	
}
