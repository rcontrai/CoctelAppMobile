package com.pad.coctelapp.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pad.coctelapp.R;
import com.pad.coctelapp.util.Recipe;
import com.squareup.picasso.Picasso;

/** A ViewHolder containing an ImageView and a TextView
 */
public class RecipeHolder extends RecyclerView.ViewHolder {


    TextView textView;
    ImageView imageView;

    public RecipeHolder(View v) {
        super(v);
        textView = itemView.findViewById(R.id.text);
        imageView = itemView.findViewById(R.id.image);
    }

    /** Fills the view holder with the data from a recipe
     *
     * @param recipe a recipe
     */
    public void bind(Recipe recipe) {
        textView.setText(
                String.format("%s\n%s",
                        recipe.getName(),
                        recipe.ingredientsAndAmountsToString()));
        Picasso.with(imageView.getContext()).load(recipe.getPhotoURL()).centerCrop().fit().into(imageView);
    }

}
