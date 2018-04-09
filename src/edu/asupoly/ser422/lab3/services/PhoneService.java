package edu.asupoly.ser422.lab3.services;

import java.util.List;
import java.util.Map;

import edu.asupoly.ser422.lab3.model.PhoneBook;
import edu.asupoly.ser422.lab3.model.PhoneEntry;

public interface PhoneService {

	public String[] getSet(String bookName, String firstname, String lastname); // REQ 7

	public int addToBook(String name, String id); // REQ 3

	public List<PhoneEntry> listEntries(String phBook); // REQ 6

	public void savePhoneBook(String fname);

	public boolean updateEntry(String userId, String fname, String lname); // REQ 4

	public int createEntry(int id, String fname, String lname, String phone); // REQ 2

	public List<PhoneEntry> getEntry(String phoneNum); // REQ 1

	public int removeEntry(int id); // REQ 5

	public String toString();

	public List<PhoneEntry> getUnlisted(); // REQ 8

	int createEntry(String fname, String lname, String phoneNum);

}
