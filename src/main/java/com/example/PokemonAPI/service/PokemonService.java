package com.example.PokemonAPI.service;

import com.example.PokemonAPI.model.dto.MaxMin;
import com.example.PokemonAPI.model.dto.Pokemon;
import com.example.PokemonAPI.model.pojo.SearchPokemon;
import com.example.PokemonAPI.repository.PokemonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.PokemonAPI.repository.PokemonSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;
    private final PokemonRepository pokemonRepository;
    private String count;

    public PokemonService(RestTemplate restTemplate, PokemonRepository pokemonRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
    }

    @PostConstruct
    private void setCount() {
        this.count = getTotalCountPokemonAPI();
    }

    public String getCount() {
        return count;
    }

    public String getTotalCountPokemonAPI() {
        String apiUrlBase = "https://pokeapi.co/api/v2/pokemon-species/?limit=0";
        ResponseEntity<String> response;

        try {
            response = restTemplate.exchange(apiUrlBase, HttpMethod.GET, null, String.class);
        } catch (Exception e) {
            return "1000";
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree;

        try {
            tree = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode node = tree.get("count");

        return String.valueOf(node.intValue());
    }

    public List<Pokemon> getListPokemonAPI(Integer end) {

        List<Pokemon> list = new ArrayList<>();

        for (int i=1; i <= end; i++) {
            String apiUrlBase = "https://pokeapi.co/api/v2/pokemon/";
            String urlApi = apiUrlBase + i;
            ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(
                    urlApi, HttpMethod.GET, null, Pokemon.class);
            list.add(responseEntity.getBody());
        }

        return list;
    }

    public List<Pokemon> getListPokemonAPI(Integer start, Integer end) {

        List<Pokemon> list = new ArrayList<>();

        for (int i=start; i <= end; i++) {
            String apiUrlBase = "https://pokeapi.co/api/v2/pokemon/";
            String urlApi = apiUrlBase + i;
            ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(
                    urlApi, HttpMethod.GET, null, Pokemon.class);
            list.add(responseEntity.getBody());
        }

        return list;
    }

    public Pokemon getPokemonByIdAPI(Integer id) {
        String apiUrlBase = "https://pokeapi.co/api/v2/pokemon/";
        String urlApi = apiUrlBase + id;
        ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(
                urlApi, HttpMethod.GET, null, Pokemon.class);

        return responseEntity.getBody();
    }

    public Pokemon getPokemonByNameAPI(String name) {
        String apiUrlBase = "https://pokeapi.co/api/v2/pokemon/";
        String urlApi = apiUrlBase + name;
        ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(
                urlApi, HttpMethod.GET, null, Pokemon.class);

        return responseEntity.getBody();
    }

    public List<Pokemon> getListPokemonAll() {
        return pokemonRepository.findAll();
    }

    public List<Pokemon> getListPokemonAllFiltered(SearchPokemon searchPokemon) {
        return pokemonRepository.findAll(where(nameLike(searchPokemon.getNameFilter()).
                and(idLike(searchPokemon.getIdFilter())).
                and(heightLike(searchPokemon.getHeightFilter())).
                and(weightLike(searchPokemon.getWeightFilter()))));
    }

    public Pokemon getPokemonByID(Integer id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        return pokemon.orElse(new Pokemon());

    }

    public void savePokemonWithIdFromAPI(String id) {
        pokemonRepository.save(getPokemonByIdAPI(Integer.parseInt(id)));
    }

    public void savePokemonWithNameFromAPI(String name) {
        pokemonRepository.save(getPokemonByNameAPI(name));
    }

    public void saveListPokemon(Integer start, Integer end) {
        List<Pokemon> listPokemon = getListPokemonAPI(start, end);
        pokemonRepository.saveAll(listPokemon);
    }

    public void deleteListPokemon(String start, String end) {
        pokemonRepository.delete(where(idGreaterThanOrEqualTo(start).
                and(idLessThanOrEqualTo(end))));
    }

    public void deletePokemonWithId(String id) {
        pokemonRepository.deleteById(Integer.valueOf(id));
    }

    public void deletePokemonWithName(String name) {
        pokemonRepository.deleteByName(name);
    }


    public String getTotalPokemons() {
        return String.valueOf(pokemonRepository.count());
    }

    public MaxMin getMaxMin() {
        MaxMin maxMin = pokemonRepository.getMaxMin();

        if (maxMin.isFieldsNull()) {
            return new MaxMin(0,0,0,0);
        }

        return pokemonRepository.getMaxMin();
    }
}
