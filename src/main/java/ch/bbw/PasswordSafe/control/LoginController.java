package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.service.PasswordService;
import ch.bbw.PasswordSafe.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private PasswordService pwService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        return "";
    }

    @PostMapping("/login")
    public String signIn(@ModelAttribute Credentials credentials) {
        return "";
    }
}
