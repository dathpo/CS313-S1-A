/**
 * 
 * This is the current account, it's only additional feature is the ability to
 * transfer
 * 
 * @Group5
 *
 */
public class Current extends Account {
	public Current(int accNo, String accHolder) {
		super(accNo, accHolder);
	}

	public boolean transfer(double value, Account recipient) {
		if (value >= this.printBalance()) {
			recipient.deposit(value);
			this.withdraw(value);
		}
		return false;
	}

}
