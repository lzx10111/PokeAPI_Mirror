package com.example.PokemonAPI.model.pojo;

import com.example.PokemonAPI.annotation.IntegerPositive;
import jakarta.validation.constraints.Size;

public class SearchPokemon {
    @Size(max = 25, message = "{nameFilter.size}")
    private String nameFilter;

    @IntegerPositive(allowEmptyValue = true, message = "{idFilter.type}")
    @Size(max = 10, message = "{idFilter.size}")
    private String idFilter;

    @IntegerPositive(allowEmptyValue = true, message = "{heightFilter.type}")
    @Size(max = 10, message = "{heightFilter.size}")
    private String heightFilterMax;

    @IntegerPositive(allowEmptyValue = true, message = "{heightFilter.type}")
    @Size(max = 10, message = "{heightFilter.size}")
    private String heightFilterMin;

    @IntegerPositive(allowEmptyValue = true, message = "{weightFilter.type}")
    @Size(max = 10, message = "{heightFilter.size}")
    private String weightFilterMax;

    @IntegerPositive(allowEmptyValue = true, message = "{weightFilter.type}")
    @Size(max = 10, message = "{heightFilter.size}")
    private String weightFilterMin;

    public SearchPokemon(String nameFilter, String idFilter, String heightFilterMax, String heightFilterMin, String weightFilterMax, String weightFilterMin) {
        this.nameFilter = nameFilter;
        this.idFilter = idFilter;
        this.heightFilterMax = heightFilterMax;
        this.heightFilterMin = heightFilterMin;
        this.weightFilterMax = weightFilterMax;
        this.weightFilterMin = weightFilterMin;
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

    public String getHeightFilterMax() {
        return heightFilterMax;
    }

    public void setHeightFilterMax(String heightFilterMax) {
        this.heightFilterMax = heightFilterMax;
    }

    public String getHeightFilterMin() {
        return heightFilterMin;
    }

    public void setHeightFilterMin(String heightFilterMin) {
        this.heightFilterMin = heightFilterMin;
    }

    public String getWeightFilterMax() {
        return weightFilterMax;
    }

    public void setWeightFilterMax(String weightFilterMax) {
        this.weightFilterMax = weightFilterMax;
    }

    public String getWeightFilterMin() {
        return weightFilterMin;
    }

    public void setWeightFilterMin(String weightFilterMin) {
        this.weightFilterMin = weightFilterMin;
    }

    @Override
    public String toString() {
        return "SearchPokemon{" +
                "nameFilter='" + nameFilter + '\'' +
                ", idFilter='" + idFilter + '\'' +
                ", heightFilterMax='" + heightFilterMax + '\'' +
                ", heightFilterMin='" + heightFilterMin + '\'' +
                ", weightFilterMax='" + weightFilterMax + '\'' +
                ", weightFilterMin='" + weightFilterMin + '\'' +
                '}';
    }
}
