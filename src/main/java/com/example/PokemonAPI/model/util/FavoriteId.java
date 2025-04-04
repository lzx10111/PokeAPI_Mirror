package com.example.PokemonAPI.model.util;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoriteId implements Serializable {

    private Long userId;
    private Long pokemonId;

    public FavoriteId(Long userId, Long pokemonId) {
        this.userId = userId;
        this.pokemonId = pokemonId;
    }

    public FavoriteId() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
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
