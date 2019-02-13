package com.android.guillaume.mynews.controllers.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.ResultActivity;
import com.android.guillaume.mynews.utils.CloseDialogListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDialogFragment extends Fragment {

    @BindView(R.id.result_toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_bar)
    SearchView searchView;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.checkBox3)
    CheckBox checkBox3;
    @BindView(R.id.checkBox4)
    CheckBox checkBox4;
    @BindView(R.id.checkBox5)
    CheckBox checkBox5;
    @BindView(R.id.checkBox6)
    CheckBox checkBox6;
    @BindView(R.id.search_btn)
    Button btn;
    @BindView(R.id.begin_dateText)
    EditText beginDateText;
    @BindView(R.id.end_dateText)
    EditText endDateText;

    private String[] checkBoxNames = new String[]{"Health", "Sports", "Business", "Technology", "World", "U.S"};
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private CloseDialogListener closeDialog;
    private String errorMessage;

    public SearchDialogFragment() {
        // Required empty public constructor
    }
    public static SearchDialogFragment newInstance() {
        return new SearchDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_search_dialog,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // For configure item action in toolbar
        this.configureToolbarItem();

        // For Submit btn
        this.configureSubmitBtn();

        // For Checkboxes configuration
        this.configureCheckBox();

        this.configureDatePicker(beginDateText);
        this.configureDatePicker(endDateText);

        //Define dialog event Callback
        this.closeDialog = (CloseDialogListener) getParentFragment();

    }

    /************************ UI CONTROLS *******************/

    //Add OnClickListener on submit btn
    private void configureSubmitBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Submit test +  set values + start Activity
                if(isValidSearchFilter()) {
                    //Callback button clicked
                    closeDialog.buttonClicked(true);
                    putExtraInputValues();
                } else {
                    Snackbar.make(getView().findViewById(R.id.search_dialog),errorMessage,Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    // Add OnClickListener on close Toolbar btn
    private void configureToolbarItem() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog.buttonClicked(true);
            }
        });
    }


    private ArrayList<String> getCheckedBoxes(){
        ArrayList<String> result = new ArrayList<>();

        for (CheckBox box : this.checkBoxes) {
            if(box.isChecked()){
                result.add(box.getText().toString().toLowerCase());
            }
        }
        return result;
    }

    public void putExtraInputValues() {
        Intent intent = new Intent(getContext(), ResultActivity.class);

        intent.putExtra("EXTRA_TEXT", searchView.getQuery().toString());
        intent.putStringArrayListExtra("EXTRA_BOXES", getCheckedBoxes());
        startActivity(intent);
    }


    public void configureDatePicker(final TextView dateView){
        //Instance of calendar
        final Calendar calendar = Calendar.getInstance();

        // Date Format to display
        String myFormat ="yyyy/MM/d";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        //Set default Value
        dateView.setText(dateFormat.format(calendar.getTime()));

       final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                //Show date
                dateView.setText(dateFormat.format(calendar.getTime()));
            }
        };

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        datePicker,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime()); //Set max Date (now)
                dialog.show();
            }
        });
    }

    private void configureCheckBox(){
        //Define counter
        int i = 0;

        //Define list of Checkboxes
        checkBoxes.add(checkBox);
        checkBoxes.add(checkBox2);
        checkBoxes.add(checkBox3);
        checkBoxes.add(checkBox4);
        checkBoxes.add(checkBox5);
        checkBoxes.add(checkBox6);

        //Add name text to each checkbox
        for (CheckBox box : checkBoxes) {
            box.setText(this.checkBoxNames[i]);
            i++;
        }

        //Set selected checkbox
        this.setCategoryFilters();
    }

    private void setCategoryFilters(){
        //TODO: SharedPreferences
        //Default Checkbox checked
        checkBox.setChecked(true);
        checkBox5.setChecked(true);
        checkBox3.setChecked(true);
    }

    private Boolean isValidSearchFilter(){

        if(this.searchView.getQuery() == null || this.searchView.getQuery().toString().isEmpty()) {
            this.errorMessage = "Please, enter query term";
            return false;
        }else {
            return true;
        }
    }
}
