package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.dto.Pokemon;
import org.springframework.data.jpa.domain.Specification;

public class PokemonSpecifications {

    public static Specification<Pokemon> nameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    public static Specification<Pokemon> idLike(String id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null || id.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("id"), Long.parseLong(id));
        };
    }

    public static Specification<Pokemon> heightLike(String height) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("height"), Integer.parseInt(height));
    }

    public static Specification<Pokemon> weightLike(String weight) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("weight"), Integer.parseInt(weight));
    }

    public static Specification<Pokemon> idGreaterThanOrEqualTo(String id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null || id.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), Long.parseLong(id));
        };
    }

    public static Specification<Pokemon> idLessThanOrEqualTo(String id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null || id.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("id"), Long.parseLong(id));
        };
    }
}
