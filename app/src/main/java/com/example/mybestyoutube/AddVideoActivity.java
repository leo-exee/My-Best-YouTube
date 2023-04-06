package com.example.mybestyoutube;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mybestyoutube.dao.YoutubeVideoDAO;
import com.example.mybestyoutube.database.YoutubeVideoDatabase;
import com.example.mybestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddVideoActivity extends AppCompatActivity {

    private Context context;
    private Button Add;
    private Button Cancel;

    private Spinner Category;
    private EditText Title;
    private EditText Url;
    private EditText Description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        context = getApplicationContext();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Category = findViewById(R.id.category);

        String[] category = new String[]{
                "Choose a category",
                "Music",
                "Movie & TV",
                "Video games",
                "Sport",
                "Culture",
                "News",
                "Fashion"
        };

        List<String> categoryList = new ArrayList<>
                (Arrays.asList(category));


        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categoryList
        ){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );
        Category.setAdapter(spinnerArrayAdapter);

        Title = findViewById(R.id.title);
        Description = findViewById(R.id.description);
        Url = findViewById(R.id.url);


        Add = findViewById(R.id.add);
        Cancel = findViewById(R.id.cancel);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Title.getText().toString();
                String description = Description.getText().toString();
                String url = Url.getText().toString();
                String category = Category.getSelectedItem().toString();

                if (title.isEmpty() || description.isEmpty() || url.isEmpty() || Category.getSelectedItemPosition() == 0) {
                    Toast.makeText(context, "Fill in the form correctly", Toast.LENGTH_SHORT).show();
                } else {
                    YoutubeVideo youtubeVideo = new YoutubeVideo();
                    youtubeVideo.setTitle(title);
                    youtubeVideo.setDescription(description);
                    youtubeVideo.setUrl(url);
                    youtubeVideo.setCategory(category);

                    YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().add(youtubeVideo);
                    Toast.makeText(context, "Video added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
