
public class RunnableTransfer implements Runnable {
	private static final int DELAY = 1;
	private Account account;
	private Account recipient;
	private double value;

	public RunnableTransfer(Account a, Account r, double v) {
		account = a;
		recipient = r;
		value = v;
	}
	
	public void run() {
		try {
			account.transfer(value, recipient);
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e) {
			System.out.println("Timeout");
		}
		
	}
}
