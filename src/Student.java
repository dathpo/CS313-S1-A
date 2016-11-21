import java.util.concurrent.TimeUnit;

/**
 * This class is the student account type and has the addition features of a
 * standing order functionality and transfer
 * 
 * @Group6
 */

public class Student extends Account implements Runnable {

	public Student(int accNo, String accName) {
		super(accNo, accName);
	}
		
}