package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class courseSelectedAdapter extends RecyclerView.Adapter<courseSelectedAdapter.ViewHolder> {
    selectedCourse_data[] selectedCourse_data;
    Context context;

    public courseSelectedAdapter(com.example.myapplication.selectedCourse_data[] selectedCourse_data, Context context) {
        this.selectedCourse_data = selectedCourse_data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_itemofcourseselected,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final selectedCourse_data selectedCourse_datalist= selectedCourse_data[position];
        holder.imageView.setImageResource(selectedCourse_datalist.getFlags());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,selectedCourse_datalist.getFlags(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedCourse_data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.course_icon);
        }
    }
}
