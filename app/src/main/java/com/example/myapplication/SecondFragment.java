package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

public class SecondFragment extends Fragment {

    private Contact contact;

    String name = "";
    TextView textView;

    EditText phoneText;

    ListView historyListView;

    public boolean isEditting = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView2);
        phoneText = view.findViewById(R.id.phoneText);
        historyListView = view.findViewById(R.id.historyListView);


        Bundle bundle = getArguments();
        if (bundle != null)
            contact = bundle.getParcelable("NAME");
        else if (savedInstanceState != null)
            contact = savedInstanceState.getParcelable("CONTACT");
        if (contact != null) {
            phoneText.setText(String.valueOf(contact.getPhone()));
            textView.setText(contact.getName());
            historyListView.setAdapter(new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_list_item_1,
                    contact.getHistory()));
        }
        else {
            textView.setText("Никто не выбран");
            phoneText.setText("");
        }

        Button call = view.findViewById(R.id.call);
        Button sms = view.findViewById(R.id.sms);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneText.getText().toString()));
                Intent chosen = Intent.createChooser(intent, "Позвонить через");
                startActivity(chosen);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + phoneText.getText().toString()));
                startActivity(intent);
            }
        });

        Button edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditting = !isEditting;
                phoneText.setEnabled(isEditting);
                phoneText.setEnabled(isEditting);
                phoneText.setCursorVisible(isEditting);
                if (isEditting) {
                    phoneText.setTextColor(Color.parseColor("#A9A9A9"));
                }
                else
                    phoneText.setTextColor(Color.parseColor("#000000"));
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("CONTACT", contact);
    }
}