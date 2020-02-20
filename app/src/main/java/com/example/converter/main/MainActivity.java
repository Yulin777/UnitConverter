package com.example.converter.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.converter.R;

public class MainActivity extends AppCompatActivity implements MyView {
    private Presenter mPresenter;

    private EditText inputEditText;
    private Button convertBtn;
    private Spinner convertFromSpinner;
    private Spinner convertToSpinner;
    private TextView resultTextView;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uid = getIntent().getStringExtra("uid");

        mPresenter = new Presenter(this);

        findViews();
        setViews();
    }

    private void setViews() {
        setSpinners();
        setConvertButton();
    }

    private void setSpinners() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.convertion_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        convertFromSpinner.setAdapter(adapter);
        convertToSpinner.setAdapter(adapter);
    }

    private void setConvertButton() {
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onConversionBtnClicked();
            }
        });
    }

    private void findViews() {
        inputEditText = findViewById(R.id.input);
        convertBtn = findViewById(R.id.convert_btn);
        convertFromSpinner = findViewById(R.id.convert_from_spinner);
        convertToSpinner = findViewById(R.id.convert_to_spinner);
        resultTextView = findViewById(R.id.result);
    }

    @Override
    public double getInput() {
        return Double.valueOf(inputEditText.getText().toString());
    }

    @Override
    public Type getFromType() {
        return getTypeFromString(convertFromSpinner);
    }

    @Override
    public Type getToType() {
        return getTypeFromString(convertToSpinner);
    }

    private Type getTypeFromString(Spinner spinner) {
        switch (spinner.getSelectedItem().toString()) {
            case "MM":
                return Type.MM;
            case "CM":
                return Type.CM;
            case "DM":
                return Type.DM;
            case "METER":
                return Type.METER;
            case "KM":
                return Type.KM;
        }
        return Type.METER;
    }

    @Override
    public void updateConvertedValue(double convertedValue) {
        resultTextView.setText(String.valueOf(convertedValue));
    }
}
