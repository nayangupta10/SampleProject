package com.nayan.sampleproject.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nayan.sampleproject.R;
import com.nayan.sampleproject.Util.Utils;
import com.nayan.sampleproject.adapter.ListAdapter;
import com.nayan.sampleproject.database.SQLiteDatabaseHandler;
import com.nayan.sampleproject.model.Doc;
import com.nayan.sampleproject.model.Example;
import com.nayan.sampleproject.retrofit.ApiRequest;
import com.nayan.sampleproject.retrofit.RetrofitRequest;
import com.nayan.sampleproject.viewmodel.DocViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiRequest apiRequest;
    List<Doc> docArrayList = new ArrayList<>();
    DocViewModel docViewModel;
    ProgressBar progressBar;
    public static ListAdapter listAdapter;
    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLiteDatabaseHandler(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        listAdapter = new ListAdapter(this, (ArrayList<Doc>) docArrayList);
        recyclerView.setAdapter(listAdapter);

        docViewModel = ViewModelProviders.of(this).get(DocViewModel.class);

        getDoc();
    }

    private void getDoc() {


        if (Utils.isInternetConnected(this)) {
            docViewModel.getExampleLiveData().observe(this, docResponse -> {
                if (docResponse != null) {

                    progressBar.setVisibility(View.GONE);
                    List<Doc> articles = docResponse.getResponse().getDocs();
                    docArrayList.addAll(articles);
                    listAdapter.notifyDataSetChanged();
                    //db
                    if (docArrayList != null)
                        db.addDoc(docArrayList);
                }
            });

        } else {
             progressBar.setVisibility(View.GONE);

              db.getAllDocs();
              Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }

    }
}
