package com.example.converter.login.ui.login;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.converter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText usernameEditText;
    EditText passwordEditText;
    Button signInBtn;
    Button signUpBtn;
    ProgressBar loadingProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        signInBtn = findViewById(R.id.signin);
        signUpBtn = findViewById(R.id.signup);
        loadingProgressBar = findViewById(R.id.loading);


        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signInBtn.callOnClick();
                }
                return false;
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                attemptLogin();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                attemptSignUp();
            }
        });
    }

    private void attemptSignUp() {
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Hello "+user.getDisplayName(),Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void attemptLogin() {
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
