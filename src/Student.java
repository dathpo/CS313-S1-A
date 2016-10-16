/**
 * This class is the student account type and has the addition features of a
 * standing order functionality and transfer
 * 
 * @author
 *
 */
public class Student extends Account {

	public Student(int accNo, String accHolder) {
		super(accNo, accHolder);
	}

	public void transfer(double amount, Account recipient) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void createSO(int period) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public boolean performSO() {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

}
