package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ListAllDriversActivity extends AppCompatActivity {

    private static final String BASE_URL = "rapidapi.com";
    private ListView lstDrivers;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_drivers);


        // Initialize the ListView and the Adapter
        lstDrivers = findViewById(R.id.listOfDrivers);
        adapter = new CustomAdapter(this, new ArrayList<Driver>());

        // Set the Adapter to the ListView
        lstDrivers.setAdapter(adapter);

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // Create an instance of the API interface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Make a GET request to get 10 posts
        Call<ArrayList<Driver>> call = apiInterface.getDrivers(10);

        // Enqueue the call and handle the response
        call.enqueue(new Callback<ArrayList<Driver>>() {
            @Override
            public void onResponse(Call<ArrayList<Driver>> call, Response<ArrayList<Driver>> response) {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // Get the list of
                    // posts from the response body
                    ArrayList<Driver> posts = response.body();

                    // Clear the adapter and add the posts to it
                    adapter.clear();
                    adapter.addAll();

                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Driver>> call, Throwable t) {
                // Handle the failure
                t.printStackTrace();
            }
        });
    }
}