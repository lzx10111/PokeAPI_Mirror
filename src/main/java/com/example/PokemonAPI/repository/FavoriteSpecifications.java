package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.entity.Favorite;
import org.springframework.data.jpa.domain.Specification;

public class FavoriteSpecifications {
    public static Specification<Favorite> idLike(String id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null || id.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("userId"), Integer.parseInt(id));
        };
    }
}
