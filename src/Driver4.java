/** 
 *  This driver class tests the scenario where
 *  the	two	account	holders	are	trying simultaneously
 *  to deposit/withdraw money & then check the balance,
 *  but at the same time a bank employee is in the
 *  process of completing a standing order in/out
 *  the account.
 *  
 *  @Group6
 */

public class Driver4 {

	public static void main(String args[]) {
		Account a4 = new Account(4, "Account #4");
		Account a1 = new Account(1, "Account #1");
		a4.setBalance(16.48);
		a1.setBalance(25.31);
		RunnableDeposit rd = new RunnableDeposit(a4, 100.0);
		RunnableWithdraw rw = new RunnableWithdraw(a4, 50.0);
		RunnablePrintBalance rpt = new RunnablePrintBalance(a4);
		RunnableStandingOrder rso = new RunnableStandingOrder(a4, a1, 40, 3);
		
		Thread rdT = new Thread(rd);
		Thread rwT = new Thread(rw);
		Thread rptT = new Thread(rpt);
		Thread rsoT = new Thread(rso);

		rdT.start();
		rwT.start();
		rptT.start();
		rsoT.start();
		
	}
}
