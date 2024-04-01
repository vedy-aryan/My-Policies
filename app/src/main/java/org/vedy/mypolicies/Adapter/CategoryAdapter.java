package org.vedy.mypolicies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.vedy.mypolicies.Activity.ListPolicyActivity;
import org.vedy.mypolicies.Model.Category;
import org.vedy.mypolicies.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    ArrayList<Category> items ;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_category,parent,false);

        return new viewHolder(inflate);
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.titleTxt.setText(items.get(position).getName());

        // Get the background resource ID based on position
        int backgroundResourceId = getBackgroudResourseId(position);

        // Set the background resource to the ImageView
        holder.pic.setBackgroundResource(backgroundResourceId);

        // Load the image using Glide
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),"drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);



        // Get List View

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListPolicyActivity.class);
                intent.putExtra("CategoryId",items.get(position).getId());
                intent.putExtra("CategoryName",items.get(position).getName());
                context.startActivity(intent);
            }
        });



    }



    private int getBackgroudResourseId(int position){
        switch (position){
            case 0:{
                return R.drawable.cat_0_background;

            }
            case 1:{
                return R.drawable.cat_1_background;

            }
            case 2:{
                return R.drawable.cat_2_background;

            }
            case 3:{
                return R.drawable.cat_3_background;

            }
            case 4:{
                return R.drawable.cat_4_background;

            }
            case 5:{
                return R.drawable.cat_5_background;

            }
            case 6:{
                return R.drawable.cat_6_background;

            }
            case 7:{
                return R.drawable.cat_7_background;

            }
            default:{
                return -1;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);

        }
    }
}
