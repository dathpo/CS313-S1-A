/**
 * This class will be used to create the objects and test all of the instances given in the spec for part A
 * 
 * @Group5
 * */
public class Driver {
	
	public static void main(String args[]) {
		Student a1 = new Student(1, "Joe Bloggs");
		Student a2 = new Student(2, "John Doe");
//		Saver sa1 = new Saver(3, "John Q. Public");
//		Saver sa2 = new Saver(4, "Average Joe");
//		Current cu1 = new Current(5, "John Smith");
//		Current cu2 = new Current(6, "Every Man");
		RunnableDeposit rd = new RunnableDeposit(a1, 100.0);
		RunnableTransfer rt = new RunnableTransfer(a1, a2, 10.0);
		RunnableWithdraw rw = new RunnableWithdraw(a1, 10.0);
		RunnablePrintBalance rpt = new RunnablePrintBalance(a1);
		RunnableSO rso = new RunnableSO(a1, 10.0, 5);
		
		Thread rdT = new Thread(rd);
		Thread rtT = new Thread(rt);
		Thread rwT = new Thread(rw);
		Thread rptT = new Thread(rpt);
		Thread rsoT = new Thread(rso);
		
		rdT.start();
		try { rdT.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		
		rtT.start();
		try { rtT.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		
		rwT.start();
		try { rwT.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		
		rptT.start();
		try { rptT.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		
		rsoT.start();
		try { rsoT.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
						
	}
	
}