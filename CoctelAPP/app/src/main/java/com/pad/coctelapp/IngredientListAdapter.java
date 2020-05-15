package com.pad.coctelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ViewHolder> {
    private List<String> mIngredients;
    private LayoutInflater inflater;

    public IngredientListAdapter(Context context) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.mIngredients = new ArrayList<String>();
    }

    public void setIngredients(List<String> ingredients) { mIngredients = ingredients;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_wordlistitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapter.ViewHolder holder, int position) {
        holder.wordItemView.setText(mIngredients.get(position) + "\n");
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordItemView;

        public ViewHolder(View v) {
            super(v);
            wordItemView = itemView.findViewById(R.id.word);
        }
    }
}
