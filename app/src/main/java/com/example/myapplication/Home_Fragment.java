package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

        ImageButton current_courese=(ImageButton)v.findViewById(R.id.current_course);
        current_courese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder courseSelctedDialog= new AlertDialog.Builder(getActivity());
                View courseView= getLayoutInflater().inflate(R.layout.selected_course_dialog,null);

                RecyclerView recyclerView = courseView.findViewById(R.id.course_list);
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
                courseSelctedDialog.setView(courseView);
                AlertDialog dialog= courseSelctedDialog.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp= dialog.getWindow().getAttributes();

                wmlp.gravity= Gravity.TOP;
                wmlp.x=0;
                wmlp.y=100;
                dialog.show();

            }
        });
        return v;
    }
}
