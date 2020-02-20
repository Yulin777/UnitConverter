package com.example.converter.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.converter.R;
import com.example.converter.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText passwordConfirmEditText;
    Button signUpBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        passwordConfirmEditText = findViewById(R.id.password_confirmation);
        signUpBtn = findViewById(R.id.signup);


        passwordConfirmEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signUpBtn.callOnClick();
                }
                return false;
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignUp();
            }
        });

    }

    private void attemptSignUp() {
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirmation = passwordConfirmEditText.getText().toString();

        if (!password.equals(passwordConfirmation)) {
            Toast.makeText(SignUpActivity.this, "passwords do no match", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, "Hello " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            openMainActivity();
                        } else {
                            Toast.makeText(SignUpActivity.this, "sign up failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void openMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.putExtra("uid", mAuth.getUid());
        startActivity(intent);
    }

}
