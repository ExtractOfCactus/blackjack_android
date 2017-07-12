package com.example.glenyoung.blackjackandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameScreenActivity extends AppCompatActivity {
    int index;
    Button hitButton;
    Button standButton;
    TextView dealerName;
    TextView player1Name;
    TextView player2Name;
    TextView player3Name;
    TextView playerTurn;
    Viewer viewer = new Viewer();
    Game game = new Game(viewer);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Log.d((getClass().toString()), "onCreate called");

        index = 0;

        hitButton = (Button) findViewById(R.id.hit_button);
        standButton = (Button) findViewById(R.id.stand_button);
        dealerName = (TextView) findViewById(R.id.dealer_name);
        player1Name = (TextView) findViewById(R.id.player1_name);
        player2Name = (TextView) findViewById(R.id.player2_name);
        player3Name = (TextView) findViewById(R.id.player3_name);
        playerTurn = (TextView) findViewById(R.id.player_turn);

        String nameOfDealer = game.getDealer().getName();
        dealerName.setText(nameOfDealer);

        ArrayList<Player> players = game.getPlayers();

        game.initialDeal();

        Player player1 = players.get(0);
        player1Name.setText(player1.getName() + ": " + game.handValue(player1));

        Player player2 = players.get(1);
        player2Name.setText(player2.getName() + ": " + game.handValue(player2));

        Player player3 = players.get(2);
        player3Name.setText(player3.getName() + ": " + game.handValue(player3));

        if (game.handValue(player1) == 21) {
            index += 1;
        }
    }

    

    public void onHitButtonClick(View button) {

        Player player = game.getPlayers().get(index);
        game.getDealer().deal(player);

        Player player1 = game.getPlayers().get(0);
        player1Name.setText(player1.getName() + ": " + game.handValue(player1));

        Player player2 = game.getPlayers().get(1);
        player2Name.setText(player2.getName() + ": " + game.handValue(player2));

        Player player3 = game.getPlayers().get(2);
        player3Name.setText(player3.getName() + ": " + game.handValue(player3));

        if (game.handValue(player) == 21 || game.isPlayerBust(player)) {
            onStandButtonClick(standButton);
        }
    }

    public void onStandButtonClick(View button) {
        index += 1;
        Player player = game.getPlayers().get(index);
        if (game.handValue(player) == 21) {
            index += 1;
        }
        if (index == 4) {
            game.dealerFinish();
        }
        playerTurn.setText(player.getName() + ", it is your turn");
    }


}
