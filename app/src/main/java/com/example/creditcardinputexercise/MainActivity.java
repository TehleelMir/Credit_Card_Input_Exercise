package com.example.creditcardinputexercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String currentMMYY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.submit_button).setOnClickListener(this);

        EditText temp = ((TextInputLayout) findViewById(R.id.mm_yy)).getEditText();
        setMMYYListener(temp);
    }

    @Override
    public void onClick(View view) {
        String cardNumber = ((TextInputLayout) findViewById(R.id.cardNumber)).getEditText().getText().toString().trim();
        String mm_yy = ((TextInputLayout) findViewById(R.id.mm_yy)).getEditText().getText().toString().trim();
        String s_code = ((TextInputLayout) findViewById(R.id.s_code)).getEditText().getText().toString().trim();
        String firstName = ((TextInputLayout) findViewById(R.id.firstName)).getEditText().getText().toString().trim();
        String lastName = ((TextInputLayout) findViewById(R.id.lastName)).getEditText().getText().toString().trim();

        if (cardNumber.equals("") || mm_yy.equals("") || s_code.equals("") || firstName.equals("") || lastName.equals("")) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int cardType = Validation.isCardNumberValid(cardNumber);
        if (cardType == 0) {
            Toast.makeText(this, "Invalid card number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Validation.isMMYYValid(mm_yy)) {
            Toast.makeText(this, "Invalid MM/YY", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Validation.isS_CodeValid(s_code, cardType)) {
            Toast.makeText(this, "Invalid Security code", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Validation.isFirstNameValid(firstName)) {
            Toast.makeText(this, "Invalid first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Validation.isLastNameValid(lastName)) {
            Toast.makeText(this, "Invalid last name", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Payment was successful");
        dialog.show();

    }

    private void setMMYYListener(EditText temp) {
        temp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 2 && !(currentMMYY.equals(charSequence.toString()))) {
                    temp.setText(charSequence + "/");
                    temp.setSelection(charSequence.length() + 1);
                }
                currentMMYY = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}