package hwBank;

//import java.awt.List;
import java.util.ArrayList;
import java.util.List;

public class Account implements Comparable<Account>{
	public int id;
	public float amount = 0;
	public List<Integer> customers;
	public Type type;
	
	@Override
	public int compareTo(Account b) {
		return this.id-b.id;
	}
	//deposit method
	public void deposit(float m) {
		synchronized (this) {
			this.amount = this.amount+m;
		}
		System.out.println("deposited " +m+  " current amount: "+this.amount);
	}
	//withdraw method
	public void withdraw(float m) {
		synchronized (this) {
			if(this.amount>=m) {
				this.amount = this.amount-m;
				System.out.println("withdrawed "+m+ " current amount: "+ this.amount);
			}else {
				System.out.println("Deposite failed, in sufficient fund, current amount: "+this.amount);
			}
		}
	}
	
	
	public Account(int id, int amount, List<Integer> customers, Type type) {
		super();
		this.id = id;
		this.amount = amount;
		this.customers = customers;
		this.type = type;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public List<Integer> getCustomers() {
		return customers;
	}


	public void setCustomers(List<Integer> customers) {
		this.customers = customers;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	
}

