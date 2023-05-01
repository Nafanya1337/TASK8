package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.AddingPerson.resultOfAdding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements FirstFragment.onClickPerson, FirstFragment.addPerson, resultOfAdding {

    private FragmentContainerView containerView, container2;

    SecondFragment secondFragment;

    private int pos = -1;

    private static ArrayList<Contact> set = new ArrayList<>();

    static {
        set.add(new Contact("Демин И.А.",
                89853018872l,
                new String[]{"Вчера 18:45"}));
        set.add(new Contact("Шмаков Ф.М.",
                89851234451l,
                new String[]{"03/04/2023 19:30", "02/01/2022 10:30"}));
        set.add(new Contact("Климов Н.М.",
                89282410168l,
                new String[]{"08/05/2022 13:34"}));
        set.add(new Contact("Токарев М.М.",
                89518635630l,
                new String[]{""}));
        set.add(new Contact("Румянцев М. Д.",
                89243023215l,
                new String[]{}));
        set.add(new Contact("Миронов А. Д.",
                89531663584l,
                new String[]{""}));

        set.add(new Contact("Долгова К. А.",
                89202747007l,
                new String[]{"Вчера 18:45"}));
        set.add(new Contact("Комарова М. Я.",
                89527668314l,
                new String[]{"Вчера 18:45"}));
        set.add(new Contact("Кузнецов С. М.",
                89956211105l,
                new String[]{"Вчера 18:45"}));
        set.add(new Contact("Трофимов Р. С.",
                89691563510l,
                new String[]{"Вчера 18:45"}));
        set.add(new Contact("Степанов А. М.",
                89303809161l,
                new String[]{"Вчера 18:45"}));
        set.add(new Contact("Попов М. Д.",
                89653313828l,
                new String[]{"Вчера 18:45"}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containerView = findViewById(R.id.container);

        if (savedInstanceState != null)
            pos = savedInstanceState.getInt("POSITION");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            container2 = findViewById(R.id.container2);
            if (pos == -1)
                container2.setVisibility(View.INVISIBLE);
            else {
                container2.setVisibility(View.VISIBLE);
                onClickPerson(pos);
            }
        }

    }
    @Override
    public void onClickPerson(int pos) {
        secondFragment = new SecondFragment();
        Bundle bundle = new Bundle();
        Contact[] contacts = set.toArray(new Contact[0]);
        bundle.putParcelable("NAME", contacts[pos]);
        this.pos = pos;
        secondFragment.setArguments(bundle);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerView.getId(), secondFragment, null)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerView.getId(), new FirstFragment(), null)
                    .addToBackStack(null)
                    .commit();
            container2.setVisibility(View.VISIBLE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container2, secondFragment, null)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void addPerson() {
        AddingPerson addingPerson = new AddingPerson();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerView.getId(), addingPerson, null)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container2, addingPerson, null)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void add(Contact contact) {
        set.add(contact);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FirstFragment currentFragment = containerView.getFragment();

        if (currentFragment != null) {
            currentFragment.addNewContact();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("POSITION", pos);
    }
}