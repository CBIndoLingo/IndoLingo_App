package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    public Home_Fragment() {
        // Required empty public constructor
    }
    private int i = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_, container, false);
        final LinearLayout linearLayout = v.findViewById(R.id.child_fragment_home);
        ImageButton imageButton = v.findViewById(R.id.current_course);
        RecyclerView recyclerView = v.findViewById(R.id.course_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        selectedCourse_data[] language_data= new selectedCourse_data[]{
                new selectedCourse_data(R.drawable.english_flag),
                new selectedCourse_data(R.drawable.india_flag),
                new selectedCourse_data(R.drawable.china_flag),
        };

        courseSelectedAdapter languageAdapter= new courseSelectedAdapter(language_data,getContext());
        recyclerView.setAdapter(languageAdapter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0){
                    linearLayout.setVisibility(View.VISIBLE);
                    i++;
                }
                else if (i == 1){
                    linearLayout.setVisibility(View.INVISIBLE);
                    i =0;
                }
            }
        });
        return v;
    }
}
