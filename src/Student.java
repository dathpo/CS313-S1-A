/**
 * This class is the student account type and has the addition features of a
 * standing order functionality and transfer
 * 
 * @author
 *
 */
public class Student extends Account implements RunnableTransfer {

	private double standingOrderAmount;
	private int daysTillPayment;
	public Student(int accNo, String accHolder) {
		super(accNo, accHolder);
	}

	public boolean transfer(double value, Account recipient) {
		if (value >= this.getBalance()) { 
		recipient.deposit(value);
		this.withdraw(value);
		}
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
