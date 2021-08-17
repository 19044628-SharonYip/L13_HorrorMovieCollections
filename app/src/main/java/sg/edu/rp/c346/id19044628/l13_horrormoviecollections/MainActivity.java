package sg.edu.rp.c346.id19044628.l13_horrormoviecollections;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    Button btnShow, btnInsert;
    ImageView imgCover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getText(R.string.title_activity_main));


        imgCover = findViewById(R.id.imageView);
        btnShow = findViewById(R.id.btnShowAll);
        btnInsert = findViewById(R.id.btnInsert);



        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.insert, null);

                final EditText etName = viewDialog.findViewById(R.id.etName);
                final EditText etYear = viewDialog.findViewById(R.id.edYearrr);
                final EditText etDesc = viewDialog.findViewById(R.id.etDesc);
                final EditText etPGrate = viewDialog.findViewById(R.id.etPG);
                final RatingBar rbStar = viewDialog.findViewById(R.id.ratingBarStar);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Insert New Movie");
                myBuilder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = etName.getText().toString().trim();
                        String year_str = etYear.getText().toString().trim();
                        int year = Integer.valueOf(year_str);
                        String desc = etDesc.getText().toString().trim();
                        String pg_str = etPGrate.getText().toString().trim();
                        int pg = Integer.valueOf(pg_str);
                        int rating = (int) rbStar.getRating();



                        DBHelper dbh = new DBHelper(MainActivity.this);
                        dbh.insertMovie(name, year, desc, pg, rating);
                        dbh.close();
                    }
                });

                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }




}