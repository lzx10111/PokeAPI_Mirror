package com.example.PokemonAPI.model.pojo;

import com.example.PokemonAPI.annotation.IntegerPositive;
import jakarta.validation.constraints.Size;

public class SearchPokemon {
    @Size(max = 25, message = "{nameFilter.size}")
    private String nameFilter;

    @IntegerPositive(allowEmptyValue = true, message = "{idFilter.type}")
    @Size(max = 10, message = "{idFilter.size}")
    private String idFilter;

    @IntegerPositive(message = "{heightFilter.type}")
    @Size(max = 10, message = "{heightFilter.size}")
    private String heightFilter;

    @IntegerPositive(message = "{weightFilter.type}")
    @Size(max = 10, message = "{heightFilter.size}")
    private String weightFilter;

    public SearchPokemon(String nameFilter, String idFilter, String heightFilter, String weightFilter) {
        this.nameFilter = nameFilter;
        this.idFilter = idFilter;
        this.heightFilter = heightFilter;
        this.weightFilter = weightFilter;
    }

    public SearchPokemon() {
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getIdFilter() {
        return idFilter;
    }

    public void setIdFilter(String idFilter) {
        this.idFilter = idFilter;
    }

    public String getHeightFilter() {
        return heightFilter;
    }

    public void setHeightFilter(String heightFilter) {
        this.heightFilter = heightFilter;
    }

    public String getWeightFilter() {
        return weightFilter;
    }

    public void setWeightFilter(String weightFilter) {
        this.weightFilter = weightFilter;
    }
}
