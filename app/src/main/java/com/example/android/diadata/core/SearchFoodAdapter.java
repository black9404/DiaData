package com.example.android.diadata.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.example.android.diadata.db.DiaDataDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchFoodAdapter extends RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder> implements Filterable {

    private ArrayList<String> foodArrayList, foodArrayListFull;
    private OnItemClickListener onItemClickListener;

    public SearchFoodAdapter(ArrayList<String> foodArrayList) {
        this.foodArrayList = foodArrayList;
        this.foodArrayListFull = new ArrayList<>(foodArrayList);
    }

    @NonNull
    @Override
    public SearchFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_food_item, parent, false);
        return new SearchFoodViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchFoodAdapter.SearchFoodViewHolder holder, int position) {
        final String currentItem = foodArrayList.get(position);
        holder.foodNameTextView.setText(currentItem);
        holder.foodTypeTextView.setText(changeTipoAlimento(MainActivity.diaDataDatabase.foodDao().getTipoAlimento(currentItem)));
        //Holder para a 2º linha da medição<-------------------------------------------------------
            //holder.foodTypeTextView.setText(String.valueOf(MainActivity.diaDataDatabase.mealDao().getSomaHidratos(currentItem)));
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

    class SearchFoodViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTextView;
        TextView foodTypeTextView;

        public SearchFoodViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            foodNameTextView = itemView.findViewById(R.id.addFoodRecycler);
            foodTypeTextView = itemView.findViewById(R.id.text_view2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFoodItemClicked(foodArrayList.get(getAdapterPosition()));
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onFoodItemClicked(String foodName);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public String changeTipoAlimento(int tipoAlimento) {
        //verifica qual dos campos foi preenchido
        switch (tipoAlimento) {
            //caso do tipo de alimento
            case 1:
                return "Carboidratos";
            case 2:
                return "Verduras e Legumes";
            case 3:
                return "Frutas";
            case 4:
                return "Leite e Derivados";
            case 5:
                return "Carnes e Ovos";
            case 6:
                return "Leguminosas e Oleaginosas";
            case 7:
                return "Óleos e Gurduras";
            case 8:
                return "Açucares e Doces";
        }
        return null;
    }
}
