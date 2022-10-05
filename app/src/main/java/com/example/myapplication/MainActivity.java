package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public int activePlayer = 0;
    public static int counter = 1;
    private int choice = R.drawable.oo;

    private TextView status;


    //    ImageView[][] winPositions = {
//            {tlImage, tmImage, trImage}, {mlImage, mmImage, mrImage}, {blImage, bmImage, brImage},
//            {tlImage, mlImage, blImage}, {tmImage, mmImage, bmImage}, {trImage, mrImage, brImage},
//            {tlImage, mmImage, brImage}, {trImage, mmImage, blImage}};
//    ArrayList<ArrayList<Integer>> winPositions = new ArrayList<>();

    int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private HashMap<String, String> squares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        squares = new HashMap<>();
        status = findViewById(R.id.status);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        status.setClickable(false);

    }

    public void onClick(View view) {
        ImageView img = (ImageView) view;
        if (img.isClickable()) {
            choice = choice == R.drawable.xx ? R.drawable.oo : R.drawable.xx;
            img.setImageResource(choice);
            img.setClickable(false);
            squares.put(img.getTag().toString(), choice == R.drawable.xx ? "x" : "o");
            status.setText((choice == R.drawable.xx ? "O's " : "X's ") + "Turn");

            if (squares.keySet().size() > 5)
                for (int[] win : winPositions
                ) {
                    String first = win[0] + "";
                    String second = win[1] + "";
                    String third = win[2] + "";
                    if (squares.getOrDefault(first, "a") == squares.getOrDefault(second, "b") && squares.getOrDefault(first, "a") == squares.getOrDefault(third, "c")) {
                        status.setText(squares.get(first).toUpperCase() + "'s Win");
                        status.setTextSize(60);
                        status.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                        status.setClickable(true);
                        break;
                    }

                }
            if (squares.keySet().size() == 8)
                status.setClickable(true);
            Log.d("Squares", "onClick: " + squares.toString());
        }
    }
}


