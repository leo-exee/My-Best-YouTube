package com.example.mybestyoutube;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mybestyoutube.database.YoutubeVideoDatabase;
import com.example.mybestyoutube.pojos.YoutubeVideo;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private Context context;
    private TextView Title;
    private TextView Category;
    private TextView Description;
    private Button StartVideo;
    private YoutubeVideo youtubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        context = getApplicationContext();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Long id = (Long)intent.getSerializableExtra(MainActivity.KEY_INDEX);

        youtubeVideo = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().find(id);

        Title = findViewById(R.id.title);
        Category = findViewById(R.id.category);
        Description = findViewById(R.id.description);

        Title.setText(youtubeVideo.Title);
        Category.setText(youtubeVideo.Category);
        Description.setText(youtubeVideo.Description);

        StartVideo = findViewById(R.id.start_video);

        StartVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String youtubeUrl = youtubeVideo.Url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                intent.setPackage("com.google.android.youtube");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                    startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
