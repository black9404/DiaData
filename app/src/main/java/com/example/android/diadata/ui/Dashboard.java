package com.example.android.diadata.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.example.android.diadata.animation.FabAnimation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Dashboard extends Fragment {

    boolean isRotate = false;

    private TextView hidratosTextView, ultimaDoseTextView, unidadesTextView;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton addMealFloatingActionButton, addFoodFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        loadNotificationsIcon();
        loadDashboardInformation();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {

        hidratosTextView = Objects.requireNonNull(getView()).findViewById(R.id.hidratos);

        ultimaDoseTextView = Objects.requireNonNull(getView()).findViewById(R.id.ultima_dose);

        unidadesTextView = Objects.requireNonNull(getView()).findViewById(R.id.unidades);

        bottomAppBar = Objects.requireNonNull(getView()).findViewById(R.id.bar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.navMedicao) {
                    redirectToMeasurement();
                    return true;
                }
                if (item.getItemId() == R.id.navProfile) {
                    redirectToProfile();
                    return true;
                } else if (item.getItemId() == R.id.navNotifications) {
                    toggleNotificationStatus(item);
                    return true;
                } else {
                    return false;
                }
            }
        });

        addMealFloatingActionButton = Objects.requireNonNull(getView()).findViewById(R.id.addMeal);
        addMealFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToMeal();
            }
        });
        FabAnimation.init(addMealFloatingActionButton);

        addFoodFloatingActionButton = Objects.requireNonNull(getView()).findViewById(R.id.addFood);
        addFoodFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToFood();
            }
        });
        FabAnimation.init(addFoodFloatingActionButton);

        FloatingActionButton addFloatingActionButton = Objects.requireNonNull(getView()).findViewById(R.id.fab);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = FabAnimation.rotateFab(v, !isRotate);
                if (isRotate) {
                    FabAnimation.showIn(addMealFloatingActionButton);
                    FabAnimation.showIn(addFoodFloatingActionButton);
                } else {
                    FabAnimation.showOut(addMealFloatingActionButton);
                    FabAnimation.showOut(addFoodFloatingActionButton);
                }
            }
        });

    }

    //metodo que redireciona o utilizador para o perfil
    private void redirectToProfile() {
        isRotate = false;
        Fragment profile = new Profile();
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        transaction.replace(R.id.fragment_container, profile);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //metodo que redireciona o utilizador para a medição e aplicação de insulina
    private void redirectToMeasurement() {
        isRotate = false;
        Fragment NewMeasurement = new NewMeasurement();
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        transaction.replace(R.id.fragment_container, NewMeasurement);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //metodo que redireciona o utilizador para adicionar um alimento
    private void redirectToFood() {
        isRotate = false;
        Fragment newFood = new NewFood();
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        transaction.replace(R.id.fragment_container, newFood);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //metodo que redireciona o utilizador para adicionar uma refeição
    private void redirectToMeal() {
        isRotate = false;
        Fragment newMeal = new NewMeal();
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        transaction.replace(R.id.fragment_container, newMeal);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //metodo que liga ou desliga as notificações durante um periodo de tempo
    private void toggleNotificationStatus(MenuItem item) {

        SharedPreferences sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("prefs", MODE_PRIVATE);
        boolean notificationStatus = sharedPreferences.getBoolean("notificationStatus", false);

        if (notificationStatus) {
            //alterado o icon
            item.setIcon(getContext().getDrawable(R.drawable.ic_notifications_active));
            //mostrada imagem ao utilizador uma mensagem a informar a ação
            Toast.makeText(getContext(), "As notificações da aplicação foram reativadas", Toast.LENGTH_SHORT).show();
            //atualizadas as SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notificationStatus", false);
            editor.apply();
        } else {
            //alterado o icon
            item.setIcon(getContext().getDrawable(R.drawable.ic_notifications_inactive));
            //mostrada imagem ao utilizador uma mensagem a informar a ação
            Toast.makeText(getContext(), "As notificações da aplicação foram desativadas durante 8 horas", Toast.LENGTH_SHORT).show();
            //atualizadas as SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notificationStatus", true);
            editor.apply();
        }

    }

    //metodo que verifica o estado do icone das notificações e atualiza o icon de acordo
    private void loadNotificationsIcon() {

        SharedPreferences sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("prefs", MODE_PRIVATE);
        MenuItem item = bottomAppBar.getMenu().findItem(R.id.navNotifications);
        boolean notificationStatus = sharedPreferences.getBoolean("notificationStatus", false);

        if (notificationStatus) {
            item.setIcon(getContext().getDrawable(R.drawable.ic_notifications_active));
        } else {
            item.setIcon(getContext().getDrawable(R.drawable.ic_notifications_inactive));
        }

    }

    //metodo que carrega toda a informação do dashboard
    @SuppressLint("StringFormatMatches")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadDashboardInformation() {
        //Caso tenha calculado a dosagem no mesmo dia
        if (LocalDateTime.now().getDayOfMonth() == MainActivity.diaDataDatabase.DashData().getUltimaDoseDia()) {
            ultimaDoseTextView.setText(String.format(getResources().getString(R.string.h_s_horas), String.valueOf(LocalDateTime.now().getHour() - MainActivity.diaDataDatabase.DashData().getUltimaDose())));
        //Caso haja mudança de mes
        }else if(LocalDateTime.now().getDayOfMonth() < MainActivity.diaDataDatabase.DashData().getUltimaDoseDia()){
            ultimaDoseTextView.setText(String.format(getResources().getString(R.string.h_s_horas), String.valueOf(LocalDateTime.now().getHour()*24)));
        }else if (MainActivity.diaDataDatabase.DashData().getUltimaDoseDia() == 0){ //Caso nunca tenha feito um calculo de dosagem
            ultimaDoseTextView.setText("Sem Dados");
        }else{
            ultimaDoseTextView.setText("Error 404");
        }

        NumberFormat hidratosFormater = new DecimalFormat("#0.00");
        hidratosTextView.setText(String.format(getResources().getString(R.string.gramas), hidratosFormater.format(MainActivity.diaDataDatabase.DashData().getHidratosTotal())));
        unidadesTextView.setText(String.format(getResources().getString(R.string.s_unidade_s), MainActivity.diaDataDatabase.DashData().getDosesTotal()));

    }

}