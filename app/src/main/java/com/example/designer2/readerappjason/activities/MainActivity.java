package com.example.designer2.readerappjason.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.designer2.readerappjason.R;
import com.example.designer2.readerappjason.adapter.RecyclerViewAdapter;
import com.example.designer2.readerappjason.adapter.HorizontalAdapter;
import com.example.designer2.readerappjason.model.Anime;
import com.example.designer2.readerappjason.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final String JASON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";

    private JsonArrayRequest request,verticalreq;
    private RequestQueue requestQueue;
    public List<Anime> firstAnime;
    private List<Category>firstcategorie;
    public RecyclerView recyclerView,verticalRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstAnime = new ArrayList<>();


       recyclerView = findViewById(R.id.recyclerview_id);
       jsonrequest();


        firstcategorie = new ArrayList<>();

       verticalRecyclerview = findViewById(R.id.horizontal_rec);

       verticaljsonrequest();
    }

    public void jsonrequest() {


        request = new JsonArrayRequest(JASON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;

                for (int i = 0; i < response.length();i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                      anime.setNb_episode(jsonObject.getString("episode"));
                      anime.setStudio(jsonObject.getString("studio"));
                      anime.setImage_url(jsonObject.getString("img"));
                      firstAnime.add(anime);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                setuprecyclerview(firstAnime);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);


    }

    private void verticaljsonrequest() {
        verticalreq = new JsonArrayRequest(JASON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;

                for (int i = 0; i < response.length();i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        Category category = new Category();
                        category.setName(jsonObject.getString("name"));
                        category.setImage_url(jsonObject.getString("img"));
                        firstcategorie.add(category);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                setupverticalrecyclerview(firstcategorie);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
        requestQueue.add(verticalreq);

    }

    private void setuprecyclerview(List<Anime> firstAnime) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,firstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);




    }
    private void setupverticalrecyclerview(List<Category> firstcategorie){
        HorizontalAdapter verticalAdapter = new HorizontalAdapter(this,firstcategorie);
        verticalRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        verticalRecyclerview.setAdapter(verticalAdapter);


    }

}
