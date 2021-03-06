package com.example.aalloul.packets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchOffer extends AppCompatActivity implements DatePickerFragment.TheListener{
    // Logging
    private final static String LOG_TAG = SearchOffer.class.getSimpleName();

    // Button initialization
    private EditText srcCity;
    private EditText srcCountry;
    private EditText destCity;
    private EditText destCountry;
    private static String sourceCity = "sourceCity";
    private static String sourceCountry = "sourceCountry";

    private FloatingActionButton confirm, cancel;
    private Button date_picker_button;
    private String sendingDate= "Choose a date";
    private String dateForSearch;

    // Communication from MainActivity
    private Intent fromMainActivityIntent;

    // Communication to MainActivity (canceling search)
    private Intent toMainActivityIntent;
    public final static String SEARCH_SOURCE_CITY_EXTRA =
            "com.example.aalloul.packets.SearchOffer.SEARCHSOURCECITY";
    public final static String SEARCH_SOURCE_COUNTRY_EXTRA =
            "com.example.aalloul.packets.SearchOffer.SEARCHSOURCECOUNTRY";
    public final static String SEARCH_DESTINATION_CITY_EXTRA =
            "com.example.aalloul.packets.SearchOffer.SEARCHDESTINATIONCITY";
    public final static String SEARCH_DESTINATION_COUNTRY_EXTRA =
            "com.example.aalloul.packets.SearchOffer.SEARCHDESTINATIONCOUNTRY";
    public final static String SEARCH_PERFORM_SEARCH_ACTION =
            "com.example.aalloul.packets.SearchOffer.PERFORMSEARCHACTION";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate - start");
        setContentView(R.layout.activity_search_offer);

        Log.i(LOG_TAG, "onCreate - Get the intent");
        fromMainActivityIntent = getIntent();
        sourceCity = fromMainActivityIntent.getStringExtra(MainActivity.SEARCH_SOURCE_CITY_EXTRA);
        sourceCountry = fromMainActivityIntent.getStringExtra(
                MainActivity.SEARCH_SOURCE_COUNTRY_EXTRA);

        Log.i(LOG_TAG, "onCreate - get the edit text");
        srcCity = (EditText) findViewById(R.id.sourceCity);
        srcCountry = (EditText) findViewById(R.id.sourceCountry);
        srcCity.setText(sourceCity);
        srcCountry.setText(sourceCountry);
        destCity = (EditText) findViewById(R.id.destCity);
        destCountry = (EditText) findViewById(R.id.destCountry);

        // Get the confirm button
        Log.i(LOG_TAG, "onCreate - Getting the confim button");
        confirm = (FloatingActionButton) findViewById(R.id.confirmSearch);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmSearch();
            }
        });


        // Get the cancel button
        Log.i(LOG_TAG, "onCreate - Getting the cancel button");
        cancel = (FloatingActionButton) findViewById(R.id.cancelSearch);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelSearch();
            }
        });

        // Get the date picker button
        Log.i(LOG_TAG, "onCreate - Getting the date picker");
        date_picker_button = (Button) findViewById(R.id.date_picker);
        date_picker_button.setText(sendingDate);

        Log.i(LOG_TAG, "onCreateView - exit");
    }

    public void showDatePickerDialog(View v) {
        Log.i(LOG_TAG, "showDatePickerDialog - start");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        Log.i(LOG_TAG, "showDatePickerDialog - end");
    }
    private void onConfirmSearch() {
        Log.i(LOG_TAG, "onConfirmSearch - start");
        Log.i(LOG_TAG, "onConfirmSearch - srcCity "+srcCity.getText()+"- srcCountry = "+
                srcCountry.getText());
        Log.i(LOG_TAG, "onConfirmSearch - destCity "+destCity.getText()+"- destCountry = "+
                destCountry.getText());
        Log.i(LOG_TAG, "onConfirmSearch - thedate = "+dateForSearch);

        toMainActivityIntent = new Intent(this, MainActivity.class);
        toMainActivityIntent.putExtra(SEARCH_SOURCE_CITY_EXTRA, srcCity.getText());
        toMainActivityIntent.putExtra(SEARCH_SOURCE_COUNTRY_EXTRA, srcCountry.getText());
        toMainActivityIntent.putExtra(SEARCH_DESTINATION_CITY_EXTRA, destCity.getText());
        toMainActivityIntent.putExtra(SEARCH_DESTINATION_COUNTRY_EXTRA, destCity.getText());
        toMainActivityIntent.putExtra(SEARCH_PERFORM_SEARCH_ACTION, true);
        startActivity(toMainActivityIntent);
        Log.i(LOG_TAG, "onConfirmSearch - End");

    }

    private void onCancelSearch() {
        Log.i(LOG_TAG, "onCancelSearch - start");
        toMainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(toMainActivityIntent);
        Log.i(LOG_TAG, "onCancelSearch - End");

    }


    @Override
    public void returnDate(String date) {
        Log.i(LOG_TAG, "returnDate - start");
        dateForSearch = date;
        date_picker_button.setText(dateForSearch);
        Log.i(LOG_TAG, "returnDate - dateForSearch = " + dateForSearch);
        Log.i(LOG_TAG, "returnDate - End");

    }
}
