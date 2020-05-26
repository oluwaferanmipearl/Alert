package com.example.covid19alert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    //CREATE A CONSTANT FOR THE STARTACTIVITY RESULT
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";


    private TextView textViewHighscore;
    private TextView textViewpresentScore;
    private int highscore;
    private int presentScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHighscore = findViewById(R.id.text_highscore);
        loadHighscore();
        textViewpresentScore = findViewById(R.id.text_presentscore);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigation view


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        //to select the home screen
        navigationView.setCheckedItem(R.id.nav_game);


        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun) {
            CardView cardView = findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog();
                }

            });

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();

        } else {
            //implement the click on button of the card view
            CardView cardView = findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, QuizStart.class);
                    startActivityForResult(intent, REQUEST_CODE_QUIZ);

                }
            });


        }


    }

    public void openDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        TextView title = new TextView(this);
        title.setText("Are you ready?");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.RED);
        title.setTextSize(28);
        alertDialog.setCustomTitle(title);

        TextView msg = new TextView(this);
        msg.setText("You'll have 20 seconds\nto answer each questions.\n \n Scores: \n15-20 CovGenius.\n10-14 Average.\n5-9 Study more.\n0-4 CovIdiot.");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        msg.setTextSize(23);
        alertDialog.setView(msg);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "PLAY", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
                Intent intent = new Intent(MainActivity.this, QuizStart.class);
                startActivityForResult(intent, REQUEST_CODE_QUIZ);

            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();

        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        okBT.setGravity(Gravity.CENTER_HORIZONTAL);
        okBT.setTextSize(23);
        okBT.setTextColor(Color.RED);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizStart.EXTRA_SCORE, 0);
                textViewpresentScore.setText("Score: " + score);


                if (score > highscore) {
                    updateHighScore(score);
                }
            }
        }
    }


    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighScore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();

    }


    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_game:
                break;
            case R.id.nav_symptoms:
                Intent intent = new Intent(MainActivity.this, Symptoms.class);
                startActivity(intent);
                break;

            case R.id.nav_prevent:
                Intent intent1 = new Intent(MainActivity.this, prevention.class);
                startActivity(intent1);
                break;
            case R.id.nav_spread:
                Intent intent2 = new Intent(MainActivity.this, Spreads.class);
                startActivity(intent2);
                break;
            case R.id.nav_cases:
                Intent intent3 = new Intent(MainActivity.this, TotalCases.class);
                startActivity(intent3);
                break;
            case R.id.nav_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "I'm using the Covid19Alert app to get more information about the deadly virus" +
                        ", I thought you might like it too." +
                        " See it here: https://github.com/oluwaferanmipearl/Alert";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

                break;
            case R.id.nav_send:

                Intent emailintent = new Intent(Intent.ACTION_SENDTO);
                emailintent.setData(Uri.parse("mailto:"));
                emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pearlferanmi@gmail.com"});
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Covid19Alert App Feedback");
                if (emailintent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailintent);
                }
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
