package com.pad.coctelapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pad.coctelapp.R;
import com.pad.coctelapp.util.Recipe;

import java.util.ArrayList;
import java.util.List;

/** Adapter for a recyclerView aimed at displaying a list of recipes. \n
 *  Refer to the documentation of the RecyclerView interface for more information.
 */
@SuppressWarnings("Convert2Diamond")
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    private List<Recipe> mRecipes;
    private LayoutInflater inflater;

    public RecipeListAdapter(Context context) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.mRecipes = new ArrayList<Recipe>();
    }

    /** Sets the list of recipes to be displayed
     *
     * @param recipes a list of Strings representing recipes
     */
    public void setRecipes(List<Recipe> recipes) { mRecipes = recipes;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_wordlistitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        Recipe current = mRecipes.get(position);
        holder.wordItemView.setText(String.format("%s\n%s\n",current.getName(),current.getIngredients()));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    /** A ViewHolder consisting of a single TextView
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordItemView;

        public ViewHolder(View v) {
            super(v);
            wordItemView = itemView.findViewById(R.id.word);
        }
    }
}