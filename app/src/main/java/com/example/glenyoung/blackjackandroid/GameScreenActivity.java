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
    private int index;
    private Button hitButton;
    private Button standButton;
    private Button dealButton;
    private TextView dealerName;
    private TextView player1Name;
    private TextView player2Name;
    private TextView player3Name;
    private TextView playerTurn;

    ArrayList<TextView> views;
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
        dealButton = (Button) findViewById(R.id.deal_button);
        dealButton.setVisibility(View.INVISIBLE);
        dealerName = (TextView) findViewById(R.id.dealer_name);
        player1Name = (TextView) findViewById(R.id.player1_name);
        player2Name = (TextView) findViewById(R.id.player2_name);
        player3Name = (TextView) findViewById(R.id.player3_name);
        playerTurn = (TextView) findViewById(R.id.player_turn);


        ArrayList<Player> players = game.getPlayers();
        views = new ArrayList<>();
        views.add(player1Name);
        views.add(player2Name);
        views.add(player3Name);

        game.initialDeal();

        String nameOfDealer = game.getDealer().getName();
        dealerName.setText(nameOfDealer + ": " + game.handValue(game.getDealer()));

        Player player1 = players.get(0);
        player1Name.setText(player1.getName() + ": " + game.handValue(player1));

        Player player2 = players.get(1);
        player2Name.setText(player2.getName() + ": " + game.handValue(player2));

        Player player3 = players.get(2);
        player3Name.setText(player3.getName() + ": " + game.handValue(player3));

        playerTurn.setText(viewer.turn(player1));

        if (game.checkBlackjack(player1)) {
            player1Name.setText(viewer.declareBlackjack(player1));
            onStandButtonClick(standButton);
        }
    }


    public void onHitButtonClick(View button) {

        Player player = game.getPlayers().get(index);
        game.getDealer().deal(player);

        TextView view = views.get(index);
        view.setText(viewer.score(player, game.handValue(player)));

        if (game.handValue(player) == 21) {
            onStandButtonClick(standButton);
        }
        if (game.isPlayerBust(player)) {
            view.setText(viewer.playerBust(player));
            onStandButtonClick(standButton);
        }
    }

    public void onStandButtonClick(View button) {
        if (index > 2) {
            dealerEndgame();
        }
        index += 1;
        if (index > 2) {
            dealerEndgame();
        }
        else {
            Player player = game.getPlayers().get(index);
            if (game.checkBlackjack(player)) {
                TextView view = views.get(index);
                view.setText(viewer.declareBlackjack(player));
                index += 1;
                if (index > 2) {
                    dealerEndgame();
                }
                player = game.getPlayers().get(index);
                if (game.handValue(player) == 21) {
                    index += 1;
                    if (index > 2) {
                        dealerEndgame();
                    }
                }
            }
            playerTurn.setText(viewer.turn(player));
        }
    }

    public void onDealButtonClick(View button) {
        recreate();
    }

    public void makeButtonsInvisible() {
        hitButton.setVisibility(View.INVISIBLE);
        standButton.setVisibility(View.INVISIBLE);
    }

    public void dealerEndgame() {
        makeButtonsInvisible();
        if (game.allBust()) {
            playerTurn.setText(viewer.allBust());
        }
        else {
            game.dealerFinish();
            playerTurn.setText("Game Over");
            dealerName.setText(game.dealerResult());
            player1Name.setText(game.compareHands(game.getPlayers().get(0)));
            player2Name.setText(game.compareHands(game.getPlayers().get(1)));
            player3Name.setText(game.compareHands(game.getPlayers().get(2)));
        }
        dealButton.setVisibility(View.VISIBLE);
    }


}
