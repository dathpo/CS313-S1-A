
public class RunnableSO implements Runnable {
	private static final int DELAY = 1;
	private Student account;
	private double value;
	private int period;

	public RunnableSO(Student a, double v, int p) {
		account = a;
		value = v;
		period = p;
	}
	
	public void run() {
		try {
			for(;;){
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " is trying to create a standing order.");
				Thread.sleep(3000);
				account.createStandingOrder(value, period);
				System.out.println(account.getAccHolder() + " has tried to add £" + value + " to SO Running Balance " + account.getStandingOrderAmount() + ".");
				System.out.println("The balance of " + account.getAccHolder() + " is now £" + account.getBalance() + ".");
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException e) {
			System.out.println("Cannot wait anymore.");
		}
		
	}
}