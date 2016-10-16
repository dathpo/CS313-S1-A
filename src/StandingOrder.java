import java.util.Set;
import java.util.HashSet;

/**
 * This is a standing order class, will be used to create and store the details
 * of specific standing orders
 * 
 * @author
 *
 */
public class StandingOrder {
	private int daysTillPayment;

	public void setDaysTillPayment(int value) {
		this.daysTillPayment = value;
	}

	public int getDaysTillPayment() {
		return this.daysTillPayment;
	}

	private Set<Student> student;

	public Set<Student> getStudent() {
		if (this.student == null) {
			this.student = new HashSet<Student>();
		}
		return this.student;
	}

	private double amount;

	public void setAmount(double value) {
		this.amount = value;
	}

	public double getAmount() {
		return this.amount;
	}

}
