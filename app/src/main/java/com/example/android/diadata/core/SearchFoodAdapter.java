package com.example.android.diadata.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.diadata.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFoodAdapter extends RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder> implements Filterable {

    private ArrayList<String> foodArrayList;
    private ArrayList<String> foodArrayListFull;

    public SearchFoodAdapter(ArrayList<String> foodArrayList) {
        this.foodArrayList = foodArrayList;
        this.foodArrayListFull = new ArrayList<>(foodArrayList);
    }

    @NonNull
    @Override
    public SearchFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_food_item, parent, false);
        return new SearchFoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFoodAdapter.SearchFoodViewHolder holder, int position) {
        String currentItem = foodArrayList.get(position);
        holder.foodNameTextView.setText(currentItem);
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(foodArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String item : foodArrayListFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            foodArrayList.clear();
            foodArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    static class SearchFoodViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTextView;

        public SearchFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodNameTextView = itemView.findViewById(R.id.addFoodRecycler);

        }
    }

}
