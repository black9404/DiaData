package com.example.android.diadata.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
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
import com.example.android.diadata.db.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewMeal extends Fragment {

    private SearchFoodAdapter searchFoodAdapter;

    private ArrayList<String> foodArrayList;

    private TextInputEditText mealNameInputEditText;
    private SearchView searchFoodSearchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchFoodFromDatabaseToArray();
        setUpRecyclerView();
        initViews();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {

        mealNameInputEditText = Objects.requireNonNull(getView()).findViewById(R.id.userNameForm);

        searchFoodSearchView = Objects.requireNonNull(getView()).findViewById(R.id.searchFood);
        searchFoodSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchFoodSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchFoodAdapter.getFilter().filter(newText);
                return false;
            }
        });

        Button submitFormButton = Objects.requireNonNull(getView()).findViewById(R.id.button);
        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //metodo que retorna todos os alimentos da base de dados e os adiciona num arraylist
    private void fetchFoodFromDatabaseToArray() {

        //instancia uma arraylist
        foodArrayList = new ArrayList<>();

        foodArrayList.add("teste");
        foodArrayList.add("ola");
        foodArrayList.add("ola1");
        foodArrayList.add("ola3");
        foodArrayList.add("ola4");

    }

    //metodo que prepara a RecyclerView de procura
    private void setUpRecyclerView() {
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        searchFoodAdapter = new SearchFoodAdapter(foodArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchFoodAdapter);
    }

    //metodo que pesquisa de entre os alimentos todos
    private void searchFoodFromDatabase() {}

    //metodo que verifica se os dados introduzidos pelo utilizador são válidos
    private void checkIfDataIsValid() {}

    //metodo que insere os dados introduzidos na base de dados
    private void addDataToDatabase() {}

}
