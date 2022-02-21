package com.naturewallpaper.allimagesfolder.activites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.naturewallpaper.allimagesfolder.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Viewholder> {
    Context context;
    ArrayList<ModelImage> modelImageArrayList;
    public ImageAdapter( Context context, ArrayList<ModelImage> modelImageArrayList){
        this.context=context;
        this.modelImageArrayList=modelImageArrayList;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_list_images,parent,false);
        return new ImageAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ModelImage modelImage= modelImageArrayList.get(position);

        holder.name_folder.setText(""+modelImage.getFolderName());
        holder.size_folder.setText(""+modelImage.getNoPicturs());
        Glide.with(context).load(modelImage.getFstPicture()).into(holder.image_show);

    }

    @Override
    public int getItemCount() {
        return modelImageArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView image_show;
        TextView name_folder,size_folder;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image_show=itemView.findViewById(R.id.image_show);
            name_folder=itemView.findViewById(R.id.name_folder);
            size_folder=itemView.findViewById(R.id.size_folder);
        }
    }
}
