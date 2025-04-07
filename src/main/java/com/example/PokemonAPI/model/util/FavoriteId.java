package com.example.PokemonAPI.model.util;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoriteId implements Serializable {

    private Integer userId;
    private Integer pokemonId;

    public FavoriteId(Integer userId, Integer pokemonId) {
        this.userId = userId;
        this.pokemonId = pokemonId;
    }

    public FavoriteId() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    @Override
    public String toString() {
        return "FavoriteId{" +
                "userId=" + userId +
                ", pokemonId=" + pokemonId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(pokemonId, that.pokemonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, pokemonId);
    }
}
