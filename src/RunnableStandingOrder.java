
public class RunnableStandingOrder implements Runnable {
	private static final int DELAY = 1;
	private Account account;
	private Account recipient;
	private double value;
	private int days;

	public RunnableStandingOrder(Account a, Account r, double v, int d) {
		account = a;
		recipient = r;
		value = v;
		days = d;
	}
	
	public void run() {
		try {
			for(;;){
				Thread.sleep(3000);
				account.createStandingOrder(recipient, value, days);
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException e) {
			System.out.println("Timeout");
		}
		
	}
}