/** 
 *  This driver class tests the scenario where
 *  the two	account	holders	are	trying simultaneously
 *  to deposit/withdraw money & then check the
 *  balance. 
 *  
 *  @Group6
 */

public class Driver3 {

	public static void main(String args[]) {
		System.out.println("Scenario #3");
		System.out.println();
		Account a3 = new Account(3, "Account #3");
		a3.setBalance(252.0);
		RunnableDeposit rd = new RunnableDeposit(a3, 100.0);
		RunnableWithdraw rw = new RunnableWithdraw(a3, 50.0);
		RunnablePrintBalance rpb = new RunnablePrintBalance(a3);
		
		Thread rdT = new Thread(rd);
		Thread rwT = new Thread(rw);
		Thread rpbT = new Thread(rpb);

		rdT.start();
		rwT.start();
		rpbT.start();

	}
}
