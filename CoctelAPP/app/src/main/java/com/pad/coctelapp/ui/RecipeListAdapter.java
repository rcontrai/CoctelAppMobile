package com.pad.coctelapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pad.coctelapp.CocktailChoiceActivity;
import com.pad.coctelapp.R;
import com.pad.coctelapp.util.Recipe;

import java.util.ArrayList;
import java.util.List;

/** Adapter for a recyclerView aimed at displaying a list of recipes. \n
 *  Refer to the documentation of the RecyclerView interface for more information.
 */
@SuppressWarnings("Convert2Diamond")
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeHolder> {
    private List<Recipe> mRecipes;
    private LayoutInflater inflater;
    private final RecipeOnClickListener mOnClickListener;

    public RecipeListAdapter(CocktailChoiceActivity context) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.mRecipes = new ArrayList<Recipe>();
        mOnClickListener = new RecipeOnClickListener(context.recyclerView);
    }

    /** Sets the list of recipes to be displayed
     *
     * @param recipes a list of Strings representing recipes
     */
    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        mOnClickListener.setList(recipes);
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_cocktail, parent, false);
        itemView.setOnClickListener(mOnClickListener);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe current = mRecipes.get(position);
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

}
