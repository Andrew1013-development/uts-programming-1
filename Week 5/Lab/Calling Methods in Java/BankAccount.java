public class BankAccount {
	
	private String accountName;
	private int balance;
	
	public BankAccount(String accountName, float initialBalance) {
		this.accountName = accountName;
		this.balance = Math.round(initialBalance*100);
	}
	
	public float currentBalance() {
		return (float)this.balance/100;
	}
	
	public void withdraw(float amount) {
		balance -= Math.round(amount*100);
	}
	
	public void deposit(float amount) {
		balance += Math.round(amount*100);
	}
	
	public String accountName() {
		return this.accountName;
	}
	
}
