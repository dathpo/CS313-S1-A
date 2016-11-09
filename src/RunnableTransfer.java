
public class RunnableTransfer implements Runnable {
	private static final int DELAY = 1;
	private Student account;
	private Student recipient;
	private double value;

	public RunnableTransfer(Student a, Student r, double v) {
		account = a;
		recipient = r;
		value = v;
	}
	
	public void run() {
		try {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " is trying to transfer.");
			account.transfer(value, recipient);
			System.out.println(account.getAccHolder() + " has transferred £" + value + " to " + recipient.getAccHolder() + ".");
			System.out.println("The balance of " + account.getAccHolder() + " is now £" + account.getBalance() + ".");
			System.out.println("The balance of " + recipient.getAccHolder() + " is now £" + recipient.getBalance() + ".");
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e) {
			System.out.println("Cannot wait anymore.");
		}
		
	}
}
