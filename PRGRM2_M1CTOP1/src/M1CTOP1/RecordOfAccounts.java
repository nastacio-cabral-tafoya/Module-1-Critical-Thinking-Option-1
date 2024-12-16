package M1CTOP1;

public class RecordOfAccounts
{
	private Account first;
	private Account last;
	private Account iterator;
	private int     length;
	
	public RecordOfAccounts()
	{
		this.first    = null;
		this.last     = this.first;
		this.iterator = this.first;
		this.length   = 0;
	}
	
	public CheckingAccount getAccount(long accountID)
	{
		this.iterator = this.first;
		
		while (this.iterator != null)
		{
			if (this.iterator.acc.id() == accountID)
			{
				return (this.iterator.acc);
			}
			
			this.iterator = this.iterator.next;
		}
		
		return (null);
	}
	
	public void add(CheckingAccount acc)
	{
		switch(this.length)
		{
			case 0:
				this.first     = new Account();
				this.first.acc = acc;
				this.last      = this.first;
				this.iterator  = this.first;
				this.length++;
				break;
			
			default:
				this.last.next = new Account();
				this.last.next.acc  = acc;
				this.last = this.last.next;
				this.length++;
				break;
		}
	}
	
	public String remove(long accountID)
	{
		String err = "Account Number " + String.valueOf(accountID) + " does not exist!";
		
		if (this.first.acc.id() == accountID)
		{
			err        = "";
			this.first = this.first.next;
			this.length--;
		}
		else
		{
			this.iterator = this.first;
			
			while(this.iterator != null)
			{
				if (this.iterator.next.acc.id() == accountID)
				{
					err                = "";
					this.iterator.next = this.iterator.next.next;
					this.length--;
					
					if (this.iterator.next == null)
					{
						this.last = this.iterator;
					}
				}
				
				this.iterator = this.iterator.next;
			}
		}
		
		return (err);
	}
	
	private String [][] formatAccSummary()
	{
		String [][] accounts = new String[this.length + 1][BankAccount.summaryFieldCount];
		int    []   mxLngths = new int[BankAccount.summaryFieldCount];
		
		accounts[0] = BankAccount.summaryFieldNames;
		
		for (int i = 1; (i < mxLngths.length); i++)
		{
			mxLngths[i] = 0;
		}
		
		this.iterator = this.first;
		
		for (int i = 1; (i < accounts.length); i++)
		{
			accounts[i] = this.iterator.acc.accountSummary();
			
			for (int j = 0; (j < accounts[i].length); j++)
			{
				if (accounts[i][j].length() > mxLngths[j])
				{
					mxLngths[j] = accounts[i][j].length();
				}
			}
			
			this.iterator = this.iterator.next;
		}
		
		for (int i = 0; (i < accounts.length); i++)
		{
			for (int j = 0; (j < accounts[i].length); j++)
			{
				while (accounts[i][j].length() < mxLngths[j])
				{
					accounts[i][j] += " ";
				}
			}
		}
		
		return (accounts);
	}
	
	public void print()
	{
		String [][] outPutData = this.formatAccSummary();
		
		for (int i = 0; (i < outPutData.length); i++)
		{
			System.out.printf(" %s | %s | %s | %s\n", outPutData[i][0], outPutData[i][1], outPutData[i][2], outPutData[i][3]);
			
			if (i == 0)
			{
				for (int j = 0; (j < (outPutData[i][0].length() + outPutData[i][1].length() + outPutData[i][2].length() + outPutData[i][3].length() + 10)); j++)
				{
					System.out.print("_");
				}
				
				System.out.println();
			}
		}
		
		System.out.println();
	}
	
	private class Account
	{
		public CheckingAccount acc  = null;
		public Account         next = null;
	}
}





















































