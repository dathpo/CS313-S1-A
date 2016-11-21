
public class RunnablePrintBalance implements Runnable {
	private static final int DELAY = 1;
	private Account account;

	public RunnablePrintBalance(Account a) {
		account = a;
	}
	
	public void run() {
		try {
			account.printBalance();
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e) {
			System.out.println("Timeout");
		}
		
	}
}

