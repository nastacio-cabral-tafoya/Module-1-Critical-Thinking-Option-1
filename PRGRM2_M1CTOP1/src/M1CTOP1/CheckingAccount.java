package M1CTOP1;

import java.util.List;

public class CheckingAccount extends BankAccount
{
	private static long accountCount = 0;
	private double interestRate;
	private double overDraftFee;
	private double feesCharged;
	
	public CheckingAccount(String firstname, String lastname)
	{
		this.firstName(firstname);
		this.lastName(lastname);
		
		CheckingAccount.accountCount++;
		this.interestRate  = 0.01;
		this.overDraftFee  = 30.00;
		this.feesCharged   = 0.00;
		
		this.id(CheckingAccount.accountCount);
	}
	
	public void processWithdrawl(double amt)
	{

		this.withdrawl(amt);
		
		if (this.getBalance() < 0)
		{
			System.out.println("Overdraft Fee Charged: $" + String.valueOf(this.overDraftFee));

			this.withdrawl(this.overDraftFee, "Overdraft Fee");
			this.feesCharged += this.overDraftFee;
		}
		
		System.out.println("Account Balance: $" + String.valueOf(this.getBalance()));
	}
	
	public void displayAccount()
	{
		String [] data = this.accountSummary();
		
		if (this.getBalance() < 0)
		{
			System.out.print("OVER DRAWN ");
		}
		
		System.out.println("Account #" + data[0]);
		System.out.println("\tFirst Name .........: " + this.firstName());
		System.out.println("\tLast Name ..........: " + this.lastName());
		System.out.printf("\tAccount Balance .....: $%.2f\n", + this.getBalance());
		System.out.println("\tInterest Rate ......: " + String.valueOf(this.interestRate * 100) + "%");
		System.out.println("\tTotal Fees Charged .: " + String.valueOf(this.feesCharged));
		System.out.println("\tTotal Transactions .: " + String.valueOf(this.transactions.size()));
		System.out.println("\nTransaction History :");
		
		List<String> tcList = this.transactions.reversed();
		
		for (int i = 0; i < tcList.size(); i++)
		{
			System.out.println(tcList.get(i));
		}
	}
}





















































