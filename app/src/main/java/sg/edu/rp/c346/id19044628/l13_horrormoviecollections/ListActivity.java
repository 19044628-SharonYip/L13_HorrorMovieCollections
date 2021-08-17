package sg.edu.rp.c346.id19044628.l13_horrormoviecollections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Movie> movieList;
    Button btn5Stars;
    CustomAdapter caMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setTitle(getResources().getText(R.string.title_activity_list));

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        movieList = dbh.getAllMovie();
        dbh.close();

        caMovie=new CustomAdapter(this, R.layout.row, movieList);
        lv.setAdapter(caMovie);

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                movieList.clear();
                movieList.addAll(dbh.getAllMovieByStars(5));
                caMovie.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ListActivity.this, EditActivity.class);
                i.putExtra("movie", movieList.get(position));
                startActivity(i);

            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        movieList.clear();
        movieList.addAll(dbh.getAllMovie());
        caMovie.notifyDataSetChanged();
    }
}