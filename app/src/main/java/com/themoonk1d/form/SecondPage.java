package com.themoonk1d.form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.themoonk1d.form.databinding.ActivitySecondPageBinding;

public class SecondPage extends AppCompatActivity {
    ActivitySecondPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bk)));
        setTitle("Student Info");
        binding = ActivitySecondPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String FullName = intent.getStringExtra("FullName");
        String Id = intent.getStringExtra("Id");
        String Date = intent.getStringExtra("Date");

        binding.fullname.setText(FullName);
        binding.studId.setText(Id);
        binding.date.setText(Date);

    }
}