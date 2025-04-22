package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.entity.MaxMin;
import com.example.PokemonAPI.model.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>, JpaSpecificationExecutor<Pokemon> {

    void deleteByName(String name);

    @Query(value = "SELECT NEW MaxMin(max(p.height), min(p.height), max(p.weight), min(p.weight)) FROM Pokemon p")
    MaxMin getMaxMin();
}
