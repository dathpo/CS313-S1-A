
public class RunnableStandingOrder implements Runnable {
	private static final int DELAY = 1000;
	private Account account;
	private Account recipient;
	private double value;
	private int occurrence;

	public RunnableStandingOrder(Account a, Account r, double v, int o) {
		account = a;
		recipient = r;
		value = v;
		occurrence = o;
	}
	
	public void run() {
		try {
			for(int i=0; i<occurrence; i++){
				account.createStandingOrder(recipient, value, occurrence);
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException e) {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " (SO): There are no impending balance-increasing operations, the thread has timed out.");
		}
		
	}
}