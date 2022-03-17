package com.monk3y.xo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive= true;
    //Player Representation
    //1-X
    //2-O
    int activePlayer = 1;
    int[] gameState ={0,0,0,0,0,0,0,0,0,0};
    int chance=1;
    //State meanings
    // 0-Null
    // 1-X
    // 2-O
    int[][]winPositions ={{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
    public void tapped(View view) {
        ImageView img = (ImageView)view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        chance+=1;
        if(!gameActive) {
            gameReset(view);
        }
        else if(gameState[tappedImage]==0){

            gameState[tappedImage]= activePlayer;
            img.setTranslationY(-1000f);

            if(activePlayer==1){
                img.setImageResource(R.drawable.cross);
                activePlayer=2;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            }
            else{
                img.setImageResource(R.drawable.circle);
                activePlayer=1;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Checking If Anyone Wins
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[2]] == gameState[winPosition[1]] &&
                    gameState[winPosition[0]]!=0) {
                //Somebody has won
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]]==1) {
                    winnerStr = "X Wins - Tap to Reset";
                }
                else {
                    winnerStr = "O Wins - Tap to Reset";
                }
                // Status updates to announce the winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        if(chance==10){
            gameActive=false;
            TextView status = findViewById(R.id.status);
            status.setText("Draw - Tap to Reset");
        }
    }

    public void gameReset(View view) {
        gameActive=true;
        chance=1;
        activePlayer=1;
        for(int i=0; i<gameState.length; i++){
            gameState[i]=0;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
