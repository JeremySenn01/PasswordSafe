package ch.bbw.PasswordSafe.service;

import ch.bbw.PasswordSafe.model.Entry;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@SessionScope
public class PasswordService {

    private List<Entry> entries = new ArrayList<Entry>();


    public List<Entry> getAllEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        entry.setId(UUID.randomUUID().toString());
        entries.add(entry);
    }

    public void removeEntry(String id) {
        //find and remove the entry
        entries.removeIf(e -> e.getId().equals(id));
    }

    public void updateEntry(Entry entry) {
        for (Entry e : entries) {
            if (e.getId().equals(entry.getId())) {
                int index = entries.indexOf(e);
                entries.remove(index);
                entries.add(index, entry);
            }
        }
    }

    public Optional<Entry> getEntryById(String id) {
        return entries.stream().filter(e -> e.getId().equals(id)).findAny();
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
