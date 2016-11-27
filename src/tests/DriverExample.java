package tests;
import bank_accounts.Account;
import runnables.RunnableDeposit;
import runnables.RunnablePrintBalance;
import runnables.RunnableTransfer;
import runnables.RunnableWithdraw;

public class DriverExample {

	public static void main(String args[]) {
		
		Account a1 = new Account(1, "Account #215");
		Account a2 = new Account(2, "Account #232");
		a1.setBalance(0);
		a2.setBalance(0);
		
		RunnablePrintBalance rpb = new RunnablePrintBalance(a1);
		RunnableDeposit rd1 = new RunnableDeposit(a1, -40);
		RunnableWithdraw rw1 = new RunnableWithdraw(a1, -50);
		RunnableDeposit rd2 = new RunnableDeposit(a1, 0);
		RunnableTransfer rt = new RunnableTransfer(a1, a2, -10);		
		RunnableWithdraw rw2 = new RunnableWithdraw(a1, 50);
		
		Thread rpbT = new Thread(rpb);
		Thread rd1T = new Thread(rd1);
		Thread rw1T = new Thread(rw1);
		Thread rd2T = new Thread(rd2);
		Thread rtT = new Thread(rt);
		Thread rw2T = new Thread(rw2);
		
		rpbT.start();
		rd1T.start();
		rw1T.start();
		rd2T.start();
		rtT.start();
		rw2T.start();
	}
}
