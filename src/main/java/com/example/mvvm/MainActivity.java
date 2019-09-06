package com.example.mvvm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.adapter.MainAdapter;
import com.example.mvvm.model.NicePlace;
import com.example.mvvm.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private TextView hello;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setContentView(R.layout.activity_main);

        mainActivityViewModel.init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        Log.d(TAG, "onCreate: ");
        setSupportActionBar(toolbar);

        initializeRecycler();
        context = this;
        mainActivityViewModel.getNicePlaceMutableLiveData().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                mainAdapter.notifyDataSetChanged();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpNewItem();
            }
        });
    }

    private void setUpNewItem() {
        mainActivityViewModel.setNicePlaceMutableLiveData(new NicePlace("Akure", "")).observe((LifecycleOwner) context, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                mainAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    private void initializeRecycler() {
        Log.d(TAG, "initializeRecycler: ");
        recyclerView = findViewById(R.id.recycler);
        mainAdapter = new MainAdapter(this, mainActivityViewModel.getNicePlaceMutableLiveData().getValue());
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
