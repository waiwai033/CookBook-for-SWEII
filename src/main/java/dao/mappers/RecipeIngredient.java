package dao.mappers;

/**
 * Represents an ingredient used in a recipe, containing details such as recipe ID,
 * ingredient name, quantity, unit of measurement, and description.
 *
 * @author He Chenyi
 */
public class RecipeIngredient {
    private int recipeId;
    private String name;
    private Float quantity;
    private String unit;
    private String description;

    /**
     * Default constructor for the RecipeIngredient class.
     */
    public RecipeIngredient() {

    }

    /**
     * Constructs a RecipeIngredient object with specified attributes.
     *
     * @param recipeId    The unique identifier of the recipe this ingredient belongs to.
     * @param name        The name of the ingredient.
     * @param quantity    The quantity or amount of the ingredient.
     * @param unit        The unit of measurement for the quantity (e.g., grams, milliliters).
     * @param description A brief description or additional notes about the ingredient.
     */
    public RecipeIngredient(int recipeId, String name, Float quantity, String unit, String description) {
        this.recipeId = recipeId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
    }

    /**
     * Copy constructor for RecipeIngredient objects.
     * Creates a new RecipeIngredient object based on an existing one.
     *
     * @param ingredient The RecipeIngredient object to copy.
     */
    public RecipeIngredient(RecipeIngredient ingredient) {
        this.name = ingredient.name;
        this.quantity = ingredient.quantity;
        this.unit = ingredient.unit;
        this.description = ingredient.description;
        this.recipeId = ingredient.recipeId;
    }

    /**
     * Retrieves the recipe ID associated with this ingredient.
     *
     * @return The recipe ID.
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Sets the recipe ID associated with this ingredient.
     *
     * @param recipeId The recipe ID to set.
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Retrieves the name of the ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ingredient.
     *
     * @param name The name of the ingredient to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the quantity or amount of the ingredient.
     *
     * @return The quantity or amount of the ingredient.
     */
    public Float getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity or amount of the ingredient.
     *
     * @param quantity The quantity or amount of the ingredient to set.
     */
    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the unit of measurement for the ingredient quantity.
     *
     * @return The unit of measurement.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measurement for the ingredient quantity.
     *
     * @param unit The unit of measurement to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retrieves the description or additional notes about the ingredient.
     *
     * @return The description of the ingredient.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description or additional notes about the ingredient.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the RecipeIngredient object.
     *
     * @return A string containing all attributes of the RecipeIngredient.
     */
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
