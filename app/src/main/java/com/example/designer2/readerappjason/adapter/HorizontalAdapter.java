package com.example.designer2.readerappjason.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.designer2.readerappjason.R;
import com.example.designer2.readerappjason.activities.AnimeActivity;
import com.example.designer2.readerappjason.activities.MainActivity;
import com.example.designer2.readerappjason.model.Category;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>  {
    private Context myContext;
    private List<Category> mCateg;
    RequestOptions option;

    public HorizontalAdapter(Context mContext, List<Category> mCateg) {
        this.myContext = mContext;
        this.mCateg = mCateg;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(myContext);
        view = inflater.inflate(R.layout.horizontal_row,viewGroup,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCateg.get(viewHolder.getAdapterPosition()).getName().contentEquals("Attack on Titan")) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.firstAnime = new ArrayList<>();

                    mainActivity.firstAnime.clear();
                    mainActivity.jsonrequest();





                   // Intent i = new Intent(myContext, AnimeActivity.class);
                  //  i.putExtra("anime_name", mCateg.get(viewHolder.getAdapterPosition()).getName());

                  //  i.putExtra("anime_img", mCateg.get(viewHolder.getAdapterPosition()).getImage_url());

                   // myContext.startActivity(i);
                }
            }
        });


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mCateg.get(i).getName());




        ///load image from internet by using glibe

        Glide.with(myContext).load(mCateg.get(i).getImage_url()).apply(option).into(myViewHolder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return mCateg.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;



        CircleImageView thumbnail;
        RelativeLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            container = itemView.findViewById(R.id.container2);

            thumbnail = itemView.findViewById(R.id.image_view);

        }
    }


}
