package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddingPerson extends Fragment {

    public interface resultOfAdding {
        public void add(Contact contact);
    }

    Button decline, accept;

    resultOfAdding resultOfAdding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        resultOfAdding = (resultOfAdding) getContext();
        return inflater.inflate(R.layout.fragment_adding_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        decline = view.findViewById(R.id.decline);
        accept = view.findViewById(R.id.accept);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = view.findViewById(R.id.nameEdit);
                EditText phone = view.findViewById(R.id.phoneEdit);

                Contact contact = new Contact(name.getText().toString(), Long.valueOf(phone.getText().toString()), new String[]{});

                getFragmentManager().popBackStack();
                resultOfAdding.add(contact);
            }
        });
    }
}