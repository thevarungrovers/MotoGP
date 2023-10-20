package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SearchDriverActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteSearch;
    Button btnSearch;



//    dummy array | variable name must remain same
//    name + surname
    String[] arrDriverNames = {"Bob", "Mark"};
    String[] arrDriverIds = {"a1234", "v4343"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_driver);

        autoCompleteSearch = findViewById(R.id.autoCompleteSearch);
        btnSearch = findViewById(R.id.btnSearch);

        // connecting adapter to search field
        ArrayAdapter<String> myAdapter = new ArrayAdapter(SearchDriverActivity.this, android.R.layout.select_dialog_item, arrDriverNames);
        autoCompleteSearch.setThreshold(1); // number of characters to start showing the suggestions
        autoCompleteSearch.setAdapter(myAdapter);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            int foundDriverIdIndex;
            @Override
            public void onClick(View view) {
                String searchName = autoCompleteSearch.getText().toString();
                for(int i=0; i<arrDriverNames.length; i++){
                    if(searchName.equals(arrDriverNames[i])){
                        foundDriverIdIndex = i;
                    }
                }


                /* code to open new page to display driver's info */
                Intent i = new Intent(SearchDriverActivity.this, DriverInformationActivity.class);
                i.putExtra("driverID", arrDriverIds[foundDriverIdIndex]);
                startActivity(i);
            }
        });

    }
}