package org.vedy.mypolicies.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.vedy.mypolicies.Adapter.BestPolicyAdapter;
import org.vedy.mypolicies.Adapter.CategoryAdapter;
import org.vedy.mypolicies.Helper.AuthenticationHelperse;
import org.vedy.mypolicies.Model.Category;
import org.vedy.mypolicies.Model.Policy;
import org.vedy.mypolicies.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private AuthenticationHelperse uid;
    private String AgeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        initCategory();



    }

    private void setVariable() {
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        //search Text
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtextt = binding.searchTxt.getText().toString();
                if(!searchtextt.isEmpty()){
                    Intent intent = new Intent(MainActivity.this,ListPolicyActivity.class);
                    intent.putExtra("text",searchtextt);
                    intent.putExtra("isSearch",true);
                    startActivity(intent);
                }
            }
        });

        String ssss = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference myRef = database.getReference("users");


        Query query = myRef.orderByChild("uid").equalTo(ssss);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    uid = childSnapshot.getValue(AuthenticationHelperse.class);
                    binding.usernameText.setText(uid.getName());
                    AgeUser = uid.getAge();
                    initBestPolicy();
                    break;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void initBestPolicy() {

        DatabaseReference myRef = database.getReference("Policies");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Policy> list = new ArrayList<>();

        // Get AgeId from the Age provided using the Function and calling it from below
        int AgeId = getAgeID(AgeUser.toString());


        Query query = myRef.orderByChild("AgeId").equalTo(AgeId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Policy.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // For Policy for All Age Group

        Query query2 = myRef.orderByChild("AgeId").equalTo(4);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Policy.class));
                    }
                    if (list.size() > 0) {

                        binding.bestPolicyView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter adapter = new BestPolicyAdapter(list);
                        binding.bestPolicyView.setAdapter(adapter);


                    }
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }else {
                    Toast.makeText(MainActivity.this, "No Recommended Policy Found", Toast.LENGTH_SHORT).show();
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //The Function to convert Age Given to AgeId
    private int getAgeID(String ageUser) {
        int age = Integer.parseInt(ageUser);

        if(age>=0 && age<=14){
            return 0;
        } else if (age>14 && age<=18) {

            return 1;
        } else if (age>18 && age<=60) {
            return 2;
        }else {
            return 3;
        }
    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));
                    }
                    if (list.size() > 0) {
                        binding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        binding.categoryView.setAdapter(adapter);


                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//

}
