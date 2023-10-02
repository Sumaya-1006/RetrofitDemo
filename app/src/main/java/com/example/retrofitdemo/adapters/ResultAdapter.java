package com.example.retrofitdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.models.MovieModel;
import com.example.retrofitdemo.service.MovieListener;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MovieViewHolder>{
    ArrayList<MovieModel> list;
    MovieListener listener;
    String url = "https://image.tmdb.org/t/p/w500";

    public ResultAdapter(  MovieListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResultAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_layout,parent,false);

        return new MovieViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final  MovieModel model = list.get(position);

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+model.getPosterPath()).into(((MovieViewHolder)holder).img);
        ((MovieViewHolder)holder).id.setText(model.getOverview());
        ((MovieViewHolder)holder).title.setText("title : "+model.getTitle());
        ((MovieViewHolder)holder).date.setText("popularity : "+model.getPopularity());

           /*if (ApiRepositories.BASE_URL.startsWith("https")) {
            Glide.with(holder.img.getContext())
                    .load(url+model.getPosterPath())
                    .error("https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg")
                    .fitCenter()
                    .into(holder.img);
        } else {
            Glide.with(holder.img.getContext())
                    .load(url.replace("http","https"))
                    .error(url)
                    .fitCenter()
                    .into(holder.img);
        }*/

    }

    @Override
    public int getItemCount() {

        if(list !=null){
            return list.size();
        }
        return 0;
    }

    public void setList(ArrayList<MovieModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView id,title,date;
        public MovieViewHolder(@NonNull View itemView, MovieListener listener) {
            super(itemView);

            img = itemView.findViewById(R.id.imgView);
            id = itemView.findViewById(R.id.id_text);
            title = itemView.findViewById(R.id.title_text);
            date = itemView.findViewById(R.id.date_text);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
        listener.onMovieListener(getAdapterPosition());

        }
    }

}

