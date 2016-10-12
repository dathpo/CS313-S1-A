
public class Account {
   private double balance;
   
   public void setBalance(double value) {
      this.balance = value;
   }
   
   public double getBalance() {
      return this.balance;
   }
   
   private int accountNo;
   
   public void setAccountNo(int value) {
      this.accountNo = value;
   }
   
   public int getAccountNo() {
      return this.accountNo;
   }
   
   private String accountHolder;
   
   public void setAccountHolder(String name) {
      this.accountHolder = name;
   }
   
   public String getAccountHolder() {
      return this.accountHolder;
   }
   
   private double interest;
   
   public void setInterest(double rate) {
      this.interest = rate;
   }
   
   public double getInterest() {
      return this.interest;
   }
   
   public void printBalance() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean deposit(double value) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean withdraw(double value) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
