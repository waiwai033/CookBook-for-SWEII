package dao.mappers;

public class RecipeInstruction {
    private int recipeId;
    private String recipeName;
    private int instructionId;
    private String instructionText;


    public RecipeInstruction(int recipeId, String recipeName, int instructionId, String instructionText) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.instructionId = instructionId;
        this.instructionText = instructionText;
    }
    // Getter methods
    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getInstructionId() {
        return instructionId;
    }

    public String getInstructionText() {
        return instructionText;
    }

}
