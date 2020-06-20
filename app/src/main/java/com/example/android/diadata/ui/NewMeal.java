package com.example.android.diadata.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.example.android.diadata.core.SearchFoodAdapter;
import com.example.android.diadata.db.model.Meal;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class NewMeal extends Fragment {

    private SearchFoodAdapter searchFoodAdapter;
    private ArrayAdapter<String> arrayAdapter;

    private RecyclerView recyclerView;
    private ListView listViewAlimentos;

    private ArrayList<String> foodArrayList, foodArray = new ArrayList<>();

    private SearchView searchFoodSearchView;
    private TextInputEditText mealTextInputEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchFoodFromDatabase();
        initViews();
        setUpRecyclerView();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {

        ImageView backButtonImageView = Objects.requireNonNull(getView()).findViewById(R.id.backButton);
        backButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate();
            }
        });

        mealTextInputEditText = Objects.requireNonNull(getView()).findViewById(R.id.nomeRefeicao);

        searchFoodSearchView = Objects.requireNonNull(getView()).findViewById(R.id.searchFood);
        searchFoodSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchFoodSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    listViewAlimentos.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    listViewAlimentos.setVisibility(View.INVISIBLE);
                    searchFoodAdapter.getFilter().filter(newText);
                }

                return false;
            }
        });

        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recycler_view);

        listViewAlimentos = Objects.requireNonNull(getView()).findViewById(R.id.list_view_alimentos);
        listViewAlimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final TextView selectedFood = view.findViewById(R.id.foodName);
                ImageView removeFood = view.findViewById(R.id.remove_food);

                removeFood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeDataFromArray((selectedFood).getText().toString());
                    }
                });

            }
        });

        Button submitFormButton = Objects.requireNonNull(getView()).findViewById(R.id.button);
        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDatabase(Objects.requireNonNull(mealTextInputEditText.getText()).toString(), foodArray);
                Toast.makeText(getContext(), "Refeição adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //metodo que prepara a RecyclerView de procura
    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        searchFoodAdapter = new SearchFoodAdapter(foodArrayList);
        searchFoodAdapter.setOnItemClickListener(new SearchFoodAdapter.OnItemClickListener() {
            @Override
            public void onFoodItemClicked(String foodName) {
                addDataToArray(foodName);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchFoodAdapter);
    }

    //metodo que pesquisa de entre os alimentos todos
    private void searchFoodFromDatabase() {
        foodArrayList = (ArrayList<String>) MainActivity.diaDataDatabase.foodDao().getNomesAlimento();
    }

    //metodo que insere os dados introduzidos no array
    public void addDataToArray(String foodName) {

        //verifica se o alimento se encontra adicionado
        if (foodArray.contains(foodName)) {
            //caso se encontre avisa o utilizador
            Toast.makeText(getContext(), "O alimento já se encontra adicionado na refeição!", Toast.LENGTH_SHORT).show();
        }

        //caso contrario adiciona a refeição
        else {
            foodArray.add(foodName);
        }

        //atualiza o adaptar com a lista de alimentos mais recente
        arrayAdapter = new ArrayAdapter<>(
                Objects.requireNonNull(getContext()),
                R.layout.added_food_items,
                R.id.foodName,
                foodArray);
        listViewAlimentos.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();

    }

    //metodo que remove os dados introduzidos no array
    public void removeDataFromArray(String foodName) {

        foodArray.remove(foodName);

        //atualiza o adaptar com a lista de alimentos mais recente
        arrayAdapter = new ArrayAdapter<>(
                Objects.requireNonNull(getContext()),
                R.layout.added_food_items,
                R.id.foodName,
                foodArray);
        listViewAlimentos.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();

        Toast.makeText(getContext(), "Alimento removido com sucesso!", Toast.LENGTH_SHORT).show();

    }

    //metodo que obtem os dados do array criado e adiciona na base de dados
    public void addDataToDatabase(String nomeRefeicao, ArrayList<String> foodArray) {
        int idRefeicao = MainActivity.diaDataDatabase.mealDao().getIdMeal(nomeRefeicao);



        for (int i = 0; i < foodArray.size(); i++){
            int idAlimento = MainActivity.diaDataDatabase.foodDao().getIdFood(foodArray.get(i));
            int hidratosAlimento = MainActivity.diaDataDatabase.foodDao().getHidratosAlimento(foodArray.get(i));
            Meal m = new Meal(idRefeicao, idAlimento, hidratosAlimento, nomeRefeicao, 20, 30);
            MainActivity.diaDataDatabase.mealDao().addRefeicao(m);
        }
    }

}
