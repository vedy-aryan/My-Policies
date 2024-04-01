package org.vedy.mypolicies.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.vedy.mypolicies.Activity.DetailsActivity;
import org.vedy.mypolicies.Model.Policy;
import org.vedy.mypolicies.R;

import java.util.ArrayList;

public class BestPolicyAdapter extends RecyclerView.Adapter<BestPolicyAdapter.viewHolder> {

    ArrayList<Policy> items ;
    Context context;

    public BestPolicyAdapter(ArrayList<Policy> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_bestpolicy,parent,false);

        return new viewHolder(inflate);
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.titleTxt.setText(items.get(position).getTitle());
        int catId = items.get(position).getCategoryId();
        String text = "";
        switch(catId){

            case 0:{
                text = "HealthCare";
                break;
            }
            case 1:{
                text = "Transport";
                break;
            }
            case 2:{
                text = "Finance";
                break;
            }
            case 3:{
                text = "Education";
                break;
            }
            case 4:{
                text = "Agriculture";
                break;
            }
            case 5:{
                text = "Public Safety";
                break;
            }
            case 6:{
                text = "Sports";
                break;
            }
            case 7:{
                text = "Employment";
                break;
            }
            default:{
                text = "Other";
            }
        }

        holder.categoryTxt.setText(text);


        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);





        //Goto Details Page
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("object",items.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,categoryTxt;
        ImageView pic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.cardtitleTxt);
            categoryTxt = itemView.findViewById(R.id.cardCategoryTxt);
            pic = itemView.findViewById(R.id.img);

        }
    }
}
