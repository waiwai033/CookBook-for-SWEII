package dao.mappers;

import java.io.Serializable;

public class Recipe implements Serializable {
    private int recipeId;
    private String recipeName;
    private int serveAmount;
    private int preparationTime;
    private int cookingTime;
    private String imageUrl;
    public Recipe() {
    }

    public Recipe(int recipeId, String recipeName, int serveAmount, int preparationTime, int cookingTime, String imageUrl) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.serveAmount = serveAmount;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.imageUrl = imageUrl;
    }

    public int getRecipeId() {
        return this.recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServeAmount() {
        return this.serveAmount;
    }

    public void setServeAmount(int serveAmount) {
        this.serveAmount = serveAmount;
    }

    public int getPreparationTime() {
        return this.preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", serveAmount=" + serveAmount +
                ", preparationTime=" + preparationTime +
                ", cookingTime=" + cookingTime +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}