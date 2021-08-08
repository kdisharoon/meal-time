package com.techelevator.model;

public class Ingredient {

    private long ingredientId;
    private String ingredientName;
    private String measurementUnit;
    private double measurementAmount;

    public Ingredient(){}

    public Ingredient(long ingredientId, String ingredientName, String measurementUnit, double measurementAmount){
        this.ingredientId=ingredientId;
        this.ingredientName=ingredientName;
        this.measurementAmount=measurementAmount;
        this.measurementUnit=measurementUnit;

    }

    public double getMeasurementAmount() {
        return measurementAmount;
    }

    public void setMeasurementAmount(double measurementAmount) {
        this.measurementAmount = measurementAmount;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
