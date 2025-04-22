package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.entity.Favorite;
import com.example.PokemonAPI.model.entity.User;
import com.example.PokemonAPI.service.PokemonService;
import com.example.PokemonAPI.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;
    PokemonService pokemonService;

    public UserController(UserService userService, PokemonService pokemonService) {
        this.userService = userService;
        this.pokemonService = pokemonService;
    }

    @GetMapping("/edition")
    public String edition(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isUser", userService.isUser());
        model.addAttribute("count", pokemonService.getCount());
        return "edition";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isUser", userService.isUser());
        model.addAttribute("user", userService.getUser());
        model.addAttribute("enableSuccessMsg", false);
        model.addAttribute("enableErrorMsg", false);

        return "profile";
    }

    @GetMapping("/profileSuccess")
    public String profileSuccess() {
        return "profile";
    }

    @GetMapping("/profileError")
    public String profileError() {
//        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        return "profile";
    }

    @GetMapping("/profileModal")
    public String profileModal(@RequestParam("field") String field, Model model) {
        model.addAttribute("userModify", userService.emptyUser());

        return switch (field) {
            case "name" -> "fragments/profileModalFrag :: modalContentName";
            case "email" -> "fragments/profileModalFrag :: modalContentEmail";
            case "date" -> "fragments/profileModalFrag :: modalContentDateBirth";
            case "password" -> "fragments/profileModalFrag :: modalContentPassword";
            default -> "fragments/profileModalFrag :: modalContentName";
        };

    }

    @PostMapping("/profileModify")
    public RedirectView profileModify(@Validated @ModelAttribute("userModify") User user,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                      HttpServletResponse response) {

        Cookie cookie = new Cookie("showModal", "1");
        response.addCookie(cookie);

        if (!userService.isOnlyOneNotNullField(user)) {
            return new RedirectView("/user/profile", true);
        }

        String field = userService.getNotNullField(user);

        if (field.isEmpty()) {
            return new RedirectView("/user/profile", true);
        }

        if (bindingResult.hasFieldErrors(field)) {
            redirectAttributes.addFlashAttribute("user", userService.getUser());
            redirectAttributes.addFlashAttribute("enableErrorMsg", true);
            redirectAttributes.addFlashAttribute("nameField", field);
            redirectAttributes.addFlashAttribute("binding", bindingResult);

            return new RedirectView("/user/profileError", true);
        }

        userService.profileUpdate(user, field);

        redirectAttributes.addFlashAttribute("user", userService.getUser());
        redirectAttributes.addFlashAttribute("enableSuccessMsg", true);
        redirectAttributes.addFlashAttribute("successMsg", String.format("Se ha cambiado '%s' exitosamente.", field));

        return new RedirectView("/user/profileSuccess", true);
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favorite.setUserId(userService.getUser().getId());

        if (userService.favoriteExists(favorite)) {
            userService.deleteFavorite(favorite);
            return new ResponseEntity<>("0", HttpStatus.OK);
        }
        else {
            userService.saveFavorite(favorite);
            return new ResponseEntity<>("1", HttpStatus.OK);
        }
    }
}
