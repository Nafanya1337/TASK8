package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    public interface onClickPerson {
        public void onClickPerson(int pos);
    }

    public interface addPerson {
        public void addPerson();
    }
    ListView listView;

    ImageButton plus;

    addPerson addPerson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addPerson = (addPerson) getContext();
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClickPerson onClickPerson = (onClickPerson) getContext();
        String[] arr = Contact.getNamesArray();
        listView = view.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arr));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickPerson.onClickPerson(position);
            }
        });
        plus = view.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson.addPerson();
            }
        });
    }

    public void addNewContact(){
        String[] arr = Contact.getNamesArray();
        listView.setAdapter(new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arr));
    }

}