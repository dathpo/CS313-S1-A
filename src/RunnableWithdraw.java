
public class RunnableWithdraw implements Runnable {
	private static final int DELAY = 1;
	private Account account;
	private double value;

	public RunnableWithdraw(Account a, double v) {
		account = a;
		value = v;
	}
	
	public void run() {
		try {
			account.withdraw(value);
			Thread.sleep(DELAY);
		}
		catch (InterruptedException exception) {
			
		}
		
	}
}
