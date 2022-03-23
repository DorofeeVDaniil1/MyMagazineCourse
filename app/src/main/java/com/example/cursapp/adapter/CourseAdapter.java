package com.example.cursapp.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursapp.CoursePage;
import com.example.cursapp.MainActivity;
import com.example.cursapp.R;
import com.example.cursapp.model.Cours;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CoursViewHolder> {

    Context context;//
    List<Cours> cours;//Список, где каждый элимент будет содержать значения символов

    public CourseAdapter(Context context, List<Cours> cours) {
        this.context = context;
        this.cours = cours;
    }

    @NonNull
    @Override
    //Указать какой конкретный дизайн будем использовать для отображения кждого конкретного элимента.
    public CoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.cours_item, parent,false);
        return new CourseAdapter.CoursViewHolder(courseItems);
    }
    //Что конкретно мы будем подставлять в сам дизайн
    @Override
    public void onBindViewHolder(@NonNull CoursViewHolder holder, int position) {

        holder.courseBg.setCardBackgroundColor(Color.parseColor(cours.get(position).getColor()));
        int imageId= context.getResources().getIdentifier("ic_" + cours.get(holder.getAdapterPosition()).getImg(),"drawable",context.getPackageName());

        holder.courseImage.setImageResource(imageId);

        holder.courseTitle.setText(cours.get(position).getTitle());
        holder.courseDate.setText(cours.get(position).getDate());
        holder.courseLevel.setText(cours.get(position).getLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CoursePage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                        new Pair<View,String>(holder.courseImage,"courseImage")
                        );

                intent.putExtra("courseBg",Color.parseColor(cours.get(holder.getAdapterPosition()).getColor()));
                intent.putExtra("courseImage",imageId);
                intent.putExtra("courseTitle",cours.get(position).getTitle());
                intent.putExtra("courseDate",cours.get(position).getDate());
                intent.putExtra("courseLevel",cours.get(position).getLevel());
                intent.putExtra("courseText",cours.get(position).getText());
                intent.putExtra("courseID",cours.get(position).getId());

                context.startActivity(intent,options.toBundle());
            }
        });


    }


    @Override
    public int getItemCount() {
        return cours.size();
    }

    public static final class CoursViewHolder extends RecyclerView.ViewHolder{

        CardView courseBg;
        ImageView courseImage;
        TextView courseTitle, courseDate,courseLevel;
        //С какими элиментами в дизайне мы будем рабоатать
        public CoursViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.title2);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);
        }
    }
}
