package com.example.xo;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class XOActivity extends AppCompatActivity {

    private TextView textView;
    private Button[] buttons = new Button[9];
    private boolean isXTurn = true;
    private boolean[] isPressed = new boolean[9];
    String player1;
    String player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xo);

        textView = (TextView) findViewById(R.id.xo_text_view);
        buttons[0] = (Button) findViewById(R.id.xo_button_0);
        buttons[1] = (Button) findViewById(R.id.xo_button_1);
        buttons[2] = (Button) findViewById(R.id.xo_button_2);
        buttons[3] = (Button) findViewById(R.id.xo_button_3);
        buttons[4] = (Button) findViewById(R.id.xo_button_4);
        buttons[5] = (Button) findViewById(R.id.xo_button_5);
        buttons[6] = (Button) findViewById(R.id.xo_button_6);
        buttons[7] = (Button) findViewById(R.id.xo_button_7);
        buttons[8] = (Button) findViewById(R.id.xo_button_8);
        Button buttonRestart = (Button) findViewById(R.id.xo_button_restart);

        Arrays.fill(isPressed,  false);

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(0);
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(1);
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(2);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(3);
            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(4);
            }
        });

        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(5);
            }
        });

        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(6);
            }
        });

        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(7);
            }
        });

        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClick(8);
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestartClick();
            }
        });

        textView.setText(getString(R.string._X_player) + getString(R.string._turn));
    }

    private void BtnClick(int btnNum) {
        if (!isPressed[btnNum]) {
            isPressed[btnNum] = true;
            if (isXTurn) {
                this.buttons[btnNum].setText(R.string._X);
                if (!winCheck()){
                    isXTurn = false;
                    textView.setText(getString(R.string._Y_player) + getString(R.string._turn));
                }
            } else {
                this.buttons[btnNum].setText(R.string._O);
                if (!winCheck()){
                    isXTurn = true;
                    textView.setText(getString(R.string._X_player) + getString(R.string._turn));
                }
            }
        }
    }

    private void RestartClick() {
        Arrays.fill(isPressed,  false);
        isXTurn = true;
        textView.setText(getString(R.string._X_player) + getString(R.string._turn));
        for (Button b:buttons){
            b.setText("");
            b.clearAnimation();
        }
//                Intent intent = getIntent();
//                finish();
//                startActivity(intent);
    }

    public boolean winCheck(){
        if (       lineCheck(0, 1, 2)
                || lineCheck(3, 4, 5)
                || lineCheck(6, 7, 8)
                || lineCheck(0, 3, 6)
                || lineCheck(1, 4, 7)
                || lineCheck(2, 5, 8)
                || lineCheck(0, 4, 8)
                || lineCheck(2, 4, 6)){
            Arrays.fill(isPressed, true);
            if (isXTurn) {
                textView.setText(getString(R.string._X_player) + getString(R.string._win));
            }
            else {
                textView.setText(getString(R.string._Y_player) + getString(R.string._win));
            }
            return true;
        }
        return false;
    }

    public boolean lineCheck(int i, int j, int k){
        if (isPressed[i] && isPressed[j]
                && buttons[i].getText().equals(buttons[j].getText())
                && buttons[j].getText().equals(buttons[k].getText())) {
            blink(i, j, k);
            return true;
        }
        else return false;
    }

    public void blink (int i, int j, int k){
        manageBlinkEffect(buttons[i]);
        manageBlinkEffect(buttons[j]);
        manageBlinkEffect(buttons[k]);
    }

    public void manageBlinkEffect(Button btn) {
        Animation anim = new AlphaAnimation(0.5f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        btn.startAnimation(anim);
    }
}