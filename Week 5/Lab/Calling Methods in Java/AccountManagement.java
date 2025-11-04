public class AccountManagement {
	
	public static String richest(BankAccount[] accounts) {
		BankAccount richest = accounts[0];
		
		for (BankAccount account : accounts) {
			if (account.currentBalance() > richest.currentBalance())
				richest = account;
		}
		
		return richest.accountName();
	}
	
	public static String poorest(BankAccount[] accounts) {
		BankAccount poorest = accounts[0];
		
		for (BankAccount account : accounts) {
			if (account.currentBalance() < poorest.currentBalance())
				poorest = account;
		}
		
		return poorest.accountName();
	}
	
	public static float overallBalance(BankAccount[] accounts) {
		float total = 0;
		
		for (BankAccount account : accounts) {
			total += account.currentBalance();
		}
		
		return total;
	}
	
	public static BankAccount findAccount(String accountName, BankAccount[] accounts) {
		for (BankAccount account : accounts) {
			if (account.accountName().equals(accountName)) return account;
		}
		
		return null;
	}
	
}
