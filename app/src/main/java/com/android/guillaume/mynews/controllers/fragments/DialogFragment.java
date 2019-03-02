package com.android.guillaume.mynews.controllers.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.ResultActivity;
import com.android.guillaume.mynews.utils.CloseDialogListener;
import com.android.guillaume.mynews.utils.NotificationJobService;
import com.android.guillaume.mynews.utils.PreferencesHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    //TODO: ADD suggestion to AutoCompleteTextView

    @BindView(R.id.result_toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_bar)
    EditText searchInput;
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
    @BindView(R.id.notif_button)
    Switch switchBtn;

    // To define tagTypeDialog param
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String SEARCH_ARTICLE = "SEARCH_ARTICLE";

    private String[] checkBoxNames = new String[]{"Health", "Sports", "Business", "Arts", "World", "Politics"};
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private CloseDialogListener closeDialog;
    private String errorMessage;

    //Instance of calendar
    private Calendar beginCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();

    private View dateSection, submitSection, notifiSection;
    private String tagTypeDialog;
    private String searchInputValue;

    private PreferencesHelper sharedPref;

    public DialogFragment() {
        // Required empty public constructor
    }

    public static DialogFragment newInstance(String TAG) {
        DialogFragment dialogFragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putString("TYPE_TAG",TAG);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            this.tagTypeDialog = getArguments().getString("TYPE_TAG");

        Log.d(tagTypeDialog, "onCreate: " + " ----Dialog----->  "+ tagTypeDialog);
        this.sharedPref = new PreferencesHelper(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_search_dialog,container,false);
        this.dateSection = view.findViewById(R.id.dialog_date_section);
        this.submitSection = view.findViewById(R.id.dialog_submit_section);
        this.notifiSection = view.findViewById(R.id.dialog_notification_section);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (this.tagTypeDialog){
            case SEARCH_ARTICLE:
                this.showSearchArticleConfig();
                break;
            case NOTIFICATION:
                this.showNotificationConfig();
                break;
            default:
        }
    }

    private void showNotificationConfig(){
        // For configure item action in toolbar
        this.configureToolbarItem();

        //For configure EditText
        this.configureSearchInput();

        // Hide DatePicker Layout
        dateSection.setVisibility(View.GONE);

        // Hide submit Button Layout
        submitSection.setVisibility(View.GONE);

        // For Checkboxes configuration
        this.configureCheckBox();

        this.addCheckedChangeSwitchListener();

        // For switch button configuration
        this.configureSwitchButton();

        //Define dialog event Callback
        this.closeDialog = (CloseDialogListener) getParentFragment();
    }

    private void showSearchArticleConfig(){

        // For configure item action in toolbar
        this.configureToolbarItem();

        //For configure EditText
        this.configureSearchInput();

        // Hide NotificationBuilder section
        this.notifiSection.setVisibility(View.GONE);

        // For Submit btn
        this.configureSubmitBtn();

        // For Checkboxes configuration
        this.configureCheckBox();

        this.configureDatePicker(beginDateText, endDateText);

        //Define dialog event Callback
        this.closeDialog = (CloseDialogListener) getParentFragment();
    }


    /************************ UI CONTROLS *******************/

    // Add OnClickListener on close Toolbar btn
    private void configureToolbarItem() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSearchInput();

                NotificationJobService jobService = new NotificationJobService(getContext());
                if(switchBtn.isChecked())
                    jobService.cancelJob();
                jobService.createJob(searchInput.getText().toString(), getCheckedBoxesValues());

                closeDialog.buttonClicked(true);
            }
        });
    }

    //Add OnClickListener on submit btn
    private void configureSubmitBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValidSearchFilter()) {
                    closeDialog.buttonClicked(true);//Callback to close dialog
                    putExtraInputValues();
                } else {
                    displayErrorMessage();
                }
            }
        });
    }


    // Configure (Date) EditText to open DatePicker
    public void configureDatePicker(final TextView beginDate, final TextView endDate){

        // Date Format to display
        String myFormat ="yyyy/MM/d";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        long intervalDate = (1000 * 60 * 60 * 24) * 14; // = 14j
        long currentDate = endCalendar.getTimeInMillis(); // the current time

        //Set default Value for begin Date
        beginDate.setText(dateFormat.format(currentDate - intervalDate));
        //Set begin date to calendar
        beginCalendar.setTimeInMillis(currentDate - intervalDate);
        //Set default Value for end Date
        endDate.setText(dateFormat.format(currentDate));

        //Change date in beginDate EditText
        final DatePickerDialog.OnDateSetListener beginDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                beginCalendar.set(Calendar.YEAR,year);
                beginCalendar.set(Calendar.MONTH,month);
                beginCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                //Show date
                beginDate.setText(dateFormat.format(beginCalendar.getTime()));
            }
        };

        //Change date in endDate EditText
        final DatePickerDialog.OnDateSetListener endDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endCalendar.set(Calendar.YEAR,year);
                endCalendar.set(Calendar.MONTH,month);
                endCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                //Show date
                endDate.setText(dateFormat.format(endCalendar.getTime()));
            }
        };

        //Open DatePicker dialog when click on beginDate EditText
        beginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog beginDialog = new DatePickerDialog(getContext(),
                        beginDatePicker,
                        beginCalendar.get(Calendar.YEAR),
                        beginCalendar.get(Calendar.MONTH),
                        beginCalendar.get(Calendar.DAY_OF_MONTH));
                beginDialog.getDatePicker().setMaxDate(endCalendar.getTimeInMillis()); //Set max Date(End date)
                beginDialog.show();
            }
        });

        //Open DatePicker dialog when click on endDate EditText
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog endDialog = new DatePickerDialog(getContext(),
                        endDatePicker,
                        endCalendar.get(Calendar.YEAR),
                        endCalendar.get(Calendar.MONTH),
                        endCalendar.get(Calendar.DAY_OF_MONTH));
                endDialog.getDatePicker().setMaxDate(new Date().getTime()); //Set max Date (now)
                endDialog.show();
            }
        });
    }


    /************************ CONFIGURATION *******************/

    // Add name on each checkbox + tick default boxes
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

        this.addOnCheckedListener();

        //Set selected checkbox
        this.setCategoryFilters();
    }

    // Define OnCheckedChangeListener on all checkboxes
    private void addOnCheckedListener(){
        for (CheckBox box:checkBoxes) {
            box.setOnCheckedChangeListener(this);
        }
    }

    // Tick checkboxes
    private void setCategoryFilters(){
        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        Boolean check = sharedPref.getFromPreference("checkBox_" + tagTypeDialog,true);// default checked box
        Boolean check2 = sharedPref.getFromPreference("checkBox2_" + tagTypeDialog,false);
        Boolean check3 = sharedPref.getFromPreference("checkBox3_" + tagTypeDialog,false);
        Boolean check4 = sharedPref.getFromPreference("checkBox4_" + tagTypeDialog,false);
        Boolean check5 = sharedPref.getFromPreference("checkBox5_" + tagTypeDialog,false);
        Boolean check6 = sharedPref.getFromPreference("checkBox6_" + tagTypeDialog,false);

        //Apply
        this.checkBox.setChecked(check);
        this.checkBox2.setChecked(check2);
        this.checkBox3.setChecked(check3);
        this.checkBox4.setChecked(check4);
        this.checkBox5.setChecked(check5);
        this.checkBox6.setChecked(check6);
    }

    /************************ CHECK METHODS *******************/

    // Put Values From Dialog to Extra intent
    public void putExtraInputValues() {
        Intent intent = new Intent(getContext(), ResultActivity.class);

        intent.putExtra("EXTRA_TEXT", searchInput.getText().toString());
        intent.putStringArrayListExtra("EXTRA_BOXES", getCheckedBoxesValues());
        intent.putStringArrayListExtra("EXTRA_DATES", getDateValues());
        startActivity(intent);
    }


    // Return a list with checkboxes(checked) values
    private ArrayList<String> getCheckedBoxesValues(){
        ArrayList<String> result = new ArrayList<>();

        for (CheckBox box : this.checkBoxes) {
            if(box.isChecked()){
                result.add(box.getText().toString().toLowerCase());
            }
        }
        return result;
    }


    // Return How many boxes are check
    private int getCheckedBoxesCount(){
        int count = 0;

        for (CheckBox box : this.checkBoxes) {
            if(box.isChecked()){
                count ++;
            }
        }
        return count;
    }


    // Check if filters search are good
    private Boolean isValidSearchFilter(){

        if(this.searchInput.getText() == null || this.searchInput.getText().toString().isEmpty()) {
            this.errorMessage = "Please, enter query term";
            return false;
        }else if(this.getCheckedBoxesCount() == 0) {
            this.errorMessage = "At least one of box should have picked";
            return false;
        }else if(beginCalendar.getTimeInMillis() > endCalendar.getTimeInMillis()){
            this.errorMessage = "The end date can't be superior to begin date";
            return false;
        }else{
            return true;
        }
    }

    // Display error message
    private void displayErrorMessage(){
        //FIXME: Add Material design reco
        AlertDialog.Builder  dialog = new AlertDialog.Builder(getActivity());

        dialog.setMessage(errorMessage).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        dialog.create().show();
    }

    // Return date value in each EditText(Date)
    private ArrayList<String> getDateValues(){
        ArrayList<String> dateValues = new ArrayList<>();
        String myFormat ="yyyyMMdd";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        dateValues.add(dateFormat.format(beginCalendar.getTime()));
        dateValues.add(dateFormat.format(endCalendar.getTime()));

        return dateValues;
    }


    private void addCheckedChangeSwitchListener(){
        final NotificationJobService jobService = new NotificationJobService(getContext());

        this.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    if (searchInput.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Please enter a keyword", Toast.LENGTH_SHORT).show();
                        switchBtn.setChecked(false);
                    }else{
                        jobService.createJob(searchInput.getText().toString(), getCheckedBoxesValues());
                    }

                }else {
                    jobService.cancelJob();
                }
                saveSearchInput();
                saveEnableNotification();
            }
        });
    }

    /************************ SHAREDPREFERENCE METHODS *******************/
    private void configureSearchInput(){
        this.searchInput.setText(sharedPref.getFromPreference("SEARCH_INPUT_" + tagTypeDialog,""));
    }

    private void configureSwitchButton(){
        this.switchBtn.setChecked(sharedPref.getFromPreference("NOTIFICATION_BUTTON",false));
    }

    private void saveCheckBoxes(final boolean isChecked, String key) {
        this.sharedPref.saveInPreference(key,isChecked);
    }

    public void saveSearchInput() {
        this.sharedPref.saveInPreference("SEARCH_INPUT_" + tagTypeDialog, searchInput.getText().toString());
    }

    private void saveEnableNotification() {
        this.sharedPref.saveInPreference("NOTIFICATION_BUTTON", this.switchBtn.isChecked());
    }

    // Save checkBoxes status in Shared Preferences on checked change
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){
            case(R.id.checkBox):
                if(isChecked) this.saveCheckBoxes(true, "checkBox_" + tagTypeDialog);
                else this.saveCheckBoxes(false, "checkBox_" + tagTypeDialog);
                break;
            case(R.id.checkBox2):
                if(isChecked) this.saveCheckBoxes(true, "checkBox2_" + tagTypeDialog);
                else this.saveCheckBoxes(false, "checkBox2_" + tagTypeDialog);
                break;
            case(R.id.checkBox3):
                if(isChecked) this.saveCheckBoxes(true, "checkBox3_" + tagTypeDialog);
                else this.saveCheckBoxes(false, "checkBox3_" + tagTypeDialog);
                break;
            case(R.id.checkBox4):
                if(isChecked) this.saveCheckBoxes(true, "checkBox4_" + tagTypeDialog);
                else this.saveCheckBoxes(false, "checkBox4_" + tagTypeDialog);
                break;
            case(R.id.checkBox5):
                if(isChecked) this.saveCheckBoxes(true, "checkBox5_" + tagTypeDialog);
                else this.saveCheckBoxes(false, "checkBox5_" + tagTypeDialog);
                break;
            case(R.id.checkBox6):
                if(isChecked) this.saveCheckBoxes(true, "checkBox6_" + tagTypeDialog);
                else this.saveCheckBoxes(false, "checkBox6_" + tagTypeDialog);
                break;
        }
    }

    // Override jobService with lasted data
    @Override
    public void onStop() {
        super.onStop();

        this.saveSearchInput();

        NotificationJobService jobService = new NotificationJobService(getContext());
        if(switchBtn.isChecked()) jobService.createJob(searchInput.getText().toString(), getCheckedBoxesValues());

    }
}
