package com.example.tictactoe;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, restart;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int flag = 0; // 0 for X, 1 for O
    int count = 0;
    String lastPlayer = ""; // Track the last player
    Handler handler = new Handler(); // Handler to handle the delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        restart = findViewById(R.id.restart);

        btn1.setOnClickListener(this::Check);
        btn2.setOnClickListener(this::Check);
        btn3.setOnClickListener(this::Check);
        btn4.setOnClickListener(this::Check);
        btn5.setOnClickListener(this::Check);
        btn6.setOnClickListener(this::Check);
        btn7.setOnClickListener(this::Check);
        btn8.setOnClickListener(this::Check);
        btn9.setOnClickListener(this::Check);
    }

    public void Check(View view) {
        Button btnCurrent = (Button) view;
        if (btnCurrent.getText().toString().equals("")) {

            count++;
            String currentPlayer;

            // Determine the current player based on the flag
            if (flag == 0) {
                currentPlayer = "X";
                flag = 1;
            } else {
                currentPlayer = "O";
                flag = 0;
            }

            btnCurrent.setText(currentPlayer);
            lastPlayer = currentPlayer; // Update the last player to current player

            if (count > 4) {
                // Get current button texts
                b1 = btn1.getText().toString();
                b2 = btn2.getText().toString();
                b3 = btn3.getText().toString();
                b4 = btn4.getText().toString();
                b5 = btn5.getText().toString();
                b6 = btn6.getText().toString();
                b7 = btn7.getText().toString();
                b8 = btn8.getText().toString();
                b9 = btn9.getText().toString();

                // Check for winning conditions
                if ((b1.equals(b2) && b2.equals(b3) && !b1.equals("")) ||
                        (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) ||
                        (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) ||
                        (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) ||
                        (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) ||
                        (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) ||
                        (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) ||
                        (b3.equals(b5) && b5.equals(b7) && !b3.equals(""))) {

                    Toast.makeText(this, "Winner is: " + lastPlayer, Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this::newGame, 3000); // Delay before starting a new game
                } else if (count == 9) {
                    Toast.makeText(this, "Game is Drawn", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this::newGame, 3000); // Delay before starting a new game
                }
            }
        }
    }

    public void newGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        count = 0;
        flag = 0; // Reset flag to start with "X"
        lastPlayer = ""; // Reset the last player
    }
}
