package com.example.android.diadata.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.example.android.diadata.core.SearchFoodAdapter;
import com.example.android.diadata.db.model.DashData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class NewMeasurement extends Fragment {

    private SearchFoodAdapter searchFoodAdapter;

    private SearchView searchMealSearchView;
    private ArrayList<String> mealArrayList;
    private TextInputLayout glicemiaTextInputLayout;

    private TextInputEditText valoresGlicemiaForm;

    private Button glicemiaButton;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_measurement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchMealFromDatabase();
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

        searchMealSearchView = Objects.requireNonNull(getView()).findViewById(R.id.searchMeal);
        searchMealSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchMealSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)) {
                    recyclerView.setVisibility(View.INVISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    searchFoodAdapter.getFilter().filter(newText);
                }

                return false;
            }
        });

        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recycler_view_meal);

        glicemiaTextInputLayout = getView().findViewById(R.id.niveis_glicemia);

        valoresGlicemiaForm = Objects.requireNonNull(getView()).findViewById(R.id.valoresGlicemiaForm);
        valoresGlicemiaForm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                recyclerView.setVisibility(View.GONE);
            }
        });

        glicemiaButton = getView().findViewById(R.id.button_gli);
        glicemiaButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmar")
                        .setMessage("Confirma os dados da seguinte refeição?\n " +
                                "Refeição Selecionada: " + searchMealSearchView.getQuery().toString() + "\n" +
                                "Valores de Glicemia: "+ valoresGlicemiaForm.getText().toString() + "\n" +
                                "Dose a tomar: " + calcularDoseInsulina(searchMealSearchView.getQuery().toString(),Integer.parseInt(valoresGlicemiaForm.getText().toString())) + " unidades")
                        .setIcon(R.drawable.ic_confirmation)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                addDataToDatabase(
                                        searchMealSearchView.getQuery().toString(),
                                        Integer.parseInt(valoresGlicemiaForm.getText().toString()));
                                Toast.makeText(getContext(), "Medição adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Operação cancelada", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

            }
        });
    }

    //metodo que prepara a RecyclerView de procura
    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        searchFoodAdapter = new SearchFoodAdapter(mealArrayList);
        searchFoodAdapter.setOnItemClickListener(new SearchFoodAdapter.OnItemClickListener() {
            @Override
            public void onFoodItemClicked(String foodName) {
                searchMealSearchView.setQuery(foodName, false);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchFoodAdapter);
    }

    //metodo que pesquisa de entre os alimentos todos
    private void searchMealFromDatabase() {
        mealArrayList = (ArrayList<String>) MainActivity.diaDataDatabase.mealDao().getRefeicoes();
    }

    //metodo para adicionar a informação a base de dados
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addDataToDatabase(String nomeRefeicao, int nivelGlicemia) {
        DashData dashData = new DashData(
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getMonthValue(),
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute(),
                MainActivity.diaDataDatabase.mealDao().getIdMeal(nomeRefeicao),
                MainActivity.diaDataDatabase.mealDao().getSomaHidratos(nomeRefeicao),
                calcularDoseInsulina(nomeRefeicao, nivelGlicemia));
        MainActivity.diaDataDatabase.DashData().addDados(dashData);
    }

    //Metodo para calcular unidades de dosagem
    private int calcularDoseInsulina(String nomeRefeicao, int glicemia_momento){
        double dose = 0;
        double totalHidratos = MainActivity.diaDataDatabase.mealDao().getSomaHidratos(nomeRefeicao);
        //Caso os valores de glicemia sejam maiores do que o aconselhado
        if (glicemia_momento > 120){
            dose = totalHidratos/10;
            dose = dose + Math.round(((double)(glicemia_momento-103)/50)); //103 é a mediana do intervalo de valores aconselhado [85-120]
        //Caso os valores de glicemia sejam inferiores ao aconselhado
        }else if(glicemia_momento <85) {
            dose = (((totalHidratos * 5) + glicemia_momento) - 103) / 50;
        }else{
            dose = totalHidratos/10;
        }
        return (int)Math.round(dose);
    }
}
