/**
 * This class will be used to create the objects and test all of the instances given in the spec for part A
 * 
 * @Group5
 * */
public class Driver {
	
	public static void main(String args[]) {
		Account a1 = new Student(1, "Joe Bloggs");
		Account a2 = new Student(2, "John Doe");
		RunnableDeposit rd = new RunnableDeposit(a1, 20.0);
		RunnableWithdraw rw = new RunnableWithdraw(a1, 18.0);
		RunnableTransfer rt = new RunnableTransfer(a1, a2, 1.0);
		RunnablePrintBalance rpt = new RunnablePrintBalance(a1);
		
		Thread rdT = new Thread(rd);
		Thread rwT = new Thread(rw);
		Thread rtT = new Thread(rt);
		Thread rptT = new Thread(rpt);
		
		rdT.start();
		rwT.start();
		rtT.start();
		rptT.start();
						
//		Saver sa1 = new Saver(3, "John Q. Public");
//		Saver sa2 = new Saver(4, "Average Joe");
//		Current cu1 = new Current(5, "John Smith");
//		Current cu2 = new Current(6, "Every Man");
//		
	}
	
}
