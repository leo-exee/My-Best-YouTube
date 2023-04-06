package com.example.mybestyoutube;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybestyoutube.database.YoutubeVideoDatabase;
import com.example.mybestyoutube.pojos.YoutubeVideo;
import com.example.mybestyoutube.pojos.YoutubeVideoAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.URIResolver;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_INDEX = "key_index";
    public Context context;
    public RecyclerView YoutubeVideoList;

    @Override
    protected void onStart() {
        super.onStart();
        YoutubeVideoAsyncTask youtubeVideoAsyncTasks = new YoutubeVideoAsyncTask();
        youtubeVideoAsyncTasks.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        YoutubeVideoList = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        YoutubeVideoList.setHasFixedSize(true);
        YoutubeVideoList.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addVideo:
                Context context = getApplicationContext();
                Intent intent = new Intent(context, AddVideoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
    }

    public class YoutubeVideoAsyncTask extends AsyncTask<Nullable, Nullable, List<YoutubeVideo>> {
        @Override
        protected List<YoutubeVideo> doInBackground(Nullable... nullables) {
            List<YoutubeVideo> youtubeVideoList = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().list();

            return youtubeVideoList;
        }
        @Override
        protected void onPostExecute(List<YoutubeVideo> youtubeVideoList) {
            YoutubeVideoAdapter youtubeVideoAdapter = new YoutubeVideoAdapter(youtubeVideoList, new YoutubeVideoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(YoutubeVideo item) {
                    Context context = getApplicationContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(KEY_INDEX, item.getId());
                    startActivity(intent);
                }
            });
            YoutubeVideoList.setAdapter(youtubeVideoAdapter);
        }
    }
}
