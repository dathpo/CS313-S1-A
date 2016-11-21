
public class RunnableDeposit implements Runnable {
	private static final int DELAY = 1;
	private Account account;
	private double value;

	public RunnableDeposit(Account a, double v) {
		account = a;
		value = v;
	}
	
	public void run() {
		try {
			account.deposit(value);
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e) {
			System.out.println("Timeout");
		}
		
	}
}