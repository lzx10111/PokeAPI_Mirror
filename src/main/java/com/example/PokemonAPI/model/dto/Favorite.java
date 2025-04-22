package com.example.PokemonAPI.model.dto;

import com.example.PokemonAPI.model.util.FavoriteId;
import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
@IdClass(FavoriteId.class)
public class Favorite {
    @Id
    private Integer userId;
    @Id
    private Integer pokemonId;

    public Favorite() {
    }

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
        return "Favorite{" +
                "userId=" + userId +
                ", pokemonId=" + pokemonId +
                '}';
    }
}
