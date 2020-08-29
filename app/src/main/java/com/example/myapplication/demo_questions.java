package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class demo_questions extends AppCompatActivity {

    private CustomViewPager demo_pager;
    Adapter adapter;
    Button next;
    ProgressBar progressBar;
    ObjectAnimator progressAnimator;
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_questions);

        close= findViewById(R.id.demo_close_question);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(demo_questions.this,welcome.class);
                startActivity(intent);
                finish();
            }
        });

        next= findViewById(R.id.demo_next_button);
        next.setClickable(false);
        progressBar= findViewById(R.id.demo_progressBar);

        adapter= new Adapter(this);
        demo_pager = (CustomViewPager)findViewById(R.id.demo_pager);
        demo_pager.setAdapter(adapter);
        demo_pager.setPagingEnabled(false);


    }

    public void Next(View view) {
        demo_pager.setCurrentItem(demo_pager.getCurrentItem() + 1, true);
        next.setBackgroundResource(R.color.grey_button);
        next.setText("Choose the Ans");
        next.setClickable(false);
    }


    private class Adapter extends PagerAdapter{

        Context context;
        LayoutInflater inflater;

        public Adapter(Context context) {
            this.context = context;
        }

        String[] list_questions={
          "IS","HI","OK"
        };
        String[] list_answers={
                "IS","HI","OK"
        };

        @Override
        public int getCount() {
            return list_questions.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view==object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.demo_question_layout,container,false);
            LinearLayout linearLayout= view.findViewById(R.id.demo_questions_layout);
            TextView question= view.findViewById(R.id.demo_question);
            Button answer= view.findViewById(R.id.demo_answer_button);

            question.setText(list_questions[position]);
            answer.setText(list_answers[position]);

            container.addView(view);

            answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position!=2){
                    next.setClickable(true);
                    next.setBackgroundResource(R.drawable.button_task_continue);
                    next.setText("Good Work, Click for Next");
                    progressAnimator= ObjectAnimator.ofInt(progressBar,"progress",position*33,(position+1)*33);
                    progressAnimator.setDuration(1000);
                    progressAnimator.start();
                    }
                    else{
                        next.setVisibility(View.INVISIBLE);
                        final Dialog dialog= new Dialog(demo_questions.this);
                        dialog.setContentView(R.layout.dialog_demo_congratulations);
                        dialog.setCanceledOnTouchOutside(false);
                        Button createProfile = dialog.findViewById(R.id.demo_create_profile);
                        createProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(demo_questions.this, register.class);
                                startActivity(intent);
                                finish();
                            dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                }
            });
            return  view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout)object);
        }
    }
}
