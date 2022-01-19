package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class ResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle bundle = getArguments();
        assert bundle != null;
        ArrayList<String> param = bundle.getStringArrayList("key1");

        TextView finalTextView = rootView.findViewById(R.id.textViewResult);
//        finalTextView.setText(param.toString());

        FileManager fileManager = new FileManager();

        fileManager.writeToFile(param.toString(), requireContext());
        fileManager.readFromFile(requireContext());


        Toast.makeText(getContext(), "Дані було записано в файл",
                Toast.LENGTH_SHORT).show();


        Button buttonCancel = rootView.findViewById(R.id.buttonCancel);

        Button buttonOpen = rootView.findViewById(R.id.buttonOpen);

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FinalActivity.class);
                startActivity(intent);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new com.example.lab3.CheckBoxFragment());
                ft.commit();
            }
        });

        return rootView;
    }
}