package org.vedy.mypolicies.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.vedy.mypolicies.Helper.AuthenticationHelperse;
import org.vedy.mypolicies.R;
import org.vedy.mypolicies.databinding.ActivitySignupBinding;

public class SignupActivity  extends BaseActivity {
    ActivitySignupBinding binding;

    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();

    }

    private void setVariable() {
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.userEdt.getText().toString();
                String password = binding.passEdt.getText().toString();
                String name = binding.namee.getText().toString();
                String age = binding.age.getText().toString();
                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioforgender);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(selectedId);
                String Gender = radioButton.getText().toString();




                if(password.length()<8){
                    Toast.makeText(SignupActivity.this,"Your Password to be atleast 8 charecters long",Toast.LENGTH_LONG).show();
                    return ;
                }else{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, task -> {
                        if(task.isSuccessful()){
                            Log.i(TAG," OnComplete: ");
                            String ssss = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            db = FirebaseDatabase.getInstance();
                            reference = db.getReference("users");

                            AuthenticationHelperse authenticationHelperse = new AuthenticationHelperse(ssss,name,age,Gender);
                            reference.child(ssss).setValue(authenticationHelperse);

                            startActivity(new Intent(SignupActivity.this, ProfileActivity.class));
                        }else{
                            Log.i(TAG," Failed: ",task.getException());
                            Toast.makeText(SignupActivity.this,"EMail Already Exists",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }
}