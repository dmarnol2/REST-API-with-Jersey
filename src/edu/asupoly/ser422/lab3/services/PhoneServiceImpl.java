package edu.asupoly.ser422.lab3.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.asupoly.ser422.lab3.model.PhoneBook;
import edu.asupoly.ser422.lab3.model.PhoneEntry;

public class PhoneServiceImpl implements PhoneService {

	private int id = 0;
	private static PhoneService __pService;
	ArrayList<PhoneEntry> __entries;

	public PhoneServiceImpl() {
		__entries = new ArrayList<PhoneEntry>();
	}

	// Create Singleton
	public static PhoneService getInstance() {
		if (__pService == null) {
			__pService = new PhoneServiceImpl();
		}
		return __pService;
	}

	// data structures for phone entries

	HashMap<String, PhoneEntry> phoneEntries = new HashMap<String, PhoneEntry>(); // entries that are not unlisted, with
																					// userId being original index from
																					// list
	HashMap<String, PhoneEntry> unlisted = new HashMap<String, PhoneEntry>();
	ArrayList<PhoneEntry> unlistedEntries = new ArrayList<PhoneEntry>(); // unlisted entries with name and number
	// data structures for phone books
	ArrayList<PhoneEntry> __bookSImpl = new ArrayList<PhoneEntry>(); // specific phone book with PhoneEntry objects
	HashMap<String, ArrayList<PhoneEntry>> phoneBook = new HashMap<String, ArrayList<PhoneEntry>>();
	HashMap<String, PhoneBook> bookEntries = new HashMap<String, PhoneBook>(); // Map of all phone books

	// REQ 8
	@Override
	public List<PhoneEntry> getUnlisted() {

		List<PhoneEntry> copy2 = new ArrayList<PhoneEntry>();
		for (Object value : unlisted.values()) {
			copy2.add((PhoneEntry) value);
		}
		return copy2;
	}

	// REQ 1
	@Override
	public List<PhoneEntry> getEntry(String phoneNum) {
		List<PhoneEntry> item = new ArrayList<PhoneEntry>();
		for (int i = 0; i <= __entries.size() - 1; i++) {
			if (__entries.get(i).getPhone().equals(phoneNum)) {
				item.add(new PhoneEntry(__entries.get(i).getId(), __entries.get(i).getFirstname(),
						__entries.get(i).getLastname(), __entries.get(i).getPhone()));
			}
		}
		return item;
	}

	// REQ 6
	@Override
	public List<PhoneEntry> listEntries(String phBook) {

		List<PhoneEntry> item = new ArrayList<PhoneEntry>();
		List<PhoneEntry> item2 = new ArrayList<PhoneEntry>();
		for (int i = 0; i <= __bookSImpl.size() - 1; i++) {
			if (__bookSImpl.get(i).getBook().equals(phBook)) {
				item.add(new PhoneEntry(__bookSImpl.get(i).getId(), __bookSImpl.get(i).getFirstname(),
						__bookSImpl.get(i).getLastname(), __bookSImpl.get(i).getPhone()));
			}
		}
		/*
		 * int index=bookEntries.get(ph for (PhoneBook value :
		 * bookEntries.get(phBook).getBook().con) { if
		 * (value.getBook().containsKey(phBook)) { int
		 * index=value.getBook().get(phBook).getId();
		 * //item2.add(value.getBook().get(index)); item2.add((PhoneEntry)
		 * value.getBook().get(index)); //result = id; } }
		 */
		return item;

	}

	// REQ 7
	/*
	 * @Override public String[] getSet(String bookName, String firstname, String
	 * lastname) { String[] holder = new String[10]; holder[0] = "testy test";
	 * 
	 * if (bookEntries.containsKey(bookName)) { ArrayList<PhoneEntry> pe = new
	 * ArrayList<PhoneEntry>(); pe = bookEntries.get(bookName); // for(int
	 * i=0;i<=pe.size()-1;i++) { //
	 * if(bookEntries.get(bookName).contains(firstname)||bookEntries.get(bookName).
	 * contains(lastname)) // { int t =
	 * pe.get(0).toString().lastIndexOf(firstname);// { holder[0] =
	 * pe.get(0).toString(); // .get(i).toString();
	 * 
	 * }
	 * 
	 * return holder; }
	 */

	// REQ 4
	@Override
	public boolean updateEntry(String userId, String fname, String lname) { // userId is index phoneEntries map
		String userPhone = __entries.get(Integer.parseInt(userId)).getPhone();
		Integer inty = Integer.valueOf(userId);
		int j = 1;
		boolean result = false;
		if (phoneEntries.containsKey(userId)) {
			__entries.get(Integer.parseInt(userId)).setFirstname(fname);
			__entries.get(Integer.parseInt(userId)).setLastname(lname);
			phoneEntries.get(userId).setFirstname(fname);
			phoneEntries.get(userId).setLastname(lname);
			// iterate through phone books find matching id then change fname and lname
			// phone book not being used. go with phoneEntry and find a way to match userId
			// with book name or
			// if you can use userId in some way to set names, maybe in the if statement if
			// phonebook contains element userId
			result = true;
			// end outer if

			for (int i = 0; i <= unlistedEntries.size() - 1; i++) {
				if (unlistedEntries.get(i).getId() == inty) {
					unlistedEntries.get(i).setFirstname(fname);
					unlistedEntries.get(i).setLastname(lname);
					result = true;
					j = 0;
				} else {
					result = false;
				}
			} // end for
		} // end if
		else if (phoneBook.containsKey(userId)) {

		} else {
			result = false;
		}
		return result;
	} // end updateEntry

	// REQ 3 add entry based on phone number, remove phone number from unlisted
	// array. Phone number must be from unlisted

	public int addToBook(String bookName, String userId) {
		int result = 0;
		// fist check if user even exists, then check if user exists as unlisted
		int userId2 = Integer.parseInt(userId);
		if (__entries.get(userId2).getId() == userId2) {
			// if user exist check if user is unlisted if so add entry to book
			if (unlistedEntries.get(userId2).getId() == userId2) {
				// unlistedEntries.get(userId2).setBook(bookName);
				// if book exist add to book
				if (bookEntries.containsKey(bookName)) {
					bookEntries.get(bookName).addToBook(String.valueOf(userId), new PhoneEntry(
							unlistedEntries.get(userId2).getId(), unlistedEntries.get(userId2).getFirstname(),
							unlistedEntries.get(userId2).getLastname(), unlistedEntries.get(userId2).getPhone()));

					__bookSImpl.add(unlisted.get(userId));
					String loc = String.valueOf(userId);

					bookEntries.put(bookName, bookEntries.get(bookName));
					if (unlisted.containsKey(loc)) {
						unlisted.remove(loc);

					}

				}
				// if book does not exist create new book
				else {

					PhoneBook newBook = new PhoneBook(userId, unlisted.get(userId));
					bookEntries.put(bookName, newBook);
					bookEntries.get(bookName).getBook().get(userId).setBook(bookName);
					__bookSImpl.add(unlisted.get(userId));

					String loc = String.valueOf(userId);
					if (unlisted.containsKey(loc)) {
						unlisted.remove(loc);

					}

				} // alert entry was added to book
				result = 1;
			} // end inner if
				// return error cuz user already in phonebook
			else {
				result = 0;
			}
		}
		return result;
	} // end addToBook

	// REQ 5
	@Override
	public int removeEntry(int id) {
		int result = -2;
		// int index = -1;
		for (int i = __entries.size() - 1; i >= 0; i--) {
			if (__entries.get(i).getId() == id) {
				// index = i;
				break;
			}
		}

		String loc = String.valueOf(id);
		if (unlisted.containsKey(loc)) {
			unlisted.remove(loc);
			result = id;
		}
		for (PhoneBook value : bookEntries.values()) {
			if (value.getBook().containsKey(loc)) {
				value.getBook().remove(loc);
				result = id;
			}
		}

		return result;
	} // end removeEntry method

	// REQ 2
	@Override
	public int createEntry(String fname, String lname, String phoneNum) {

		int result = -2; // if phone exists return -2, -1 for error, everything else good including 0
							// (use index as result if entry created)
		int index = -1;
		for (int i = __entries.size() - 1; i >= 0; i--) { // start from end and work back because if phone was removed
															// and added back it's index will be at a later position.
			if (__entries.get(i).getPhone().equals(phoneNum)) {
				index = i;
				break;
			}
		}

		String loc = String.valueOf(index); // is phone found in system based on index either unlisted or listed
		if (index >= 0) {
			for (int i = 0; i <= unlistedEntries.size() - 1; i++) {
				if (unlistedEntries.get(i).getPhone().equals(phoneNum)) {
					result = -2;
					break;
				}
			} // end for
		}

		else if (phoneEntries.containsKey(loc)) {
			result = -2;
		}

		else {
			__entries.add(new PhoneEntry(id, fname, lname, phoneNum));
			unlistedEntries.add(new PhoneEntry(id, fname, lname, phoneNum));
			phoneEntries.put(Integer.toString(id), __entries.get(id));
			unlisted.put(Integer.toString(id), __entries.get(id));

			result = id;
			id += 1;
		}

		return result;
	}

	@Override
	public void savePhoneBook(String fname) {
		// TODO Auto-generated method stub

	}

	@Override
	public int createEntry(int id, String fname, String lname, String phone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getSet(String bookName, String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

}
