package com.example.student_community.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_community.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostTutucu> {
    private Context mContext;
    private List<Posts> postsList;

    public PostAdapter(Context mContext, List<Posts> postsList) {
        this.mContext = mContext;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item,parent,false);
        return new PostTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostTutucu holder, int position) {
        final Posts post = postsList.get(position);

        holder.baslik.setText(post.getBaslik_id());
        holder.konum.setText(post.getKonum_id());
        holder.kisaaciklama.setText(post.getKisaaciklama_id());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class PostTutucu extends RecyclerView.ViewHolder{
        public TextView baslik;
        public TextView konum;
        public TextView kisaaciklama;
        public Button detay;

        public PostTutucu(@NonNull View itemView) {
            super(itemView);
            baslik = itemView.findViewById(R.id.baslik);
            konum = itemView.findViewById(R.id.konum);
            kisaaciklama = itemView.findViewById(R.id.kisaaciklama);
            detay = itemView.findViewById(R.id.detay);
        }
    }

}
