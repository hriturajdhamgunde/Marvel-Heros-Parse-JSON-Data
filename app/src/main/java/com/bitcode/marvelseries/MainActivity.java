package com.bitcode.marvelseries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private MarvelSeriesAdapter mMarvelSeriesAdapter;
    private ArrayList<MarvelSeriesData> mMarvelSeriesDetails;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mRecyclerView=findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager (this));

        mMarvelSeriesDetails=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON() {
        String url="https://simplifiedcoding.net/demos/marvel/";

        Log.e("TAG","JSONParsing");
        JsonArrayRequest request=new JsonArrayRequest (Request.Method.GET,
                url,null, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("Tag","Got Response"+response);

                try {

                  //  JSONArray jsonArray=response.getJSONArray(Integer.parseInt ("JSON"));

                    for(int i=0;i<response.length();i++){

                        JSONObject jsonObj=response.getJSONObject(i);

                        String imageUrl=jsonObj.getString("imageurl");
                        String name=jsonObj.getString("name");
                        String firstappearance=jsonObj.getString("firstappearance");
                        String team=jsonObj.getString("team");
                        String createdby=jsonObj.getString("createdby");
                        String publisher=jsonObj.getString("publisher");
                        String realname=jsonObj.getString("realname");
                        String bio=jsonObj.getString("bio");



                        mMarvelSeriesDetails.add(new MarvelSeriesData (name,realname,team,firstappearance,createdby,publisher,imageUrl,bio));
                    }

                    mMarvelSeriesAdapter=new MarvelSeriesAdapter (MainActivity.this,mMarvelSeriesDetails);

                    mRecyclerView.setAdapter(mMarvelSeriesAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
    }

