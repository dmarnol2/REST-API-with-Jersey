package edu.asupoly.ser422.lab3.model;

import java.io.*;
import java.util.*;

public class PhoneBook {
	public static final String DEFAULT_FILENAME = "phonebook.txt";

	private Map<String, PhoneEntry> _pbook = new HashMap<String, PhoneEntry>();

	private Map<String, PhoneBook> _pbookMap = new HashMap<String, PhoneBook>();

	private String _id;
	private PhoneEntry phoneEntry;
	private String _lastName;
	private String _firstName;
	ArrayList<PhoneEntry> entry;

	public PhoneBook(String id, PhoneEntry entry) {
		_id = id;
		phoneEntry = entry;
		addToBook(_id, phoneEntry);

	}

	public boolean addToBook(String name, PhoneEntry entry) {

		_pbook.put(name, entry);
		return true;
	}

	public boolean deleteEntry(String entry) {
		_pbook.remove(entry);
		return true;
	}

	public Map<String, PhoneEntry> getBook() {
		return _pbook;
	}

	public PhoneBook() throws IOException {
		this(DEFAULT_FILENAME);
	}

	public PhoneBook(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}

	public PhoneBook(InputStream is) throws IOException {
		this(new BufferedReader(new InputStreamReader(is)));
	}

	private PhoneBook(BufferedReader br) throws IOException {
		String name = null;
		String lname = null;
		String phone = null;
		int id;

		try {
			String nextLine = null;
			while ((nextLine = br.readLine()) != null) {
				name = nextLine;
				lname = br.readLine();
				phone = br.readLine();
				addEntry(_id, name, lname, phone);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error process phonebook");
			throw new IOException("Could not process phonebook file");
		}
	}

	public boolean removeFromBook(PhoneEntry entry) {
		return false;
	}

	public String[] listEntries() {
		String[] rval = new String[_pbook.size()];
		int i = 0;
		PhoneEntry nextEntry = null;
		for (Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}

	public void savePhoneBook(String fname) {
		try {
			System.out.println("Opening " + fname);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fname));
			System.out.println("...done");
			String[] entries = listEntries();
			for (int i = 0; i < entries.length; i++)
				pw.println(entries[i]);

			pw.close();
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("Error saving phone book");
		}
	}

	public void editEntry(String phone, String fname, String lname) {
		PhoneEntry pentry = _pbook.get(phone);
		pentry.changeName(fname, lname);
	}

	public void addEntry(String id, String fname, String lname, String phone) {
		// addEntry(phone, new PhoneEntry(id, fname, lname, phone));
	}

}
