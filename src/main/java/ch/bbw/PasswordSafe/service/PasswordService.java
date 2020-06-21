package ch.bbw.PasswordSafe.service;

import java.util.ArrayList;
import java.util.List;

import ch.bbw.PasswordSafe.model.Entry;

@org.springframework.stereotype.Service
public class PasswordService {

	//decripted entrie list 
	public List<Entry> entries = new ArrayList<Entry>();
	
	
	public List<Entry> getAllEntries(){
		if (entries.isEmpty()) {
			return null;
		}
		return entries;
	}

	public void addEntry(Entry entry) {
		entries.add(entry);
	}
}
