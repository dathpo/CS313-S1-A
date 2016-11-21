public class DriverTest {

	public static void main(String args[]) {
		Account a1 = new Account(1, "Account #215");
		Account a2 = new Account(2, "Account #232");
		a1.setBalance(0);
		a2.setBalance(0);
		RunnablePrintBalance rpb = new RunnablePrintBalance(a1);
		RunnableDeposit rd1 = new RunnableDeposit(a1, 40);
		RunnableWithdraw rw = new RunnableWithdraw(a1, 50);
		RunnableDeposit rd2 = new RunnableDeposit(a1, 20);
		RunnableTransfer rt = new RunnableTransfer(a1, a2, 10);		
		
		Thread rpbT = new Thread(rpb);
		Thread rd1T = new Thread(rd1);
		Thread rwT = new Thread(rw);
		Thread rd2T = new Thread(rd2);
		Thread rtT = new Thread(rt);
		
		rpbT.start();
		rd1T.start();
		rwT.start();
		rd2T.start();
		rtT.start();

	}
	
}
