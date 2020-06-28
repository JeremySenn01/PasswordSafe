package ch.bbw.PasswordSafe.service;

import ch.bbw.PasswordSafe.model.Entry;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SessionScope
public class PasswordService {

    private List<Entry> entries = new ArrayList<Entry>();


    public List<Entry> getAllEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void removeEntry(String id) {
        //find and remove the entry
        entries.removeIf(e -> e.getId().equals(id));
    }

    public void updateEntry(Entry entry) {
        entries = entries.stream()
                .filter(e -> e.getId().equals(entry.getId()))
                .map(e -> e = entry)
                .collect(Collectors.toList());
    }

    public Optional<Entry> getEntryById(String id) {
       return entries.stream().filter(e -> e.getId().equals(id)).findAny();
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
