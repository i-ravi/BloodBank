package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;

import com.example.bloodbank.Adapters.RequestAdapter;
import com.example.bloodbank.Adapters.SearchAdapter;
import com.example.bloodbank.DataModels.Donors;
import com.example.bloodbank.DataModels.RequestDataModel;
import com.example.bloodbank.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
    List<Donors> donorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        donorsList=new ArrayList<>();
        String json;
        String city,blood_group;
        Intent intent=getIntent();
        json=intent.getStringExtra("json");
        city=intent.getStringExtra("city");
        blood_group=intent.getStringExtra("blood_group");
        TextView heading=findViewById(R.id.heading);
        String st="Donors in "+city+" with blood group "+blood_group;
        heading.setText(st);
        Gson gson=new Gson();
        Type type=new TypeToken<List<Donors>>(){}.getType();
        List<Donors> dataModels=gson.fromJson(json,type);
        if (dataModels!=null && dataModels.isEmpty()){
            heading.setText("No results");
        }
        else if(dataModels!=null){
        donorsList.addAll(dataModels);}

        RecyclerView recyclerView=findViewById(R.id.recycler_view1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        SearchAdapter adapter=new SearchAdapter(donorsList,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
