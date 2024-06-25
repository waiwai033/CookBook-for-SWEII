package dao.mappers;


public class RecipeIngredient {
    private int recipeId;
    private String name;
    private Float quantity;
    private String unit;
    private String description;

    public RecipeIngredient(int recipeId, String name, Float quantity, String unit, String description) {
        this.recipeId = recipeId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
    }
    public RecipeIngredient(RecipeIngredient ingredient) {
        this.name = ingredient.name;
        this.quantity = ingredient.quantity;
        this.unit = ingredient.unit;
        this.description = ingredient.description;
        this.recipeId = ingredient.recipeId;
    }


    // Getters and Setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
