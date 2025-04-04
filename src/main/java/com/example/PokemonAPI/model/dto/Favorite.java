package com.example.PokemonAPI.model.dto;

import com.example.PokemonAPI.model.util.FavoriteId;
import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorite {

    @EmbeddedId
    private FavoriteId id = new FavoriteId();

    public Favorite() {
    }

    public FavoriteId getId() {
        return id;
    }

    public void setId(FavoriteId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                '}';
    }
}
