package ch.bbw.PasswordSafe.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;

import javax.validation.Valid;

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
    public String signIn(@Valid @ModelAttribute Credentials credentials) {
        System.out.println("signing in: " + credentials.getUsername() + " / " + credentials.getPassword());
        boolean foundUser = authService.signInUser(credentials);

        //Login worked
        if (foundUser) {
        	System.out.println("got em");
            return "redirect:/safe";
        }
    	System.out.println("aint got em");
        //login didn't work
        return "redirect:/failedLogin";
    }

	@GetMapping("/failedLogin")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("credentials", new Credentials());
		return "login.html";
	}
	
	@GetMapping("/logout")
	public String logout() {
		this.authService.logout();
		return "redirect:/login";
	}

}
