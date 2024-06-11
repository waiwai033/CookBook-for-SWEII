package dao.mappers;

public class RecipeIngredient {
    private int recipeId;
    private String recipeName;
    private int ingredientId;
    private String ingredientName;
    private int quantity;
    private String unit;

    public RecipeIngredient(int recipeId, String recipeName, int ingredientId, String ingredientName, int quantity, String unit) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

}
