package com.uniovi.sdi.sdi2526611spring.controllers;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import com.uniovi.sdi.sdi2526611spring.entities.User;
import com.uniovi.sdi.sdi2526611spring.services.MarksService;
import com.uniovi.sdi.sdi2526611spring.services.RolesService;
import com.uniovi.sdi.sdi2526611spring.services.SecurityService;
import com.uniovi.sdi.sdi2526611spring.services.UsersService;
import com.uniovi.sdi.sdi2526611spring.validators.SignUpFormValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UsersController {
    private final UsersService usersService;
    private final SecurityService securityService;
    private final SignUpFormValidator signUpFormValidator;
    private final RolesService rolesService;
    private final MarksService marksService;
    public UsersController(UsersService usersService, SecurityService securityService, SignUpFormValidator signUpFormValidator, RolesService rolesService, MarksService marksService) {
        this.usersService = usersService;
        this.securityService = securityService;
        this.signUpFormValidator = signUpFormValidator;
        this.rolesService = rolesService;
        this.marksService = marksService;
    }
    @GetMapping("/user/list")
    public String getListado(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list";
    }
    @GetMapping(value = "/user/add")
    public String getUser(Model model) {
        model.addAttribute("rolesList",rolesService.getRoles());
        model.addAttribute("usersList", usersService.getUsers());
        return "user/add";
    }
    @PostMapping(value = "/user/add")
    public String setUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/user/list";
    }
    @GetMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }
    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersService.deleteUser(id);
        return "redirect:/user/list";
    }
    @GetMapping(value = "/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping(value = "/user/edit/{id}")
    public String setEdit(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id); // Aseguramos que el ID es el correcto
        usersService.editUser(user);
        return "redirect:/user/list";
    }

    //SEGURIDAD

    @PostMapping("/signup")
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:home";
    }

    @GetMapping( "/login")
    public String login() {
        return "login";
    }
    @GetMapping("/home")
    public String home(Model model, Pageable pageable, Principal principal) {
        String dni = principal.getName();
        User activeUser = usersService.getUserByDni(dni);

        Page<Mark> marks = marksService.getMarksForUser(pageable, activeUser);

        model.addAttribute("marksList", marks.getContent());
        model.addAttribute("page", marks);

        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping("/user/details/{id}")
    public String getDetails(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }

    @GetMapping("/user/list/update")
    public String updateList(Model model){
        model.addAttribute("usersList", usersService.getUsers());
        return "fragments/table :: userTable";
    }
}
