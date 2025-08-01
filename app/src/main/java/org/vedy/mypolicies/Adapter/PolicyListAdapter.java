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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.vedy.mypolicies.Activity.DetailsActivity;
import org.vedy.mypolicies.Model.Policy;
import org.vedy.mypolicies.R;

import java.util.ArrayList;

public class PolicyListAdapter extends RecyclerView.Adapter<PolicyListAdapter.viewholder>{
    ArrayList<Policy> items;
    Context context;
    public PolicyListAdapter(ArrayList<Policy> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PolicyListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_list_policy,parent,false);


        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PolicyListAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.yearTxt.setText("Since " +items.get(position).getYear());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);


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

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,yearTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.textView44);
            yearTxt = itemView.findViewById(R.id.yearTxt);
            pic = itemView.findViewById(R.id.img);

        }
    }
}
