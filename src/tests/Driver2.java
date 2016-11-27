package tests;

import bank_accounts.Account;
import runnables.RunnableDeposit;
import runnables.RunnablePrintBalance;
import runnables.RunnableWithdraw;

/**
 * This driver class tests the scenario where one account holder tries to check
 * the balance while the other is depositing/withdrawing money.
 * 
 * @Group6
 */

public class Driver2 {

	public static void main(String args[]) {

		System.out.println("Scenario #2");
		System.out.println();

		Account a2 = new Account(2, "Account #2");
		a2.setBalance(126.0);

		RunnablePrintBalance rpb = new RunnablePrintBalance(a2);
		RunnableDeposit rd = new RunnableDeposit(a2, 40);
		RunnableWithdraw rw = new RunnableWithdraw(a2, 50);

		Thread rpbT = new Thread(rpb);
		Thread rdT = new Thread(rd);
		Thread rwT = new Thread(rw);

		rpbT.start();
		rdT.start();
		rwT.start();
	}
}
