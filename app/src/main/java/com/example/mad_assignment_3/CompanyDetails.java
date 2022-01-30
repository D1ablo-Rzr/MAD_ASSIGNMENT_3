package com.example.mad_assignment_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class CompanyDetails extends Fragment {
    TextView textView,Name;
    double longitude, latitude;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View V;
        V=inflater.inflate(R.layout.activity_main2,container,false);
        Name =V.findViewById(R.id.companyDetails);
        textView = V.findViewById(R.id.textView);
        Button button=V.findViewById(R.id.button2);
        ImageView Disp = V.findViewById(R.id.Image);
        Bundle bundle = getActivity().getIntent().getExtras();
        String name = getArguments().getString("name");
        String location = getArguments().getString("location");
        String year = getArguments().getString("year");
        String address = getArguments().getString("address");
        longitude = getArguments().getDouble("longitude");
        latitude = getArguments().getDouble("latitude");
        String logo=getArguments().getString("logo");
        String base64Image=logo.split(",")[1];
        byte[] decodeimage= Base64.decode(base64Image,Base64.DEFAULT);
        Bitmap decodebyte = BitmapFactory.decodeByteArray(decodeimage,0, decodeimage.length);
        Disp.setImageBitmap(decodebyte);
        Name.setText(name);
        textView.setText("Company Name: " + name + "\n" + "Location: " + location + "\n" + "Description: " +
                year+"\n"+"Address"+address+"\n"+ "Geolocation: "+"\n"+longitude+" : "+latitude);
        button.setOnClickListener(v->toMaps());
        return V;
    }
    public void toMaps(){
        Intent intent = new Intent(getActivity().getApplicationContext(),MapsActivity.class);
        intent.putExtra("longitude",longitude);
        intent.putExtra("latitude",latitude);
        startActivity(intent);
    }

}