package com.example.onix_android;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivitySignIn extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout rootWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);

        mAuth = FirebaseAuth.getInstance();

        fillClassFields();
        addListeners();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Register");

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View registerWindow = layoutInflater.inflate(R.layout.register_window, null);
        dialog.setView(registerWindow);
        dialog.setCancelable(true);

        final TextInputEditText regEmail = registerWindow.findViewById(R.id.reg_input_email);
        final TextInputEditText regPass = registerWindow.findViewById(R.id.reg_pass);
        final TextInputEditText regPassConfirm = registerWindow.findViewById(R.id.reg_pass_confirm);
        final Button regButton = registerWindow.findViewById(R.id.reg_register_btn);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Errors check
                if (TextUtils.isEmpty(Objects.requireNonNull(regEmail.getText()).toString())) {
                    Snackbar.make(rootWindow, "Enter Email", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (!regPass.getText().toString().equals(regPassConfirm.getText().toString())) {
                    Snackbar.make(rootWindow, "Passwords not equals", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }
        });

        dialog.show();
    }

    /**
     * Method for add all listeners
     */
    private void addListeners() {
        addBtnSignInListener();
        addBtnRegisterListener();
    }

    private void addBtnRegisterListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
    }

    private void addBtnSignInListener() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText email = findViewById(R.id.input_email);
                TextInputEditText pass = findViewById(R.id.input_pass);

                // pass and email must be equals
                if (email.getText().toString().equals(pass.getText().toString())) {
                    Intent intent = new Intent(".MainFrame");
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivitySignIn.this, "email and pass must be equals", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Method for fill all private values in this class
     */
    private void fillClassFields() {
        btnSignIn = findViewById(R.id.btn_signIn);
        btnRegister = findViewById(R.id.btn_register);
        rootWindow = findViewById(R.id.root_window);

        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
    }
}
