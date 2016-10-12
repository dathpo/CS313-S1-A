import java.util.Set;
import java.util.HashSet;

public class StandingOrder {
   private int daysTillPayment;
   
   public void setDaysTillPayment(int value) {
      this.daysTillPayment = value;
   }
   
   public int getDaysTillPayment() {
      return this.daysTillPayment;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * StandingOrder ------------------------- Student
    *           standingOrder        &gt;       student
    * </pre>
    */
   private Set<Student> student;
   
   public Set<Student> getStudent() {
      if (this.student == null) {
         this.student = new HashSet<Student>();
      }
      return this.student;
   }
   
   private double amount;
   
   public void setAmount(double value) {
      this.amount = value;
   }
   
   public double getAmount() {
      return this.amount;
   }
   
   }
