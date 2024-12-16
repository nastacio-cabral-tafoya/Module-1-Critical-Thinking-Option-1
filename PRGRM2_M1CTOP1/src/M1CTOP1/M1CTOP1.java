package M1CTOP1;

import java.util.Scanner;

public class M1CTOP1
{
	private static String           displayError    = "";
	private static String           user_input      = "";
	private static String []        user_input_list = null;
	private static Scanner          inputScanner    = new Scanner(System.in);
	private static RecordOfAccounts accounts        = new RecordOfAccounts();
	
	
	public static void main(String [] args)
	{
		//Main method.
		//Enters terminal like interface for user interaction.
		System.out.println("Type \"help\" to get a list of commands for the Accounts Management System.");
		
		outerloop:
		while (true)
		{ //Prompts the user to enter commands to update and display daily average temperature for the week.
			M1CTOP1.displayErrorMessage();
			M1CTOP1.accounts.print();
			
			System.out.print("Accounts Management> ");
			
			try
			{
				M1CTOP1.user_input_list = M1CTOP1.split(M1CTOP1.inputScanner.nextLine(), ' ');
				
				switch(M1CTOP1.user_input_list[0].toUpperCase())
				{
					case "HELP":
						System.out.println("Command Information:");
						System.out.println("\tHELP displays a list of commands and information about how to use them.");
						System.out.println("\tOPEN allows the user to create a new account.");
						System.out.println("\tCLOSE allows the user to close an account.");
						System.out.println("\tSELECT allows the user to select and view a specific account.");
						System.out.println("\tEXIT allows the user to quit and exit the program.\n");
						break;
					case "OPEN":
						if (M1CTOP1.user_input_list.length == 1)
						{
							M1CTOP1.openAccount();
						}
						break;
					case "CLOSE":
						if (M1CTOP1.user_input_list.length == 1)
						{
							M1CTOP1.closeAccount();
						}
						break;
					case "SELECT":
						if (M1CTOP1.user_input_list.length == 1)
						{
							M1CTOP1.selectAccount();
						}
						break;
					case "EXIT":
						innerloop:
						while (true)
						{
							M1CTOP1.displayErrorMessage();
							
							System.out.print("Are you sure you want to quit? (y/n) > ");
							
							M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
							
							switch (M1CTOP1.user_input.toUpperCase())
							{
								case "Y":
								case "YES":
									break outerloop;
								case "N":
								case "NO":
									break innerloop;
								default:
									M1CTOP1.displayError = ("INVALID RESPONSE: \"" + M1CTOP1.user_input + "\" is not recognized!");
									break;
							}
						}
						break;
					default:
						M1CTOP1.displayError = ("\"" + M1CTOP1.user_input_list[0] + "\" is not a recognized command! Type command \"help\" to get a list of commands.");
						break;
				}
			}
			catch (NullPointerException e)
			{
				continue;
			}
		}
	}
	
	private static void openAccount()
	{
		String firstname  = "";
		String lastname   = "";
		int    blankCount = 0;
		
		do
		{
			M1CTOP1.displayErrorMessage();
			System.out.print("Enter First Name: ");
			firstname = M1CTOP1.inputScanner.nextLine();
			
			if (firstname.length() == 0)
			{
				switch (blankCount)
				{
					case 0:
						blankCount++;
						M1CTOP1.displayError = "First Name cannot be blank.\nPress enter again to cancel.";
						break;
					case 1:
						return;
				}
			}
			else
			{
				blankCount = 0;
			}
		} while(firstname.length() == 0);
		
		do
		{
			M1CTOP1.displayErrorMessage();
			System.out.print("Enter Last Name: ");
			lastname = M1CTOP1.inputScanner.nextLine();
			
			if (lastname.length() == 0)
			{
				switch (blankCount)
				{
					case 0:
						blankCount++;
						M1CTOP1.displayError = "Last Name cannot be blank.\nPress enter again to cancel.";
						break;
					case 1:
						return;
				}
			}
			else
			{
				blankCount = 0;
			}
		} while(lastname.length() == 0);
		
		M1CTOP1.accounts.add(new CheckingAccount(firstname, lastname));
	}
	
	private static void closeAccount()
	{
		int blankCount = 0;
		
		do
		{
			M1CTOP1.displayErrorMessage();
			System.out.print("Enter Account Number to Close: ");
			M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
			
			if (M1CTOP1.user_input.length() == 0)
			{
				switch (blankCount)
				{
					case 0:
						blankCount++;
						M1CTOP1.displayError = "Account Number cannot be blank.\nPress enter again to cancel.";
						break;
					case 1:
						return;
				}
			}
			else
			{
				blankCount = 0;
				M1CTOP1.displayError = M1CTOP1.accounts.remove(Long.parseLong(M1CTOP1.user_input));
				break;
			}
		} while(true);
	}
	
	public static void selectAccount()
	{
		int             blankCount = 0;
		CheckingAccount account    = null;
		
		do
		{
			M1CTOP1.displayErrorMessage();
			System.out.print("Enter Account Number to Select: ");
			M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
			
			try
			{
				if (M1CTOP1.user_input.length() == 0)
				{
					switch (blankCount)
					{
						case 0:
							blankCount++;
							M1CTOP1.displayError = "Account Number cannot be blank.\nPress enter again to cancel.";
							break;
						case 1:
							return;
					}
				}
				else
				{
					blankCount = 0;
					account    = M1CTOP1.accounts.getAccount(Long.parseLong(M1CTOP1.user_input));
					break;
				}
			}
			catch (NumberFormatException e)
			{
				M1CTOP1.displayError = ("\"" + M1CTOP1.user_input + "\" is invalid. The Account Number must be a number.");
			}
			
		} while(true);
		
		outerloop:
			while (true)
			{ //Prompts the user to enter commands to update and display daily average temperature for the week.
				M1CTOP1.displayErrorMessage();
				account.displayAccount();
				
				System.out.print("\nAccounts Management\\Account " + String.valueOf(account.id())+ "> ");
				
				try
				{
					M1CTOP1.user_input_list = M1CTOP1.split(M1CTOP1.inputScanner.nextLine(), ' ');
					
					switch(M1CTOP1.user_input_list[0].toUpperCase())
					{
						case "HELP":
							System.out.println("Command Information:");
							System.out.println("\tHELP displays a list of commands and information about how to use them.");
							System.out.println("\tCHNGFIRST allows the user to change the first name.");
							System.out.println("\tCHNGLAST allows the user to change the last name.");
							System.out.println("\tDEPOSIT allows the user to deposit an amount into the account.");
							System.out.println("\tWITHDRAWL allows the user to withdraw from the account.");
							System.out.println("\tEXIT allows the user to exit the account.\n");
							break;
						case "CHNGFIRST":
							if (M1CTOP1.user_input_list.length == 1)
							{
								blankCount = 0;
								
								innerloop:
								do
								{
									M1CTOP1.displayErrorMessage();
									System.out.print("Enter First Name: ");
									M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
									
									if (M1CTOP1.user_input.length() == 0)
									{
										switch (blankCount)
										{
											case 0:
												blankCount++;
												M1CTOP1.displayError = "First Name cannot be blank.\nPress enter again to cancel.";
												break;
											case 1:
												break innerloop;
										}
									}
									else
									{
										blankCount = 0;
										account.firstName(M1CTOP1.user_input);
									}
								} while(M1CTOP1.user_input.length() == 0);
							}
							break;
						case "CHNGLAST":
							if (M1CTOP1.user_input_list.length == 1)
							{
								blankCount = 0;
								
								innerloop:
								do
								{
									M1CTOP1.displayErrorMessage();
									System.out.print("Enter Last Name: ");
									M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
									
									if (M1CTOP1.user_input.length() == 0)
									{
										switch (blankCount)
										{
											case 0:
												blankCount++;
												M1CTOP1.displayError = "Last Name cannot be blank.\nPress enter again to cancel.";
												break;
											case 1:
												break innerloop;
										}
									}
									else
									{
										blankCount = 0;
										account.lastName(M1CTOP1.user_input);
									}
								} while(M1CTOP1.user_input.length() == 0);
							}
							break;
						case "DEPOSIT":
							if (M1CTOP1.user_input_list.length == 1)
							{
								blankCount = 0;
								
								innerloop:
								do
								{
									M1CTOP1.displayErrorMessage();
									System.out.print("Enter Deposit Amount: ");
									M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
									
									try
									{
										if (M1CTOP1.user_input.length() == 0)
										{
											switch (blankCount)
											{
												case 0:
													blankCount++;
													M1CTOP1.displayError = "Deposit Amount cannot be blank.\nPress enter again to cancel.";
													break;
												case 1:
													break innerloop;
											}
										}
										else
										{
											blankCount = 0;
											account.deposit(Double.parseDouble(M1CTOP1.user_input));
										}
									}
									catch (NumberFormatException e)
									{
										M1CTOP1.displayError = ("\"" + M1CTOP1.user_input + "\" is invalid. The deposit amount must be a number.");
									}
								} while(M1CTOP1.user_input.length() == 0);
							}
							break;
						case "WITHDRAWL":
							if (M1CTOP1.user_input_list.length == 1)
							{
								blankCount = 0;
								
								innerloop:
								do
								{
									M1CTOP1.displayErrorMessage();
									System.out.print("Enter Withdrawl Amount: ");
									M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
									
									try
									{
										if (M1CTOP1.user_input.length() == 0)
										{
											switch (blankCount)
											{
												case 0:
													blankCount++;
													M1CTOP1.displayError = "Withdrawl Amount cannot be blank.\nPress enter again to cancel.";
													break;
												case 1:
													break innerloop;
											}
										}
										else
										{
											blankCount = 0;
											account.processWithdrawl(Double.parseDouble(M1CTOP1.user_input));
										}
									}
									catch (NumberFormatException e)
									{
										M1CTOP1.displayError = ("\"" + M1CTOP1.user_input + "\" is invalid. The deposit amount must be a number.");
									}
								} while(M1CTOP1.user_input.length() == 0);
							}
							break;
						case "EXIT":
							innerloop:
							while (true)
							{
								M1CTOP1.displayErrorMessage();
								
								System.out.print("Are you sure you want to exit the account? (y/n) > ");
								
								M1CTOP1.user_input = M1CTOP1.inputScanner.nextLine();
								
								switch (M1CTOP1.user_input.toUpperCase())
								{
									case "Y":
									case "YES":
										break outerloop;
									case "N":
									case "NO":
										break innerloop;
									default:
										M1CTOP1.displayError = ("INVALID RESPONSE: \"" + M1CTOP1.user_input + "\" is not recognized!");
										break;
								}
							}
							break;
						default:
							M1CTOP1.displayError = ("\"" + M1CTOP1.user_input_list[0] + "\" is not a recognized command! Type command \"help\" to get a list of commands.");
							break;
					}
				}
				catch (NullPointerException e)
				{
					continue;
				}
			}
	}
	
	private static void displayErrorMessage()
	{
		if (M1CTOP1.displayError.length() > 0)
		{
			System.out.println("ERROR:\n\t" + M1CTOP1.displayError.replace("\n", "\n\t"));
			
			M1CTOP1.displayError = "";
		}
	}
	
	public static String replace(String str, String find, String replacement)
	{
		String retVal = "";
		
		for (int i = 0; (i < str.length()); i++)
		{
			int    j;
			String tempStr = "";
			
			for (j = i; (tempStr.length() < find.length()) && (j < str.length()); j++)
			{
				tempStr += str.charAt(j);
			}
			
			if (find.equals(tempStr))
			{
				retVal += replacement;
			}
			else
			{
				retVal += tempStr;
			}
			
			i = (j - 1);
		}
		
		return (retVal);
	}
	
	public static String [] split(String str, char delimiter)
	{
		int j = 0;
		
		for (int i = 0; (i < str.length()); i++)
		{
			if (str.charAt(i) == delimiter)
			{
				j++;
			}
		}
		
		String [] retVal = new String[j + 1];
		j = 0;
		
		for (int i = 0; (i < str.length()); i++)
		{
			if (str.charAt(i) == delimiter)
			{
				j++;
			}
			else
			{
				if (retVal[j] == null)
				{
					retVal[j] = "";
				}
				
				retVal[j] += str.charAt(i);
			}
		}
		
		return (retVal);
	}
}