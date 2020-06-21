package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.model.User;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private PasswordService pwService;
    @Autowired
    private AuthenticationService authService;
    
    
    @GetMapping("/login")
    public String showLogin(Model model) {
        System.out.println("Getting login!");
    	model.addAttribute("credentials", new Credentials());
        model.addAttribute("loginError", false);

        return "login.html";
    }

    @PostMapping("/login")
    public String signIn(@ModelAttribute Credentials credentials) {
        System.out.println("signing in");
//        Optional<User> foundUser = authService.signInUser(credentials);

//        if (foundUser.isPresent()) {
//				
//        }
        return "";
    }

    @GetMapping("/failedLogin")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("credentials", new Credentials());
        return "login.html";
    }

}
