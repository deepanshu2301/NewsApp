package com.codingblocks.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MeraAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<News> adaplist;
    Context ctx;
    View view1;
    public MeraAdapter(ArrayList<News> adaplist, MainActivity mainActivity) {
        this.adaplist=adaplist;
        ctx=mainActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.layout,parent,false);
        view1=view;
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final News news = adaplist.get(position);
        Holder LayoutHolder= (Holder) holder;
        LayoutHolder.title.setText(news.getTitle());
        LayoutHolder.description.setText(news.getDescription());
        Picasso.get()
                .load(news.getUrlToImage())
                .into(((Holder) holder).image);
        Button btn=view1.findViewById(R.id.btnview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx,DetailClass.class);
                i.putExtra("Title",news.getTitle());
                i.putExtra("Description",news.getDescription());
                i.putExtra("Author",news.getAuthor());
                i.putExtra("Published",news.getPublishedAt());
                i.putExtra("Image",news.getUrlToImage());
                i.putExtra("url",news.getUrl());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adaplist.size();
    }

     class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;
        public Holder(View view) {
            super(view);
            image=view.findViewById(R.id.imview);
            title=view.findViewById(R.id.title);
            description=view.findViewById(R.id.description);
        }
    }
}
