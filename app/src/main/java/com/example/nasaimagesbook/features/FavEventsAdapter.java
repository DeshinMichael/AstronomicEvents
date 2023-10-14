package com.example.nasaimagesbook.features;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasaimagesbook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavEventsAdapter extends RecyclerView.Adapter<FavEventsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> list_id;
    private ArrayList<String> list_image;
    private ArrayList<String> list_name;
    private ArrayList<String> list_desc;
    private OnItemClickListener mListener;

    public FavEventsAdapter(Context context, ArrayList<String> id,
                            ArrayList<String> image,
                            ArrayList<String> name,
                            ArrayList<String> desc) {
        this.context = context;
        this.list_id = id;
        this.list_image = image;
        this.list_name = name;
        this.list_desc = desc;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageEvent;
        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageEvent = itemView.findViewById(R.id.image_item);
            tvName = itemView.findViewById(R.id.name_item);
        }
    }

    @NonNull
    @Override
    public FavEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fav_events, parent, false);
        FavEventsAdapter.ViewHolder viewHolder = new FavEventsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavEventsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String imageUrl = list_image.get(position);
        Picasso.get().load(imageUrl).into(holder.imageEvent);

        String name = list_name.get(position);
        holder.tvName.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_id.size();
    }

    public ArrayList<String> getList_id() {
        return list_id;
    }

    public ArrayList<String> getList_image() {
        return list_image;
    }

    public ArrayList<String> getList_name() {
        return list_name;
    }

    public ArrayList<String> getList_desc() {
        return list_desc;
    }
}
