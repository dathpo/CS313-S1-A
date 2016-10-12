import java.util.Set;
import java.util.HashSet;

public class Student extends Account {
   /**
    * <pre>
    *           0..*     0..*
    * Student ------------------------- StandingOrder
    *           student        &lt;       standingOrder
    * </pre>
    */
   private Set<StandingOrder> standingOrder;
   
   public Set<StandingOrder> getStandingOrder() {
      if (this.standingOrder == null) {
         this.standingOrder = new HashSet<StandingOrder>();
      }
      return this.standingOrder;
   }
   
   public void transfer(double amount, Account recipient) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void createSO(int period) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean performSO() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
