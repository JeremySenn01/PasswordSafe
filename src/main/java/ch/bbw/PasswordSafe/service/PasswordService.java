package ch.bbw.PasswordSafe.service;

import java.util.List;

import ch.bbw.PasswordSafe.model.Entry;

@org.springframework.stereotype.Service
public class PasswordService {

	//decripted entrie list 
	//public List<entries> entries = new ArraList<>;
	
	
	public List<Entry> getAllEntries(){
		if (entries.isEmpty()) {
			return null;
		}
		return entries;
	}
	

	public void addEntrie(Entry entry) {
		entries.add(entry);
	}
}
