
public class RunnableDeposit implements Runnable {
	private static final int DELAY = 1;
	private Student account;
	private double value;

	public RunnableDeposit(Student a, double v) {
		account = a;
		value = v;
	}
	
	public void run() {
		try {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " is trying to deposit.");
			account.deposit(value);
			System.out.println(account.getAccHolder() + " has deposited £" + value + ".");
			System.out.println("The balance of " + account.getAccHolder() + " is now £" + account.getBalance() + ".");
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e) {
			System.out.println("Cannot wait anymore.");
		}
		
	}
}
