package com.example.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Weapon playerWeapon;
    private Weapon computerWeapon;
    private int playerLives;
    private int computerLives;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        playerLives = 3;
        computerLives = 3;


    }

    public void rockButtonClicked(View v) {
        playerWeapon = Weapon.ROCK;
        computerWeapon = getComputerWeapon();

        int result = determineWinner(playerWeapon, computerWeapon);

        if(playerLives > 0 && computerLives > 0) {
            updateBoard(result);
        }
    }

    public void paperButtonClicked(View v) {
        playerWeapon = Weapon.PAPER;
        computerWeapon = getComputerWeapon();

        int result = determineWinner(playerWeapon, computerWeapon);

        if(playerLives > 0 && computerLives > 0) {
            updateBoard(result);
        }
    }

    public void scissorsButtonClicked(View v) {
        playerWeapon = Weapon.SCISSORS;
        computerWeapon = getComputerWeapon();

        int result = determineWinner(playerWeapon, computerWeapon);
        if(playerLives > 0 && computerLives > 0) {
            updateBoard(result);
        }
    }

    public Weapon getComputerWeapon() {
        Weapon cWeapon;
        Random rand = new Random();
        int range = 2;
        int randNum = rand.nextInt(range);

        switch(randNum) {
            case 0: cWeapon = Weapon.ROCK;
                    break;

            case 1: cWeapon = Weapon.PAPER;
                    break;

            default: cWeapon = Weapon.SCISSORS;
                    break;
        }

        return cWeapon;
    }

    public int determineWinner(Weapon pWep, Weapon cWep) {
        int winner = 0; //0 is tie, 1 is player wins, 2 is computer wins
        if (pWep == Weapon.PAPER) {
            if (cWep == Weapon.PAPER) {
                winner = 0;
            }
            else if (cWep == Weapon.ROCK) {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }
        else if (pWep == Weapon.SCISSORS) {
            if (cWep == Weapon.SCISSORS) {
                winner = 0;
            }
            else if (cWep == Weapon.PAPER) {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }
        else {
            if (cWep == Weapon.ROCK) {
                winner = 0;
            }
            else if (cWep == Weapon.SCISSORS) {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }
        return winner;
    }

    public void updateBoard(int r) {
        TextView p = (TextView) findViewById(R.id.playerWeaponText);
        p.setText("Player's Weapon: " + playerWeapon);

        TextView c = (TextView) findViewById(R.id.computerWeaponText);
        c.setText("Computer's Weapon: " + computerWeapon);

        TextView f = (TextView) findViewById(R.id.flavorText);
        if (computerLives > 0 && playerLives > 0) {
            if (r == 1) {
                if (playerWeapon == Weapon.ROCK) {
                    f.setText("Player wins ... Rock blunts scissors!");
                } else if (playerWeapon == Weapon.SCISSORS) {
                    f.setText("Player wins ... Scissors cuts paper!");
                } else {
                    f.setText("Player wins ... Paper covers rock!");
                }
                computerLives--;

            } else if (r == 2) {
                if (computerWeapon == Weapon.ROCK) {
                    f.setText("Computer wins ... Rock blunts scissors!");
                } else if (computerWeapon == Weapon.SCISSORS) {
                    f.setText("Computer wins ... Scissors cuts paper!");
                } else {
                    f.setText("Computer wins ... Paper covers rock!");
                }
                playerLives--;

            } else {
                f.setText("It's a draw!");
            }
        }

        if(playerLives == 0) {
            f.setText("Computer wins the game!");
        }

        if(computerLives == 0) {
            f.setText("Player wins the game!");
        }
        TextView s = (TextView) findViewById(R.id.scoreboardText);
        s.setText("Player: " + Integer.toString(playerLives) + ", Computer: " + Integer.toString(computerLives));

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        private String message;

        private Weapon(String msg) { message = msg; }

        @Override
        public String toString() { return message; }

    };
}
