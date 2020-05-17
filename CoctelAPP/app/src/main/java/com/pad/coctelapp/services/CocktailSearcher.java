package com.pad.coctelapp.services;

import com.pad.coctelapp.util.Recipe;

import java.util.*;
/*
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("Convert2Diamond")
public class CocktailSearcher {


    /**
     * Finds recipes in theCocktailDB that use given ingredients
     *
     * @param ingredients a list of strings enumerating cocktail ingredients
     * @return a List of Recipe objects
     */
    public static List<Recipe> findRecipe(List<String> ingredients) {

        List<Recipe> lRecipe = new ArrayList<Recipe>();
        ListIterator<String> i = ingredients.listIterator();
        String reader = null;
        String ingredient = null;
        JSONParser jsonParser = new JSONParser();
        Object nameCocktail = null;
        Map<String, Integer> counterCocktail = new HashMap<>();
        OkHttpClient client = new OkHttpClient();
        int max = 0;
        while (i.hasNext()) {
            ingredient = i.next();
            //System.out.println(ingredient);
            try {
                Request request = new Request.Builder()
                        .url("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=" + ingredient).get()
                        .addHeader("x-rapidapi-host", "the-cocktail-db.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "4527d57cc6msh768e09bb02bba03p172fd8jsn48546d794e1f").build();
                reader = client.newCall(request).execute().body().string();
                // System.out.println(reader);
                JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
                JSONArray lang = (JSONArray) jsonObject.get("drinks");
                Iterator i2 = lang.iterator();
                while (i2.hasNext()) {
                    JSONObject innerObj = (JSONObject) i2.next();
                    nameCocktail = innerObj.get("strDrink");
                    if (counterCocktail.containsKey(nameCocktail)) {
                        counterCocktail.computeIfPresent(nameCocktail.toString(), (k, v) -> v + 1);
                        max = Math.max(max,counterCocktail.get(nameCocktail));
                    } else {
                        counterCocktail.put(nameCocktail.toString(), 1);
                    }
                    // System.out.println("The name of the cocktail is " + nameCocktail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // System.out.println(counterCocktail.size() + " cocktails found !");
        System.out.println();
        Iterator i3 = counterCocktail.entrySet().iterator();
        while (i3.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) i3.next();
            // Clear every cocktail with just one ingredient.
            //if (!mapEntry.getValue().equals(1)) {
            if(mapEntry.getValue().equals(max)) {
                // System.out.println("Key: " + mapEntry.getKey() + " | Val: "+ mapEntry.getValue());
                // Call TheCocktailDB for every potential cocktail
                try {
                    Request request = new Request.Builder()
                            .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + mapEntry.getKey()).get()
                            .addHeader("x-rapidapi-host", "the-cocktail-db.p.rapidapi.com")
                            .addHeader("x-rapidapi-key", "4527d57cc6msh768e09bb02bba03p172fd8jsn48546d794e1f").build();
                    reader = client.newCall(request).execute().body().string();
                    JSONObject jsonRecipe = (JSONObject) jsonParser.parse(reader);
                    // Put it in a recipe and add it to lRecipe.
                    Recipe recipe = new Recipe(jsonRecipe);
                    lRecipe.add(recipe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return lRecipe;
    }


    /** Print all information from each recipe
     *
     * @param lRecipe a list of Recipe objects
     */
    public static void PrintAll(List<Recipe> lRecipe) {
        Iterator<Recipe> i = lRecipe.iterator();
        Recipe recipe = null;
        while (i.hasNext()) {
            recipe = (Recipe) i.next();
            recipe.Print();
        }

    }

/*    public static void SavePhoto(Recipe r) throws IOException {
        int scaledWidth = 1024;
        int scaledHeight = 768;
        String outputDirectoryPath = "./Photo/";
        String outputImagePath = outputDirectoryPath + r.getName() +".jpeg";
        System.out.println("Saving picture for " + r.getName() + " at " + outputImagePath);
        URL url = new URL(r.getPhotoURL());
        //URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Egyptian_Grains.jpg/204px-Egyptian_Grains.jpg");
        try {
            Image image = ImageIO.read(url);
            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, ((BufferedImage) image).getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

            // extracts extension of output file
            String formatName = outputImagePath.substring(outputImagePath
                    .lastIndexOf(".") + 1);

            // writes to output file
            File outputDirectory = new File(outputDirectoryPath);
            outputDirectory.mkdir(); //Creates the output directory if it doesn't already exist
            ImageIO.write(outputImage, formatName, new File(outputImagePath));
        } catch (IIOException e) {
            //protect from unsupported image type
            //e.printStackTrace();
            System.out.println("The picture of "+  r.getName() + " could not be saved.");
        }
    }*/
}
