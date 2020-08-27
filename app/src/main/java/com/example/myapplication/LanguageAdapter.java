package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    Language_data[] language_data;
    Context context;

    public LanguageAdapter(Language_data[] language_data, Context context) {
        this.language_data = language_data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.language_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Language_data language_datalist= language_data[position];
        holder.language_name.setText(language_datalist.getLanguage_name());
        holder.flag.setImageResource(language_datalist.getFlags());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,language_datalist.getLanguage_name(),Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,demo_questions.class);
                intent.putExtra("LanguageName",language_datalist.getLanguage_name());
                intent.putExtra("flag",language_datalist.getFlags());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return language_data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flag;
        TextView language_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag=itemView.findViewById(R.id.flags);
            language_name= itemView.findViewById(R.id.language_name);
        }
    }
}
