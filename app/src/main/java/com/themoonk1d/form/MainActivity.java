package com.themoonk1d.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.themoonk1d.form.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private int year;
    private int month;
    private int day;
    private int updatedYear;
    private String pickedDate, fName, lName, department, id;

    Language language = new Language(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String[] stringArray = getResources().getStringArray(R.array.course);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bk)));
        binding.spinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                stringArray
        ));


        binding.selectDateBtn.setOnClickListener(view12 -> {
            showDialog(0);
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_hint4), Toast.LENGTH_SHORT).show();
        });
        binding.nextBtn.setOnClickListener(view1 -> {
           department= binding.spinner.getSelectedItem().toString();
           fName = String.valueOf(binding.fName.getText());
           lName = String.valueOf(binding.lName.getText());
           id = String.valueOf(binding.id.getText());
            if (fName.isEmpty() && lName.isEmpty()) {
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.full_name_toast), Toast.LENGTH_SHORT).show();
            }else if (fName.isEmpty()){
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.f_name_toast), Toast.LENGTH_SHORT).show();
            }else if (lName.isEmpty()){
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.lname_toast), Toast.LENGTH_SHORT).show();
            }else if (id.isEmpty()){
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.id_toast), Toast.LENGTH_SHORT).show();
            }
            else {
                if (binding.textView5.getText().equals("Select a date")){
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.today_toast), Toast.LENGTH_SHORT).show();
                }
                switch (department) {
                    case "Accounting":
                        id = "ACT/" + id + "/" + updatedYear;
                        break;
                    case "Computer Science":
                        id = "RCD/" + id + "/" + updatedYear;
                        break;
                    case "Management":
                        id = "MNG/" + id + "/" + updatedYear;
                        break;
                    case "Marketing":
                        id = "MRK/" + id + "/" + updatedYear;
                        break;
                    default:
                        department = getResources().getString(R.string.dep_not);
                        break;
                }
                Intent intent = new Intent(getApplicationContext(), SecondPage.class);
                intent.putExtra("FullName", fName + " " + lName);
                intent.putExtra("Id", id);
                intent.putExtra("Date", pickedDate);
                intent.putExtra("Dep", department);
                startActivity(intent);
            }


        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lang_amh:
                Toast.makeText(this, "Please wait...Changing language to Amharic", Toast.LENGTH_SHORT).show();
                language .changeRes("am");
                recreate();
                return true;
            case R.id.lang_eng:
                Toast.makeText(this, "እባክህ ጠብቅ...ቋንቋ ወደ እንግሊዝኛ በመቀየር ላይ ነው", Toast.LENGTH_SHORT).show();
                language .changeRes("en");
                recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 0) {
            return new DatePickerDialog(this,
                    DateListener, year, month, day);
        }
        return null;
    }

    private final DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2+1, arg3);

                    binding.textView5.setText(getResources().getText(R.string.date_picked, pickedDate));
                    Toast.makeText(getApplicationContext(), pickedDate, Toast.LENGTH_SHORT).show();
                    binding.selectDateBtn.setText(getResources().getText(R.string.change_date));
                }
            };
    private void showDate(int year, int month, int day) {
        pickedDate = day + "/" + month + "/" + year;
        dateUpdate(year);

    }
    public void dateUpdate(int year)
    {
        updatedYear = year;
    }
}