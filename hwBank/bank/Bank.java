package hwBank;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.security.auth.login.LoginContext;

public class Bank {
	public static Set<Account> accounts = new TreeSet<Account>();
	public static Set<Customer> customers = new TreeSet<Customer>();
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int userid = -1;
		boolean quit=false;
		while(quit==false) {
			while(userid == -1) {
				userid = login(customers);
				if(userid == 0) {
					quit=true;
				}
			}
			if(userid==0) {
				//create new account
				addCustomer(customers);
				break;
			}
			final int loggedId = userid;
			
			
			//select the account you want to operate on
			int accid = -1;
			Account selectedAccount = null;
			boolean accselected = false;
			while(accselected ==false) {
				System.out.println("plaease enter the account id you want to operate on");
				accid = scanner.nextInt();
				Iterator<Account> accitr = accounts.iterator();
				while(accitr.hasNext()) {
					Account temp = accitr.next();
					if(temp.id == accid && temp.customers.contains(loggedId)) {						
						selectedAccount = temp;
						accselected=true;
						break;
					}
				}
				if(accselected==false) {
					System.out.println("account not found or account does not belong to you");
				}
			}
			
			
			
			
			System.out.println("Please select: ");
			System.out.println("1: deposit, 2:withdraw, 3: transfer, 4:create account, 5: create new user ");
			int userselection = scanner.nextInt();
			
			
			switch (userselection) {
			//deposit
			case 1:
				System.out.println("how much do you want to deposit?");
				float depm = scanner.nextFloat();
				if(depm>=0) {
					selectedAccount.deposit(depm);
					
				}
				break;
			//withdraw
			case 2:
				System.out.println("how much do you want to withdraw");
				float witm = scanner.nextFloat();
				selectedAccount.withdraw(witm);
			//transfer
			case 3:
				
				int toid;
				boolean toidselected=false;
				float m=-1;
				boolean amtdecided=false;
				//select amount
				while(amtdecided==false) {
					System.out.println("how much do you want to transfer?");
					m = scanner.nextFloat();
					if(m>=0 && m<selectedAccount.amount) {
						amtdecided=true;
					}
					if(amtdecided==false) {
						System.out.println("insufficient fund");
					}
				}
				
				//select target account
				Account toAccount = null;
				while(toidselected == false) {
					System.out.println("please enter account id you want to transfer to");
					toid = scanner.nextInt();
					Iterator<Account> itra = accounts.iterator();
					while(itra.hasNext()) {
						Account temp = itra.next();
						if(temp.id == toid) {
							toAccount = temp;
							toidselected =true;
						}
					}
					if(toidselected==false) {
						System.out.println("account not found, please enter another");
					}
				}
				
				//transfering
				selectedAccount.withdraw(m);
				toAccount.deposit(m);
				
			//creating new account
			case 4:
				createAccount(accounts, loggedId);
			
			//creating new user
			case 5:
				addCustomer(customers);
				
			default:
				System.out.println("invalid selection please reenter.");
				break;
			}
			
		}
		
		
		
		
		
		
	}
	
	// login validation
	public static int login(Set<Customer> c) {
		int userid;
		int logedid = -1;
		String password;
		Scanner logScanner= new Scanner(System.in);
		boolean loged = false;
		while(loged ==false) {
			System.out.println("please enter your user id, enter 0 to create a new customer");
			userid = logScanner.nextInt();
			if(userid==0) {
				return 0;
			}
			System.out.println("please enter your password");
			password = logScanner.next();
			Iterator<Customer> citr = c.iterator();
			while(citr.hasNext()) {
				Customer temp = citr.next();
				if (temp.id == userid && temp.password == password) {
					logedid = userid;
					loged = true;
					break;
				}
			}
		}
		logScanner.close();
		return logedid;
	}
	
	
	public static void addCustomer(Set<Customer> cs) {
		Scanner addcustScanner = new Scanner(System.in);
		int nid;
		String nname;
		long nmobile;
		String nemail;
		String nusername;
		String npassword = "default";
		String npasswordConfirm = "confirm";
		String naddress;
		boolean created = false;

		
		System.out.println("please input your name");
		nname = addcustScanner.next();
		System.out.println("please input your mobile");
		nmobile = addcustScanner.nextLong();
		System.out.println("please input your email");
		nemail = addcustScanner.next();
		
		System.out.println("please input your username");
		nusername = addcustScanner.next();
		while(!npassword.equals(npasswordConfirm)) {
			System.out.println("please input your password");
			npassword = addcustScanner.next();
			System.out.println("please confirm your password");
			npasswordConfirm = addcustScanner.next();
		}
		
		System.out.println("please input your address");
		naddress = addcustScanner.next();
		
		while(created == false) {
			System.out.println("please input your userid");
			nid = addcustScanner.nextInt();
			Customer newcustCustomer = new Customer(nid, nname, nmobile, nemail, nusername, npassword, naddress);
			created = cs.add(newcustCustomer);
			if(created == false) {
				System.out.println("user id already in use change another one");
			}
		}
		addcustScanner.close();
		System.out.println("Welcome, New Customer");
	}
	
	
	//create new account
	public static void createAccount(Set<Account> accounts, int custid) {
		Scanner newacc = new Scanner(System.in);
		int id;
		boolean correctid=false;
		String typeString = "SAVING";
		boolean correcttype=false;
	
		while(correcttype ==false) {
			System.out.println("please choose your account type: SAVINGS or CURRENT");
			typeString = newacc.next();
			if(typeString == "SAVING" || typeString == "CURRENT") {
				correcttype = true;
			}
		}
		Type type = Type.valueOf(typeString);
		
		while(correctid == false) {
			System.out.println("please enter an account id.");
			id = newacc.nextInt();
			if(id>0) {
				Account acc = new Account(id, 0, null, type); //not finished here
				if(accounts.add(acc)) {
					correctid = true;
					System.out.println("new account successfully created!");
				}else {System.out.println("account id alread in use, change another one");}
			}else {System.out.println("id cant be 0 or negative numbers");}
		}
	}
}
