package tests;

import bank_accounts.Account;
import runnables.RunnablePrintBalance;

/**
 * This driver class tests the scenario where the two account holders are trying
 * to check the balance simultaneously.
 * 
 * @Group6
 */

public class Driver1 {

	public static void main(String args[]) {

		System.out.println("Scenario #1");
		System.out.println();

		Account a1 = new Account(1, "Account #1");
		a1.setBalance(85.0);

		RunnablePrintBalance rpb1 = new RunnablePrintBalance(a1);
		RunnablePrintBalance rpb2 = new RunnablePrintBalance(a1);

		Thread rpbT1 = new Thread(rpb1);
		Thread rpbT2 = new Thread(rpb2);

		rpbT1.start();
		rpbT2.start();
	}
}