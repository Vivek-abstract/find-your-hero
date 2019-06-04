package com.vibrantdesigners.findyourhero;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    int day = -1, month = -1, year = -1;
    String dateOfBirthString;
    Date dateOfBirth;

    int heroeDescriptions[] = new int[]{
            R.string.thanos,
            R.string.groot,
            R.string.thor,
            R.string.hulk,
            R.string.gamora,
            R.string.rocket,
            R.string.starlord,
            R.string.captainmarvel,
            R.string.spiderman,
            R.string.blackpanther,
            R.string.captainamerica,
            R.string.ironman,
            R.string.blackwidow,
            R.string.hawkeye,
            R.string.johnwick,
            R.string.drstrange
    };

    String heroNames[] = new String[]{
            "Thanos",
            "Groot",
            "Thor",
            "Hulk",
            "Gamora",
            "Rocket",
            "Starlord",
            "Captain Marvel",
            "Spiderman",
            "Black Panther",
            "Captain America",
            "Iron Man",
            "Black Widow",
            "Hawkeye",
            "John Wick",
            "Dr. Strange"
    };

    int heroImages[] = new int[]{
            R.drawable.thanos,
            R.drawable.groot,
            R.drawable.thor,
            R.drawable.hulk,
            R.drawable.gamora,
            R.drawable.rocket,
            R.drawable.starlord,
            R.drawable.captainmarvel,
            R.drawable.spiderman,
            R.drawable.blackpanther,
            R.drawable.captainamerica,
            R.drawable.ironman,
            R.drawable.blackwidow,
            R.drawable.hawkeye,
            R.drawable.johnwick,
            R.drawable.drstrange
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (day == -1 || month == -1 || year == -1) {
                    // TODO: Show invalid alert
                    Toast.makeText(getApplicationContext(), "Please select your birthdate", Toast.LENGTH_SHORT).show();
                } else if (dateOfBirth.after(new Date())) {
                    Toast.makeText(getApplicationContext(), "You can't be born in the future!", Toast.LENGTH_SHORT).show();
                } else {
                    String md5 = md5(day + "/" + month + "/" + year);
                    Log.d("Hello", md5);

                    int heroID = convertStringToIntHash(md5);
                    Log.d("Hello", "Hero ID: " + heroID);
                    String heroName = heroNames[heroID];
                    int heroDescId = heroeDescriptions[heroID];
                    int heroImg = heroImages[heroID];

                    Log.d("Hello", dateOfBirthString);

                    Intent intent = new Intent(MainActivity.this, HeroInfo.class);
                    intent.putExtra("heroName", heroName);
                    intent.putExtra("heroDescId", heroDescId);
                    intent.putExtra("heroImg", heroImg);
                    intent.putExtra("dateOfBirth", dateOfBirthString);

                    startActivity(intent);
                }
            }
        });
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

            Intent intent = new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Do something with the date chosen by the user
        Log.d("Hello", "year: " + year);
        Log.d("Hello", "month: " + month);
        Log.d("Hello", "day: " + day);

        this.day = day;
        this.month = month;
        this.year = year;

        TextView dateTextView = findViewById(R.id.dateTextView);

        dateOfBirth = new GregorianCalendar(year, month, day).getTime();
        dateOfBirthString = new SimpleDateFormat("dd/MM/yyyy").format(dateOfBirth);

        dateTextView.setText(dateOfBirthString);
        dateTextView.setVisibility(View.VISIBLE);

        Button selectDateButton = findViewById(R.id.datePickerButton);
        selectDateButton.setText("Change");

        TextView selectDateLabel = findViewById(R.id.selectBirthdayLabel);
        selectDateLabel.setText("You chose:");
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int convertStringToIntHash(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += (int) s.charAt(i);
        }

        ans = ans % heroNames.length;

        return ans;
    }
}
