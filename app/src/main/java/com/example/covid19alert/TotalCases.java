package com.example.covid19alert;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.jar.JarException;

public class TotalCases extends AppCompatActivity {
    private static final String url = "https://api.apify.com/v2/key-value-stores/tVaYRsPHLjNdNBu7S/records/LATEST?disableRedirect=true";
    private RequestQueue mQueue;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_cases);
        mQueue = Volley.newRequestQueue(TotalCases.this);
        TextView data = findViewById(R.id.data);
        //to bring in the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // to put in color to the action bar
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#DC143C"));
        actionBar.setBackgroundDrawable(colorDrawable);
        // to change the color of the status bar ie the top most part of the android page
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));



        final ArrayList<CasesNumber> casesnumber = new ArrayList<>();

        JsonArrayRequest casesReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject obj = response.getJSONObject(i);
                        CasesNumber casesNumber = new CasesNumber();
                        casesNumber.setmCountry(obj.getString("country"));
                        casesNumber.setmActivecases(obj.getString("infected"));
                        casesNumber.setmDeath(obj.getString("deceased"));
                        casesNumber.setmRecovered(obj.getString("recovered"));

                        casesnumber.add(casesNumber);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    ListView casesListview = findViewById(R.id.listView);
                    CasesNumberAdapter adapter = new CasesNumberAdapter(getApplicationContext(), casesnumber);
                    casesListview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(casesReq);



    }
}
