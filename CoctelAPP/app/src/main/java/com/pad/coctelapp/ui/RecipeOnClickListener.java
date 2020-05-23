package com.pad.coctelapp.ui;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.pad.coctelapp.util.Recipe;

import java.util.List;

/** OnClickListener used to make the elements of a list of recipes clickable
 *
 */
class RecipeOnClickListener implements View.OnClickListener {
    private static final String LOG_TAG = "RecipeOnClickListener";
    private RecyclerView mRecyclerView;
    private List<Recipe> mList;

    public RecipeOnClickListener(RecyclerView rv) {
        mRecyclerView = rv;
    }

    public void setList(List<Recipe> l) {
        mList = l;
    }

    /** Click handler for an element of the list
     * <p>Currently does nothing</p>
     * @param view
     */
    @Override
    public void onClick(View view) {
        int itemPosition = mRecyclerView.getChildAdapterPosition(view);
        Log.d(LOG_TAG, "Cocktail card " + itemPosition + " clicked !");
        Recipe item = mList.get(itemPosition);
    }
}
