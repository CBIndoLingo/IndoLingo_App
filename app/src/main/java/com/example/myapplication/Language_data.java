package com.example.myapplication;

import android.widget.ImageView;
import android.widget.TextView;

public class Language_data {

    private Integer flags;
    private String language_name;

    public Language_data(Integer flags, String language_name) {
        this.flags = flags;
        this.language_name = language_name;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}
