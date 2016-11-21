/** 
 *  This driver class tests the scenario where there
 *  are insufficient funds to complete a withdraw. This
 *  is an open-ended scenario and it is	up to you to decide
 *  what the expected behaviour will be. Ideally (i.e. in
 *  order to achieve full marks), your system should implement
 *  a mechanism that waits for the balance to grow.
 *  
 *  @Group6
 */

public class Driver5 {

	public static void main(String args[]) {
		Account a5 = new Account(5, "Account #5");
		Account a1 = new Account(1, "Account #1");
		a5.setBalance(26.35);
		a1.setBalance(135.61);
		RunnableWithdraw rw = new RunnableWithdraw(a5, 50.0);
		RunnableStandingOrder rso = new RunnableStandingOrder(a1, a5, 20, 1);
		
		Thread rwT = new Thread(rw);
		Thread rsoT = new Thread(rso);

		rwT.start();
		rsoT.start();
		
	}
}
