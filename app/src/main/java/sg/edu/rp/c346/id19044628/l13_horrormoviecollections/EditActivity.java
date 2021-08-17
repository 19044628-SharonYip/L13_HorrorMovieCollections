package sg.edu.rp.c346.id19044628.l13_horrormoviecollections;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etID, etName, etYear,etPG, etDesc;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.edName);
        etYear = (EditText) findViewById(R.id.edYearrr);
        etDesc = (EditText) findViewById(R.id.etDEsc);
        etPG = (EditText) findViewById(R.id.etpg);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar2);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        etID.setText(currentMovie.getId()+"");
        etName.setText(currentMovie.getName());
        etYear.setText(currentMovie.getYear()+"");
        etDesc.setText(currentMovie.getDescription());
        etPG.setText(currentMovie.getPgRate()+"");
        ratingBar.setRating(currentMovie.getStars());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                currentMovie.setName(etName.getText().toString().trim());
                currentMovie.setDescription(etDesc.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentMovie.setYear(year);

                int pg = 0;
                try {
                    pg = Integer.valueOf(etPG.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditActivity.this, "Invalid rate", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentMovie.setPgRate(pg);

                currentMovie.setStars((int) ratingBar.getRating());

                int result = dbh.updateMovie(currentMovie);
                if (result>0){
                    Toast.makeText(EditActivity.this, "Movie updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);

                    dbh.close();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie \n "+ etName.getText().toString().trim());
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Cancel",null);
                myBuilder.setNeutralButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        int result = dbh.deleteMovie(currentMovie.getId());
                        if (result>0){
                            Toast.makeText(EditActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(EditActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Do not discard",null);
                myBuilder.setNeutralButton("discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }


}