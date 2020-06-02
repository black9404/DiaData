package com.example.android.diadata.ui;

import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import static com.example.android.diadata.core.App.CHANNEL_ID;

public class Dashboard extends Fragment {

    private NotificationManagerCompat notificationManager;

    private TextView userNameTextView;
    private FloatingActionButton addFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard_alternative, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        fetchData();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {
        userNameTextView = Objects.requireNonNull(getView()).findViewById(R.id.userNameComplete);

        addFloatingActionButton = Objects.requireNonNull(getView()).findViewById(R.id.fab);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNotifications();
                addData();
            }
        });

    }

    //metodo que preenche as informações armazenadas
    private void fetchData() {

        //obtem o nome e o subnome do utilizador
        String nomeProprio = MainActivity.diaDataDatabase.userDao().getUserName();
        String subNome = MainActivity.diaDataDatabase.userDao().getUserSubname();

        //junta os nomes obtidos
        String nomeCompleto = nomeProprio + " " + subNome;

        //insere o nome do utilizador no dashboard
        userNameTextView.setText(nomeCompleto);

    }

    //metodo que redireciona o utilizador para o fragmento de adicionar dados
    private void addData() {
        Fragment addNewDataFragment = new AddNewDataFragment();
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        transaction.replace(R.id.fragment_container, addNewDataFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //metodo que acede a todas as notificações enviadas ao utilizador
    private void testNotifications() {

        notificationManager = NotificationManagerCompat.from(Objects.requireNonNull(getContext()));

        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle("Teste")
                .setContentText("message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

}
