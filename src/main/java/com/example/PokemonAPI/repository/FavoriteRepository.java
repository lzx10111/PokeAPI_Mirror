package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.dto.Favorite;
import com.example.PokemonAPI.model.util.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId>, JpaSpecificationExecutor<Favorite> {
    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.id.pokemonId=?1")
    int totalFavoritePokemonCount(Integer pokemonId);
}
