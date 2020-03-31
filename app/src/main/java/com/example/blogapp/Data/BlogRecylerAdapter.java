package com.example.blogapp.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogapp.Model.Blog;
import com.example.blogapp.R;


import java.util.Date;
import java.util.List;

public class BlogRecylerAdapter extends RecyclerView.Adapter<BlogRecylerAdapter.ViewHolder> {
    private Context context;
    private List<Blog> blogList;

    public BlogRecylerAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Blog blog = blogList.get(position);
    String imageurl = null;

    holder.title.setText(blog.getTitle());
    holder.description.setText(blog.getDescription());


    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
    String formatedDate = dateFormat.format(new Date( Long.valueOf(blog.getTimestamp())).getTime());
        holder.timestamp.setText(formatedDate);

        imageurl=blog.getImage();

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
public TextView title;
public TextView description ;
public TextView timestamp ;
public ImageView image;
String userid ;


        public ViewHolder(@NonNull View view, Context ctx) {

            super(view);
            context =ctx;
            title = (TextView) view.findViewById(R.id.postTitleList);
            description =(TextView)view.findViewById(R.id.postDescription);
            timestamp =(TextView)view.findViewById(R.id.postTimeList);
            image =(ImageView)view.findViewById(R.id.postimagelink);

            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ffor go to next activity
                }
            });


        }
    }
}
