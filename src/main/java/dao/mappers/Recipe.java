package dao.mappers;

import java.io.Serializable;

/**
 * The type Recipe.
 *
 * @author Chenyi He
 */

public class Recipe implements Serializable {
    private int recipeId;
    private String recipeName;
    private String description;
    private String imageUrl;
    private String category;
    private String cookTime;

    public Recipe() {}

    // 全参数构造函数
    public Recipe(int recipeId, String recipeName, String description, String imageUrl, String category, String cookTime) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.cookTime = cookTime;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }
}
