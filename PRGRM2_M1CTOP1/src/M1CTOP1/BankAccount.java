package M1CTOP1;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccount
{
	protected ArrayList<String> transactions;
	
	private String firstName;
	private String lastName;
	private long    accountID;
	private double balance;
	
	public static int summaryFieldCount = 4;
	
	public static String [] summaryFieldNames = new String[] {
		"Account Number",
		"First Name",
		"Last Name",
		"Balance"
	};
	
	public BankAccount()
	{
		this.firstName         = "";
		this.lastName          = "";
		this.accountID         = 0;
		this.balance           = 0.00;
		this.transactions      = new ArrayList<String>();
	}
	
	private void updateTCList(double amt, String description)
	{
		DecimalFormat df         = new DecimalFormat("#.##");
		double        displayAmt = amt;
		
		if (displayAmt < 0)
		{
			displayAmt *= -1;
		}
		
		String record = ("$" + df.format(displayAmt));
		
		if (amt < 0)
		{
			record = ("(" + record + ")");
		}
		
		while (record.length() < 20)
		{
			record = ("." + record);
		}
		
		record += (" - " + description);
		
		String [] ymd = M1CTOP1.split(M1CTOP1.split(LocalDateTime.now().toString(), 'T')[0], '-');
		
		String tcDate = (ymd[1] + "/" + ymd[0] + "/" + ymd[2]);
		
		switch (this.transactions.size())
		{
			case 0:
				this.transactions.add(tcDate +  ":" + record);
				break;
			default:
				boolean showTCDate = true;
				
				for (int i = (this.transactions.size() - 1); (i >= 0); i--)
				{
					if (tcDate.equals(M1CTOP1.split(this.transactions.get(i), ':')[0]))
					{
						showTCDate = false;
					}
				}
				
				if (showTCDate)
				{
					this.transactions.add(tcDate +  ":" + record);
				}
				else
				{
					String blankSpace = "";
					
					for (int i = 0; (i < tcDate.length()); i++)
					{
						blankSpace += " ";
					}
					
					this.transactions.add(blankSpace +  ":" + record);
				}
				break;
		}
	}
	
	public void deposit(double amt)
	{
		this.balance += amt;
		
		this.updateTCList(amt, "Deposit");
	}
	
	public void deposit(double amt, String description)
	{
		this.balance += amt;
		
		this.updateTCList(amt, description);
	}
	
	public void firstName(String name)
	{
		this.firstName = name;
	}
	
	public void lastName(String name)
	{
		this.lastName = name;
	}
	
	public String firstName()
	{
		return (this.firstName);
	}
	
	public String lastName()
	{
		return (this.lastName); 
	}
	
	public double getBalance()
	{
		return (this.balance);
	}
	
	public long id()
	{
		return (this.accountID);
	}
	
	public String [] accountSummary()
	{
		return (new String[]{String.valueOf(this.accountID), this.firstName, this.lastName, String.valueOf(this.balance)});
	}
	
	protected void id(long id)
	{
		this.accountID = (Long.parseLong(M1CTOP1.replace(M1CTOP1.split(LocalDateTime.now().toString(), 'T')[0], "-", "")) * 1000000);
		this.accountID += id;
	}
	
	protected void withdrawl(double amt)
	{
		this.balance -= amt;
		
		this.updateTCList((0 - amt), "Withdrawl");
	}
	
	protected void withdrawl(double amt, String description)
	{
		this.balance -= amt;
		
		this.updateTCList((0 - amt), description);
	}
}




























