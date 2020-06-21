package ch.bbw.PasswordSafe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import ch.bbw.PasswordSafe.model.Entry;

@Service
@SessionScope
public class PasswordService {

	private List<Entry> entries = new ArrayList<Entry>();	
	
	public List<Entry> getAllEntries(){
		return entries;
	}

	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	
	public void setEntries(List<Entry> entries) {
		System.out.println("new entries size: " + entries.size());
		this.entries = entries;
	}
}
