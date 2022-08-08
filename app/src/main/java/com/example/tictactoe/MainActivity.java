package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    //Player Representation
    //0-- X
    //1-- O
    int activePlayer=0;
    int [] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State Meaning
    //0 -- X
    //1 --O
    //2 -- Null
    int [][] winPos={{0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}};

    public void playerTap(View view){
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage]==2 ) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status =findViewById(R.id.status);
                status.setText("O's Turn.. Tap to Play.");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status =findViewById(R.id.status);
                status.setText("X's Turn.. Tap to Play.");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if any Player has Won..
        for (int []winPosition:winPos){
            if (gameState[winPosition[0]]==gameState[winPosition[1]] &&
                    gameState[winPosition[1]]==gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                //Somebody Has Won!! Find who !!
                String winnerStr;
                gameActive=false;
                if (gameState[winPosition[0]]==0){
                    winnerStr="X has won!!";
                }
                else{
                    winnerStr="O has Won !!";
                }
                //Update Status Bar For Winner Announcement:--->
                TextView status =findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        boolean emptySquare=false;
        for (int squareState:gameState){
            if (squareState==2){
                emptySquare=true;
                break;
            }
        }
        if (!emptySquare && gameActive){
            gameActive=false;
            String winnerstr;
            winnerstr="Match is Draw!!";
            TextView status =findViewById(R.id.status);
            status.setText(winnerstr);
        }
    }
    public void gameReset(View view) {
        gameActive=true;
        activePlayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        TextView status =findViewById(R.id.status);
        status.setText("X's Turn.. Tap to Play.");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}