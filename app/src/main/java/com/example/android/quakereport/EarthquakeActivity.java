/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public ArrayList <EarthquakeData> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = findViewById(R.id.list);
        earthquakeListView.setOnItemClickListener(this);
        // Create a new {@link ArrayAdapter} of earthquakes
        data = QueryUtils.extractEarthquakes();
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this,data);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthquakeAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        EarthquakeData earthquakeUrl = data.get(i);
        Log.d("onItemCheck",earthquakeUrl.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(earthquakeUrl.getUrl()));
        startActivity(intent);

    }
}
