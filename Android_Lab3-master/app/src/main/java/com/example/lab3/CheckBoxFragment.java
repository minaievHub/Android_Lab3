package com.example.lab3;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class CheckBoxFragment extends Fragment {


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_check_box, container, false);

        CheckBox size1 = rootView.findViewById(R.id.checkBoxSize1);
        CheckBox size2 = rootView.findViewById(R.id.checkBoxSize2);
        CheckBox size3 = rootView.findViewById(R.id.checkBoxSize3);

        CheckBox typeStandard = rootView.findViewById(R.id.checkBoxTypeDoughStandard);
        CheckBox typeThin = rootView.findViewById(R.id.checkBoxTypeDoughThin);
        CheckBox typeCheese = rootView.findViewById(R.id.checkBoxTypeDoughCheese);

        Button buttonOk = rootView.findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<CheckBox> checkBoxes = new ArrayList<>();
                checkBoxes.add(size1);
                checkBoxes.add(size2);
                checkBoxes.add(size3);
                checkBoxes.add(typeStandard);
                checkBoxes.add(typeThin);
                checkBoxes.add(typeCheese);

                boolean[] checkBoxesArray = {
                        size1.isChecked(), size2.isChecked(),
                        size3.isChecked(), typeStandard.isChecked(),
                        typeThin.isChecked(), typeCheese.isChecked()};

                if (checkBoxes.stream().noneMatch(CompoundButton::isChecked))
                    Toast.makeText(getContext(), "Виберіть розмір піци і тип тіста!",
                            Toast.LENGTH_SHORT).show();

                else if (checkBoxes.stream().limit(3).noneMatch(CompoundButton::isChecked))
                    Toast.makeText(getContext(), "Виберіть розмір піци!",
                        Toast.LENGTH_SHORT).show();

                else if (checkBoxes.stream().skip(3).limit(2).noneMatch(CompoundButton::isChecked))
                    Toast.makeText(getContext(), "Виберіть тип тіста!",
                            Toast.LENGTH_SHORT).show();

                else {
                    ResultFragment resultFragment = new ResultFragment();
                    Bundle bundle = new Bundle();

                    bundle.putStringArrayList("key1", getParameters(checkBoxes));
                    resultFragment.setArguments(bundle);

                    FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, resultFragment);
                    ft.commit();
                }
            }
        });


        size1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    size2.setChecked(false);
                    size3.setChecked(false);
                }
            }
        });
        size2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    size1.setChecked(false);
                    size3.setChecked(false);
                }
            }
        });
        size3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    size1.setChecked(false);
                    size2.setChecked(false);
                }
            }
        });
        typeStandard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    typeThin.setChecked(false);
                }
            }
        });
        typeThin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    typeStandard.setChecked(false);
                }
            }
        });
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getParameters(ArrayList<CheckBox> checkBoxes) {
        ArrayList<String> parameters = new ArrayList<>();
        checkBoxes.stream().filter(CompoundButton::isChecked).forEach(checkBox -> parameters.add((String) checkBox.getText()));
        return parameters;
    }
}