package dao.mappers;

public class PreparationStep {
    private int recipeId;
    private int step;
    private String description;

    public PreparationStep(int recipeId, int step, String description) {
        this.recipeId = recipeId;
        this.step = step;
        this.description = description;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PreparationStep{" +
                "recipeId=" + recipeId +
                ", step=" + step +
                ", description='" + description + '\'' +
                '}';
    }
}
