package com.vibrantdesigners.findyourhero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String heroName = getIntent().getStringExtra("heroName");
        int heroDescId = getIntent().getIntExtra("heroDescId", 0);
        int heroImg = getIntent().getIntExtra("heroImg", 0);
        String dateOfBirth = getIntent().getStringExtra("dateOfBirth");

        TextView heroNameLabel = findViewById(R.id.heroNameLabel);
        TextView dateOfBirthTextView = findViewById(R.id.dateLabel);
        TextView heroDesc = findViewById(R.id.heroDesc);
        ImageView heroImage = findViewById(R.id.heroImg);
        heroDesc.setMovementMethod(new ScrollingMovementMethod());

        heroImage.setImageResource(heroImg);
        heroNameLabel.setText(heroName);
        heroDesc.setText(heroDescId);
        dateOfBirthTextView.setText(dateOfBirth);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
