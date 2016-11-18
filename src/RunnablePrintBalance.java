
public class RunnablePrintBalance implements Runnable {
	private static final int DELAY = 1;
	private Student account;

	public RunnablePrintBalance(Student a) {
		account = a;
	}
	
	public void run() {
		try {
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " is trying to print the balance.");
			account.printBalance();
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e) {
			System.out.println("Cannot wait anymore.");
		}
		
	}
}

