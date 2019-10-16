package com.java.dto;

public class User {

	private String firstname;
	private String lastname;
	private String password;
	private String gender;
	private String skillSet;
	private String city;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public User(String firstname, String lastname, String password, String gender, String skillSet, String city) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.gender = gender;
		this.skillSet = skillSet;
		this.city = city;
	}
	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", gender="
				+ gender + ", skillSet=" + skillSet + ", city=" + city + "]";
	}
	
	
}