package org.vedy.mypolicies.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import org.vedy.mypolicies.R;
import org.vedy.mypolicies.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#FFE485"));
    }

    private void setVariable() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mAuth.getCurrentUser() != null){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }else{
                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
            }

        }
    });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,SignupActivity.class));
            }
        });
    }
}