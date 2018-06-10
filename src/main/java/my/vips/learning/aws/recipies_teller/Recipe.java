package my.vips.learning.aws.recipies_teller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by vipinsharma on 09/06/18.
 */
@DynamoDBTable(tableName = "Recipes")
public class Recipe implements Serializable{
    private static final long serialVersionUID = -0321234521l;

    @DynamoDBHashKey
    private String recipeCode;
    @DynamoDBAttribute
    private String recipeName;
    @DynamoDBAttribute
    private Set<String> ingredients;
    @DynamoDBAttribute
    private Map<String,String> procedure;
    @DynamoDBAttribute
    private String suggestions;
    @DynamoDBAttribute
    private int cookingTime;
    @DynamoDBAttribute
    private int preparationTime;
    @DynamoDBAttribute
    private String description;

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeCode() {
        return recipeCode;
    }


    public Set<String> getIngredients() {
        return ingredients;
    }

    public Map<String, String> getProcedure() {
        return procedure;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setProcedure(Map<String, String> procedure) {
        this.procedure = procedure;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(getRecipeName(), recipe.getRecipeName()) &&
                Objects.equals(getRecipeCode(), recipe.getRecipeCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeName(), getRecipeCode());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeName='" + recipeName + '\'' +
                ", recipeCode='" + recipeCode + '\'' +
                ", ingredients=" + ingredients +
                ", procedure=" + procedure +
                ", suggestions='" + suggestions + '\'' +
                ", cookingTime=" + cookingTime +
                ", preparationTime=" + preparationTime +
                ", description='" + description + '\'' +
                '}';
    }
}
