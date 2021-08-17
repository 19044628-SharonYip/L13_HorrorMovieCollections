package sg.edu.rp.c346.id19044628.l13_horrormoviecollections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvname = rowView.findViewById(R.id.textViewName);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        TextView tvDesc = rowView.findViewById(R.id.textViewDesc);
        //TextView tvPGrate = rowView.findViewById(R.id.textViewPG);
        //TextView tvStar = rowView.findViewById(R.id.textViewStar);
        RatingBar rbStar=rowView.findViewById(R.id.ratingBar);
        ImageView imgRate= rowView.findViewById(R.id.imgRate);
        ImageView imgNew= rowView.findViewById(R.id.imgNew);

        // Obtain the Android Version information based on the position
        Movie currentMovie = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvname.setText(currentMovie.getName());
        tvYear.setText(" - " + currentMovie.getYear());
        tvDesc.setText(currentMovie.getDescription());
        //tvPGrate.setText(String.valueOf("PG Rate: "+ currentMovie.getPgRate()));
        //tvStar.setText(currentSong.toString());
        rbStar.setRating(currentMovie.getStars());

        if (currentMovie.getYear()>=2020)
        {
            imgNew.setImageResource(R.drawable.newmovie);
        }
        else {
            imgNew.setVisibility(View.GONE);
        }

        if (currentMovie.getPgRate()>13)
        {
            imgRate.setImageResource(R.drawable.restricted);
        }
        else
        {
            imgRate.setImageResource(R.drawable.pg13);
        }


        return rowView;
    }
}
