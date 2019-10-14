package hwBank;

import java.util.List;

public final class PersonImmutable {
	final int id;
	final String name;
	final List<String> address;
	
	private PersonImmutable(int id, String name, List<String> address) {
		super();
		this.id = id;
		this.name = name;
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
	
}
