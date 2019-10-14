package hwBank;

import java.util.List;

public final class PersonSingleton {
	int id;
	String name;
	List<String> address;
	private static PersonSingleton theLonelyPerson=null;
	
	
	private PersonSingleton() {
		super();
		id=0;
		name="admin";
		address= null;
		
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}


	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<String> getAddress() {
		return address;
	}
	public PersonSingleton getInstance() {
		if(theLonelyPerson==null) {
			theLonelyPerson=new PersonSingleton();
		}
		return theLonelyPerson;
	}
	
}
