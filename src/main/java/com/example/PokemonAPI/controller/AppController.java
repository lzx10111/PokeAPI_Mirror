package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.dto.User;
import com.example.PokemonAPI.model.pojo.SearchPokemon;
import com.example.PokemonAPI.service.PokemonService;
import com.example.PokemonAPI.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    UserService userService;
    PokemonService pokemonService;

    public AppController(UserService userService, PokemonService pokemonService) {
        this.userService = userService;
        this.pokemonService = pokemonService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isUser", userService.isUser());

        if (userService.isUser()) {
            return "redirect:/index";
        }

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", userService.emptyUser());
        return "register";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isUser", userService.isUser());
        model.addAttribute("userFavorites", userService.findAllFavorites());
        model.addAttribute("listPokemons", pokemonService.getListPokemonAll());
        model.addAttribute("totalPokemons", pokemonService.getTotalPokemons());
        model.addAttribute("searchPokemon", new SearchPokemon());
        model.addAttribute("rangesFilter", pokemonService.getMaxMin());

        return "index";
    }

    @PostMapping("/register")
    public String newUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.newUser(user);
        return "redirect:/login";
    }

    @GetMapping("/filterResults")
    public String filterResults() {
        return "index";
    }

    @GetMapping("/loader")
    public String loader(Model model) {
        return "fragments/profileModalFrag :: modalContentLoader";
    }

    @PostMapping("/filter")
    public String filter(@Validated @ModelAttribute("searchPokemon") SearchPokemon searchPokemon,
                         BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listPokemons", pokemonService.getListPokemonAll());
            model.addAttribute("isAdmin", userService.isAdmin());
            model.addAttribute("isUser", userService.isUser());
            model.addAttribute("userFavorites", userService.findAllFavorites());
            model.addAttribute("totalPokemons", pokemonService.getTotalPokemons());
            model.addAttribute("rangesFilter", pokemonService.getMaxMin());

            return "index";
        }

        redirectAttributes.addFlashAttribute("listPokemons", pokemonService.getListPokemonAllFiltered(searchPokemon));
        redirectAttributes.addFlashAttribute("isAdmin", userService.isAdmin());
        redirectAttributes.addFlashAttribute("isUser", userService.isUser());
        redirectAttributes.addFlashAttribute("userFavorites", userService.findAllFavorites());
        redirectAttributes.addFlashAttribute("totalPokemons", pokemonService.getTotalPokemons());
        redirectAttributes.addFlashAttribute("searchPokemon", new SearchPokemon());
        redirectAttributes.addFlashAttribute("rangesFilter", pokemonService.getMaxMin());

        return "redirect:/filterResults";
    }
}
