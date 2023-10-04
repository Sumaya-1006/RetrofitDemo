package com.example.retrofitdemo.adapters;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MovieViewHolder>{
    ArrayList<MovieModel> list;
    MovieListener listener;
    Context context;

    public ResultAdapter(ArrayList<MovieModel> list, MovieListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    String url = "https://image.tmdb.org/t/p/w500/";

   /* public ResultAdapter(  MovieListener listener) {
        this.list = list;
        this.listener = listener;
    }
*/
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

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+model.getPosterPath()).into(((MovieViewHolder)holder).img);
        ((MovieViewHolder)holder).id.setText("title : "+model.getTitle());
        ((MovieViewHolder)holder).title.setText(model.getOverview());
        ((MovieViewHolder)holder).date.setText("Date : "+model.getReleaseDate());


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

    public MovieModel getSelectedMovie(int position) {
        if(list != null){
            if(list.size()>0)
                return list.get(position);
        }
        return null;
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

