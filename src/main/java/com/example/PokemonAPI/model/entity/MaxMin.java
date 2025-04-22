package com.example.PokemonAPI.model.entity;

import jakarta.persistence.*;

@Entity
public class MaxMin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer maxHeight;

    @Column
    private Integer minHeight;

    @Column
    private Integer maxWeight;

    @Column
    private Integer minWeight;

    public MaxMin(Object[] list) {
        this.maxHeight = (Integer) list[0];
        this.minHeight = (Integer) list[1];
        this.maxWeight = (Integer) list[2];
        this.minWeight = (Integer) list[3];
    }

    public MaxMin(Integer maxHeight, Integer minHeight, Integer maxWeight, Integer minWeight) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.maxWeight = maxWeight;
        this.minWeight = minWeight;
    }

    public MaxMin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Integer minWeight) {
        this.minWeight = minWeight;
    }

    public String getStringMaxHeight() {
        return maxHeight.toString();
    }

    public String getStringMinHeight() {
        return minHeight.toString();
    }

    public String getStringMaxWeight() {
        return maxWeight.toString();
    }

    public String getStringMinWeight() {
        return minWeight.toString();
    }

    public boolean isFieldsNull() {
        return minHeight == null || minWeight == null || maxHeight == null || maxWeight == null;
    }

    @Override
    public String toString() {
        return "MaxMin{" +
                "id=" + id +
                ", maxHeight=" + maxHeight +
                ", minHeight=" + minHeight +
                ", maxWeight=" + maxWeight +
                ", minWeight=" + minWeight +
                '}';
    }
}
