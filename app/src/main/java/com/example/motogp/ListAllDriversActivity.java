package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAllDriversActivity extends AppCompatActivity {

    // arrays to store data from API
    ArrayList<String> driverFirstNames = new ArrayList<>();
    ArrayList<String> driverLastNames = new ArrayList<>();
    ArrayList<String> driverCountries = new ArrayList<>();
    ArrayList<String> driverPicURLs = new ArrayList<>();

    private ListView listOfDrivers;
    private TextView lDriverFirstName, lDriverLastName, lCountry;
    ImageView imgDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_drivers);

        listOfDrivers = findViewById(R.id.listOfDrivers);

        // to fetch data from API and save to arrays
        new ListAllDriversActivity.NetworkRequestTask().execute();

        // list views
        ArrayAdapter adapterFirstNames = new ArrayAdapter<String>(this, R.layout.listview_drivers_layout, R.id.txtFirstName, driverFirstNames);
        listOfDrivers.setAdapter(adapterFirstNames);

//        ArrayAdapter adapterLastNames = new ArrayAdapter<String>(this, R.layout.listview_drivers_layout, R.id.txtLastName, driverLastNames);
//        listOfDrivers.setAdapter(adapterLastNames);
//
//        ArrayAdapter adapterCountry = new ArrayAdapter<String>(this, R.layout.listview_drivers_layout, R.id.txtCountry, driverCountries);
//        listOfDrivers.setAdapter(adapterCountry);
//
//        ArrayAdapter adapterDriverPicURLs = new ArrayAdapter<String>(this, R.layout.listview_drivers_layout, R.id.imgDriver, driverPicURLs);
//        listOfDrivers.setAdapter(adapterDriverPicURLs);
//
//        for(int i=0; i<listOfDrivers.getCount();i++){
//            View item = listOfDrivers.getChildAt(i);
//
//            lDriverFirstName = item.findViewById(R.id.txtFirstName);
//            lDriverLastName = item.findViewById(R.id.txtLastName);
//            lCountry = item.findViewById(R.id.txtCountry);
//            imgDriver = item.findViewById(R.id.imgDriver);

//        }
//
//        for(int i=0; i< driverPicURLs.size(); i++){
//            Picasso.get().load((driverPicURLs[i])).into(imageDriver);
//        }

    }

    private class NetworkRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();
                Response response = client.prepare("GET", "https://motogp2.p.rapidapi.com/get_all_riders_of_season?category_id=737ab122-76e1-4081-bedb-334caaa18c70&season_year=2023")
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
                        driverFirstNames.add(jsonObject.getString("name"));
                        driverLastNames.add(jsonObject.getString("surname"));
                        driverCountries.add(jsonObject.getJSONObject("country").getString("name"));
                        driverPicURLs.add(jsonObject.getJSONObject("current_career_step").getJSONObject("pictures").getString("portrait"));


//                        driverDOB = jsonObject.getString("birth_date");
//                        driverTeamName = jsonObject.getJSONObject("current_career_step").getString("sponsored_team");
//                        driverHelmetURL = jsonObject.getJSONObject("current_career_step").getJSONObject("pictures").getJSONObject("helmet").getString("main");
//                        driverBikeURL = jsonObject.getJSONObject("current_career_step").getJSONObject("pictures").getJSONObject("bike").getString("main");

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