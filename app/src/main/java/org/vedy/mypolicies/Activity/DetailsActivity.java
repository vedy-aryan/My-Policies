package org.vedy.mypolicies.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import com.bumptech.glide.Glide;

import org.vedy.mypolicies.Model.Policy;
import org.vedy.mypolicies.R;
import org.vedy.mypolicies.databinding.ActivityDetailsBinding;

public class DetailsActivity extends BaseActivity {
    private ActivityDetailsBinding binding;
    private Policy object;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getBundleExtra();
        setVariable();


    }



    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailsActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);


        binding.titleDetailsTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        String ResultAge = "";
        int AgeId = object.getAgeId();
        if (AgeId == 0 ){
            ResultAge = "Age Eligibility : 0-14yrs";
        } else if (AgeId == 1) {
            ResultAge = "Age Eligibility : 14-18yrs";
        } else if (AgeId == 2) {
            ResultAge = "Age Eligibility : 18-60yrs";
        } else if (AgeId == 3) {
            ResultAge = "Age Eligibility : Greater than 60yrs";
        }else {
            ResultAge = "Age Eligibility : All Age Groups";
        }

        binding.ageDetailsTxt.setText(ResultAge);

        String ResultCategory = "";
        int CategoryId = object.getCategoryId();

        if (CategoryId == 0){
            ResultCategory = "HealthCare";
        } else if (CategoryId == 1) {
            ResultCategory = "Transport";
        }else if (CategoryId == 2) {
            ResultCategory = "Finance";
        }else if (CategoryId == 3) {
            ResultCategory = "Education";
        }else if (CategoryId == 4) {
            ResultCategory = "Agriculture";
        }else if (CategoryId == 5) {
            ResultCategory = "Public Safety";
        }else if (CategoryId == 6) {
            ResultCategory = "Sports";
        }else if (CategoryId == 7) {
            ResultCategory = "Employment";
        }else {
            ResultCategory = "Other";
        }

        binding.categoryDetailsTxt.setText(ResultCategory);





    }

    private void getBundleExtra() {
        object = (Policy) getIntent().getSerializableExtra("object");
    }
}