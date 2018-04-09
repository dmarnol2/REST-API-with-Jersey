package edu.asupoly.ser422.lab3.model;

public class PhoneEntry {
	private int userId;
	private String firstname;
	private String lastname;
	private String phone;
	private String entry;
	private String[] substring;
	private String book = " ";

	public PhoneEntry(int userId, String firstname, String lastname, String phone) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public String getEntry(String phoneNum) {
		// search entries for match to phone number
		return entry;
	}

	public void setFirstname(String fname) {
		firstname = fname;
	}

	public void setLastname(String lname) {
		lastname = lname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setBook(String bookName) {
		book = bookName;
	}

	public String getBook() {
		return book;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public int getId() {
		return userId;
	}

	public String getLastname() {
		return lastname;
	}

	public void changeName(String newfirstname, String newlastname) {
		firstname = newfirstname;
		lastname = newlastname;
	}

	public void removeEntry(PhoneEntry entry) {

	}

	public String[] getSet(String firstname, String lastname) {
		// search entries for match names, add to array, return array
		return substring;
	}

	public String[] getUnlisted(String phone) {
		return null;

	}

	public String toString() {
		return userId + "\n" + firstname + "\n" + lastname + "\n" + phone;
	}
}
