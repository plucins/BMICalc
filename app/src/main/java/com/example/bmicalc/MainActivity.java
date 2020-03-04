package com.example.bmicalc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
    }

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if(heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)) {

            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if(Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.wyglodzenie);
        } else if(Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0){
            bmiLabel = getString(R.string.wychudzenie);
        } else if(Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 18.5f) <= 0){
            bmiLabel = getString(R.string.niedowaga);
        } else if(Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 25f) <= 0){
            bmiLabel = getString(R.string.wartosc_prawidlowa);
        } else if(Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 30f) <= 0){
            bmiLabel = getString(R.string.nadwaga);
        } else if(Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 35f) <= 0){
            bmiLabel = getString(R.string.I_stopien_otylosci);
        } else if(Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 40f) <= 0){
            bmiLabel = getString(R.string.II_stopien_otylosci);
        } else {
            bmiLabel = getString(R.string.otyłosc_skrajna);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}
