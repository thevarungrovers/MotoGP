package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchDriverActivity extends AppCompatActivity {

    String firstname;
    String lastname;
    Button btnSearch;
    EditText textFirstName, textLastName;
    String driverFirstName, driverLastname, driverDOB, driverCountry;
    String driverTeamName, driverPicURL, driverHelmetURL, driverBikeURL;


//    dummy array | variable name must remain same
//    name + surname
    String[] arrDriverNames = {"Bob", "Mark"};
    String[] arrDriverIds = {"a1234", "v4343"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_driver);

        btnSearch = findViewById(R.id.btnSearch);
        textFirstName = findViewById(R.id.txtFirstName);
        textLastName = findViewById(R.id.txtLastName);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = textFirstName.getText().toString().trim();
                lastname = textLastName.getText().toString().trim();
                new NetworkRequestTask().execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SearchDriverActivity.this, DriverInformationActivity.class);
                        intent.putExtra("driverFirstName",driverFirstName);
                        intent.putExtra("driverLastName", driverLastname);
                        intent.putExtra("driverDOB", driverDOB);
                        intent.putExtra("driverCountry", driverCountry);
                        intent.putExtra("driverTeamName", driverTeamName);
                        intent.putExtra("driverPicURL", driverPicURL);
                        intent.putExtra("driverHelmetURL", driverHelmetURL);
                        intent.putExtra("driverBikeURL", driverBikeURL);
                        startActivity(intent);
                    }
                }, 5000);
            }
        });
    }
    private class NetworkRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();
                Response response = client.prepare("GET", "https://motogp2.p.rapidapi.com/get_rider_info_by_name?first_name="+firstname+"&last_name=" +lastname)
                        .setHeader("X-RapidAPI-Key", "1ca232a069msha095878536987e5p155464jsn3b3999182940")
                        .setHeader("X-RapidAPI-Host", "motogp2.p.rapidapi.com")
                        .execute()
                        .get();

                if (response.getStatusCode() == 200) {
                    return response.getResponseBody();
                } else {
                    return "API returned an error: " + response.getStatusCode();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String jsonArrayResponse) {
            if (jsonArrayResponse != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonArrayResponse);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        driverFirstName = jsonObject.getString("name");
                        driverLastname = jsonObject.getString("surname");
                        driverDOB = jsonObject.getString("birth_date");
                        driverCountry = jsonObject.getJSONObject("country").getString("name");
                        driverTeamName = jsonObject.getJSONObject("current_career_step").getString("sponsored_team");
                        driverPicURL = jsonObject.getJSONObject("current_career_step").getJSONObject("pictures").getString("portrait");
                        driverHelmetURL = jsonObject.getJSONObject("current_career_step").getJSONObject("pictures").getJSONObject("helmet").getString("main");
                        driverBikeURL = jsonObject.getJSONObject("current_career_step").getJSONObject("pictures").getJSONObject("bike").getString("main");

                        System.out.println("Entry " + (i + 1));
                        System.out.println("Name: " + driverFirstName + " " + driverLastname);
                        System.out.println("Birth Date: " + driverDOB);
                        System.out.println("Country: " + driverCountry);
                        System.out.println("Team Name: "+ driverTeamName);
                        System.out.println("PIC URL: " + driverPicURL);
                        System.out.println("Helmet URL: " + driverHelmetURL);
                        System.out.println("Bike URL: " + driverBikeURL);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case where the data is not available
            }
        }
    }
}