package org.vedy.mypolicies.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.vedy.mypolicies.R;
import org.vedy.mypolicies.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

    }

    private void setVariable() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.userEdt.getText().toString();
                String password = binding.passEdt.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()){

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(LoginActivity.this, "PLease Enter Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });





        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
    }
}