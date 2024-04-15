package org.vedy.mypolicies.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.vedy.mypolicies.Adapter.PolicyListAdapter;
import org.vedy.mypolicies.Model.Policy;
import org.vedy.mypolicies.R;
import org.vedy.mypolicies.databinding.ActivityListPolicyBinding;

import java.util.ArrayList;

public class ListPolicyActivity extends BaseActivity {
    ActivityListPolicyBinding binding;
    private RecyclerView.Adapter adapterListPolicy;

    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Policies");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Policy> list = new ArrayList<>();

        Query query ;
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else{
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Policy.class));

                    }
                    if(list.size()>0){

                        binding.savedPollicyListView.setLayoutManager(new GridLayoutManager(ListPolicyActivity.this,2));
                        adapterListPolicy = new PolicyListAdapter(list);
                        binding.savedPollicyListView.setAdapter(adapterListPolicy);

                    }

                    binding.progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(ListPolicyActivity.this, "No Items In this Category Exist", Toast.LENGTH_LONG).show();
                    binding.progressBar.setVisibility(View.GONE);
                    Intent i = new Intent(ListPolicyActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }




    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId",0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText  = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());
    }
}