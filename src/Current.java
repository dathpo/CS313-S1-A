/**
 * 
 * This is the current account, it's only additional feature is the ability to transfer
 * 
 * @author 
 *
 */
public class Current extends Account {
   public Current(int accNo, String accHolder) {
		super(accNo, accHolder);
	}

public boolean transfer(double value, Account recipient) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
