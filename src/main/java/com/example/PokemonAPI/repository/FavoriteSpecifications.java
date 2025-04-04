package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.dto.Favorite;
import org.springframework.data.jpa.domain.Specification;

public class FavoriteSpecifications {
    public static Specification<Favorite> idLike(String id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null || id.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("id").get("userId"), Long.parseLong(id));
        };
    }
}
