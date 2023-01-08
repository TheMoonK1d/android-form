package com.themoonk1d.form;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.themoonk1d.form.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year;
    private int month;
    private int day;
    private String pickedDate, fName, lName, department, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bk)));

        binding.selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
                Toast.makeText(getApplicationContext(), "Select a date", Toast.LENGTH_SHORT).show();
            }
        });
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // binding.
               fName = String.valueOf(binding.fName.getText());
               lName = String.valueOf(binding.lName.getText());
               id = String.valueOf(binding.id.getText());
                if (fName.isEmpty() && lName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Full name first", Toast.LENGTH_SHORT).show();
                }else if (fName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter First name", Toast.LENGTH_SHORT).show();
                }else if (lName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Last name", Toast.LENGTH_SHORT).show();
                }else if (id.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (binding.textView5.getText().equals("Select a date")){
                        Toast.makeText(getApplicationContext(), "Today's date has been chosen", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(getApplicationContext(), SecondPage.class);
                    intent.putExtra("FullName", fName + " " + lName);
                    intent.putExtra("Id", id);
                    intent.putExtra("Date", pickedDate);
                    startActivity(intent);
                }


            }
        });
    }
    protected Dialog onCreateDialog(int id) {
        if (id == 0) {
            return new DatePickerDialog(this,
                    DateListener, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2+1, arg3);

                    binding.textView5.setText("Date Picked "+pickedDate);
                    Toast.makeText(getApplicationContext(), pickedDate + " picked", Toast.LENGTH_SHORT).show();
                    binding.selectDateBtn.setText("CHANGE DATE");
                }
            };
    private void showDate(int year, int month, int day) {
        StringBuilder date = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
        pickedDate = String.valueOf(date);
    }
}