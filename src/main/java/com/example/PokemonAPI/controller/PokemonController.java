package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.entity.Favorite;
import com.example.PokemonAPI.service.PokemonService;
import com.example.PokemonAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    PokemonService pokemonService;
    UserService userService;

    public PokemonController(PokemonService pokemonService, UserService userService) {
        this.pokemonService = pokemonService;
        this.userService = userService;
    }

    @GetMapping("/info")
    public String info(@RequestParam("idInfo") Integer id, Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isUser", userService.isUser());
        model.addAttribute("pokemon", pokemonService.getPokemonByID(id));
        model.addAttribute("count", userService.getTotalFavoritePokemonCount(id));
        model.addAttribute("userFavorites", userService.findAllFavorites());

        return "info";
    }

    @PostMapping("/pokemonCount")
    public ResponseEntity<String> pokemonCount(@RequestBody Favorite favorite) {
        return new ResponseEntity<>(Long.toString(userService.getTotalFavoritePokemonCount(favorite.getPokemonId())), HttpStatus.OK);
    }

    @PostMapping("/addGroup")
    public String addGroup(@RequestParam("addGroupStart") Integer start,
                           @RequestParam("addGroupEnd") Integer end) {
        pokemonService.saveListPokemon(start, end);

        return "redirect:/user/edition";
    }

    @PostMapping("/deleteGroup")
    public String deleteGroup(@RequestParam("deleteGroupStart") String start,
                           @RequestParam("deleteGroupEnd") String end) {
        pokemonService.deleteListPokemon(start, end);

        return "redirect:/user/edition";
    }

    @PostMapping("/addSpecific")
    public String addSpecific(@RequestParam("addSpecificId") String id,
                           @RequestParam("addSpecificName") String name) {

        if (!id.isEmpty()) {
            pokemonService.savePokemonWithIdFromAPI(id);
        }
        else if (!name.isEmpty()) {
            pokemonService.savePokemonWithNameFromAPI(name);
        }

        return "redirect:/user/edition";
    }

    @PostMapping("/deleteSpecific")
    public String deleteSpecific(@RequestParam("deleteSpecificId") String id,
                              @RequestParam("deleteSpecificName") String name) {

        if (!id.isEmpty()) {
            pokemonService.deletePokemonWithId(id);
        }
        else if (!name.isEmpty()) {
            pokemonService.deletePokemonWithName(name);
        }

        return "redirect:/user/edition";
    }
}
