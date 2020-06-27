package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        System.out.println("entries.length = " + entries.size());
        model.addAttribute("allEntries", entries);

        return "passwordmanager";
    }

    @GetMapping("/newEntry")
    public String getNewEntryForm(Model model) {
        model.addAttribute("newEntry", new Entry());
        return "addentry";

    }

    @PostMapping("/submitEntry")
    public String addEntry(@ModelAttribute Entry newEntry, BindingResult bindingResult, Model model) {
        System.out.println("newEntry = " + newEntry);
        if (bindingResult.hasErrors()) {
            System.out.println("has errors!");
            model.addAttribute("newEntry", newEntry);
            return "addentry";
        }

        pwService.addEntry(newEntry);
        return "redirect:/safe";
    }

    @PutMapping("/")
    public String updateEntry(@Valid @ModelAttribute Entry updateEntry) {
        return null;
    }

    @DeleteMapping("/")
    public String deleteEntry(@ModelAttribute Entry deleteEntry) {
        return null;
    }

}
