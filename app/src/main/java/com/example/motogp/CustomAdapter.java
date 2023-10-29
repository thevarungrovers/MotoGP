package com.example.motogp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import retrofit2.http.POST;

public class CustomAdapter extends ArrayAdapter<Post> {

    // Define a constructor that takes a context and a list of posts as parameters
    public CustomAdapter(Context context, ArrayList<Driver> drivers){
        super(context,0,drivers);
    }

    // Override the getView method to return a custom view for each item in the list


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // Get the post object at the given position
        Post post = getItem(position);

        // Check if the existing view is being reused, otherwise inflate a new view from the layout file
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_all_drivers, parent, false);
        }


        // Get the views from the layout file and set their values with the data from the post object
        TextView userIdTextView = convertView.findViewById(R.id.user_id_text_view);
        TextView idTextView = convertView.findViewById(R.id.id_text_view);
        TextView titleTextView = convertView.findViewById(R.id.title_text_view);
        TextView bodyTextView = convertView.findViewById(R.id.body_text_view);

        userIdTextView.setText("User ID: " + post.getUserId());
        idTextView.setText("ID: " + post.getId());
        titleTextView.setText("Title: " + post.getTitle());
        bodyTextView.setText("Body: " + post.getBody());

        // Return the completed view to render on screen
        return convertView;

    }
}
