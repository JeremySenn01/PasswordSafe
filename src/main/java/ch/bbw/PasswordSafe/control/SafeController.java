package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class SafeController {

    @Autowired
    private PasswordService pwService;
    @Autowired
    private AuthenticationService authService;

    @GetMapping("/safe")
    public String getSafe(Model model) {
        if (!authService.hasCurrentUser()) {
            return "redirect:/login";
        }
        List<Entry> entries = pwService.getAllEntries();
        model.addAttribute("allEntries", entries);

        return "passwordmanager";
    }

    @GetMapping("/newEntry")
    public String getNewEntryForm(Model model) {
        model.addAttribute("newEntry", new Entry());

        return "addentry";

    }

    @PostMapping("/submitEntry")
    public String addEntry(@Valid Entry newEntry, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("error!!!");
            model.addAttribute("newEntry", newEntry);
            return "addentry";
        }

        pwService.addEntry(newEntry);
        return "redirect:/safe";
    }

    @GetMapping("/updateEntry/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<Entry> entry = this.pwService.getEntryById(id);
        if (entry.isPresent()) {
            Entry e = entry.get();
            e.setId(id);
            model.addAttribute("updateEntry", e);
            return "updateEntry";
        }
        //unexpected error
        return "redirect:/safe";
    }

    @PostMapping("/update/{id}")
    public String updateEntry(@Valid Entry updateEntry, @PathVariable("id") String id) {
        updateEntry.setId(id);
        this.pwService.updateEntry(updateEntry);
        return "redirect:/safe";

    }

    @GetMapping("/removeEntry/{id}")
    public String deleteEntry(@PathVariable("id") String id) {
        this.pwService.removeEntry(id);
        return "redirect:/safe";
    }

}
