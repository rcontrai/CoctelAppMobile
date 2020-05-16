package com.pad.coctelapp.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**Represents a cocktail recipe for the other classes
 *
 */
public class Recipe {
    private String name;
    private String instructions;
    private List<String> ingredients;
    private List<Amount> amounts;
    private String photoURL;

    /** Creates a cocktail recipe from its JSON description
     *
     * @param obj the JSON description of a cocktail recipe matching
     *            TheCocktailDB's format
     */
    public Recipe(JSONObject obj) {

        JSONArray l = (JSONArray) obj.get("drinks");
        JSONObject innerObj = (JSONObject) l.iterator().next();
        this.name = (String) innerObj.get("strDrink");
        this.instructions = (String) innerObj.get("strInstructions");
        this.ingredients = new ArrayList<String>();
        this.amounts = new ArrayList<Amount>();
        this.photoURL = (String) innerObj.get("strDrinkThumb");
        // take value from the json array separately
        int i = 1;
        while(innerObj.get("strIngredient" + i) != null && i<=15) {
            this.ingredients.add( (String) innerObj.get("strIngredient" + i));
            this.amounts.add(new Amount( (String) innerObj.get("strMeasure" + i)));
            i++;
        }
    }

    /** Retrieves the name of the cocktail
     *
     *  @return the name of the cocktail as a string
     */
    public String getName() {
        return name;
    }

    /** Retrieves the preparation instructions
     *
     * @return a string containing instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /** Retrieves the ingredients of the cocktail
     *
     * @return a List of Strings enumerating the ingredients of the cocktail
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /** Retrieves the amounts associated with each ingredient of the cocktail
     *
     * @return a List of Amount objects, ordered so that each Amount is in the
     *         same position as the one its associated ingredient has in the
     *         List returned by getIngredients
     */
    public List<Amount> getAmounts() {
        return amounts;
    }

    /** Retrieves the photo of the cocktail
     *
     *  @return the photoURL of the cocktail as a string
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /** Prints all information from one recipe
     */
    public void Print() {
        System.out.println("Name: " + this.getName() + "\n" +
                "Ingredients: " + this.getIngredients() + "\n" + "Amount: " + this.getAmounts() + "\n" +
                "Instructions: " + this.getInstructions() + "\n");
        //"Url Photo :" + this.getPhotoURL());
    }

}