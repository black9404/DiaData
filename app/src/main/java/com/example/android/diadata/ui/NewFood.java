package com.example.android.diadata.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.example.android.diadata.db.model.Food;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewFood extends Fragment {

    private TextInputEditText nome, hidratos;
    private AutoCompleteTextView tipoAlimento;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
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

        nome = Objects.requireNonNull(getView()).findViewById(R.id.userNameForm);

        hidratos = Objects.requireNonNull(getView()).findViewById(R.id.HidratosForm);

        tipoAlimento = Objects.requireNonNull(getView()).findViewById(R.id.diabetes_spinner);
        ArrayAdapter<CharSequence> adapterDiab = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.food_type, R.layout.dropdown_menu_popup_item);
        adapterDiab.setDropDownViewResource(R.layout.dropdown_menu_popup_item);
        tipoAlimento.setAdapter(adapterDiab);

        Button submitFormButton = Objects.requireNonNull(getView()).findViewById(R.id.button);
        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMesage(checkIfDataIsValid());
            }
        });

    }

    //Confirma informação
    private boolean checkIfDataIsValid() {

        //lista de todos os campos presentes no formulário
        List<TextInputEditText> editTextList = Arrays.asList(nome, hidratos);
        List<AutoCompleteTextView> autoCompleteTextViewList = Arrays.asList(tipoAlimento);

        //variaveis
        String nome = "";
        int hidratos = 0, tipoAlimento = 0;

        //for loop que verifica se todos os campos foram preenchidos
        for (TextInputEditText edit : editTextList) {

            //verifica se o utilizador inseriu informação nos campos presentes no formulário
            if (TextUtils.isEmpty(edit.getText())) {
                edit.setError("Por favor, preencha o campo assinalado");
                return false;
            }
            //caso os valor inserido seja válido o mesmo é adicionada à String userInformation
            else {
                //verifica qual dos campos foi preenchido
                switch (editTextList.indexOf(edit)) {
                    //campo do nome
                    case 0:
                        nome = edit.getText().toString();
                        break;
                    //campo de hidratos
                    case 1:
                        hidratos = Integer.parseInt(edit.getText().toString());
                        break;
                }
            }
        }

        //for loop que verifica se os spinner foram preenchidos
        for (AutoCompleteTextView edit : autoCompleteTextViewList) {

            //verifica se o utilizador inseriu informação nos campos presentes no formulário
            if (TextUtils.isEmpty(edit.getText())) {
                edit.setError("Por favor, preencha o campo assinalado");
                return false;
            }

            //caso os valor inserido seja válido o mesmo é adicionada à String userInformation
            else {

                //verifica qual dos campos foi preenchido
                switch (autoCompleteTextViewList.indexOf(edit)) {

                    //spinner do tipo de alimento
                    case 0:
                        if (edit.getText().toString().equals("Carboidratos")) {
                            tipoAlimento = 1;
                            break;
                        } else if (edit.getText().toString().equals("Verduras e Legumes")) {
                            tipoAlimento = 2;
                            break;
                        } else if (edit.getText().toString().equals("Frutas")) {
                            tipoAlimento = 3;
                            break;
                        } else if (edit.getText().toString().equals("Leite e derivados")) {
                            tipoAlimento = 4;
                            break;
                        } else if (edit.getText().toString().equals("Carnes e Ovos")) {
                            tipoAlimento = 5;
                            break;
                        } else if (edit.getText().toString().equals("Leguminosas e Oleaginosas")) {
                            tipoAlimento = 6;
                            break;
                        } else if (edit.getText().toString().equals("Óleos e Gurduras")) {
                            tipoAlimento = 7;
                            break;
                        } else if (edit.getText().toString().equals("Açucares e Doces")) {
                            tipoAlimento = 8;
                            break;
                        }
                }
            }
        }

        //criado o alimento com os dados inseridos
        Food food = new Food(nome, hidratos, tipoAlimento);

        //adicionar o alimento a base de dados
        MainActivity.diaDataDatabase.foodDao().addAlimento(food);
        return true;
    }

    //Informa o utilizador se conseguiu adicionar alimento
    private void ToastMesage(boolean dataIsValid) {

        //atualiza os valores das SharedPreferences para que o formulário não volte a aparecer
        if (dataIsValid) {
            //Informa o utilizador que o alimento foi inserido
            Toast.makeText(getContext(), "Alimento adicionado com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Erro! Reinicie a App.", Toast.LENGTH_SHORT).show();
        }
    }
}
