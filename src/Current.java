/**
 * 
 * This is the current account, it's only additional feature is the ability to
 * transfer
 * 
 * @author
 *
 */
public class Current extends Account implements RunnableTransfer {
	public Current(int accNo, String accHolder) {
		super(accNo, accHolder);
	}

	public boolean transfer(double value, Account recipient) {
		if (value >= this.getBalance()) {
			recipient.deposit(value);
			this.withdraw(value);
		}
		return false;
	}

}
