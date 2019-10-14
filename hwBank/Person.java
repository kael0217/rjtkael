package hwBank;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person>,Cloneable{
	int id;
	String name;
	List<String> address;
	
	
	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		return this.id - p.id;
	}
	
	public Person(int id, String name, List<String> address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return name;
	}
	public void setNameString(String nameString) {
		this.name = nameString;
	}
	public List<String> getAddress() {
		return address;
	}
	public void setAddress(List<String> address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public Person deepclone() throws CloneNotSupportedException{
		List<String> cpAddress = new ArrayList<String>();
		for(String addr:this.address) {
			cpAddress.add(addr);
		}
		Person cpPerson = new Person(this.id, this.name, cpAddress);
		return cpPerson;
	}
}
