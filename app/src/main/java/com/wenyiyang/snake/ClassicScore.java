package com.wenyiyang.snake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity used to display score and best score. Implement play again and go back to main menu.
 */
public class ClassicScore extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView highScoreTextView;
    private ImageView playAgainView;
    private ImageView gotoMainMenu;
    private Animation animation;
    private TextView gameOverLeft;
    private TextView gameOverMiddle;
    private TextView gameOverRight;

    // Initialize this activity and classic score of the snake game.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_classic_score);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initialTitle();
        initialScore();
        initialHighScore();
        playAgain();
        goToMainMenu();
    }

    // Display score of the player.
    private void initialScore() {
        scoreTextView = (TextView) findViewById(R.id.player_score);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_classic_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                        GameSettings.PREFS_NAME, MODE_PRIVATE);
                int playerScore = preferences.getInt(GameSettings.SCORE, 0);
                scoreTextView.setText("Score: " + String.valueOf(playerScore));
                scoreTextView.setTextColor(Color.WHITE);
                scoreTextView.setGravity(Gravity.CENTER);
                scoreTextView.setBackgroundResource(R.mipmap.menu_options);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scoreTextView.startAnimation(animation);
    }

    // Display the title: "GAME OVER".
    private void initialTitle() {
        gameOverLeft = (TextView) findViewById(R.id.game_over_left);
        gameOverMiddle = (TextView) findViewById(R.id.game_over_middle);
        gameOverRight = (TextView) findViewById(R.id.game_over_right);
        animation = AnimationUtils.loadAnimation(this, R.anim.back_for_title_left);
        animation.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gameOverLeft.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.back_for_title_middle);
        animation.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gameOverMiddle.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.back_for_title_right);
        animation.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gameOverRight.startAnimation(animation);
    }

    // If enter the button, users will play classic mode again.
    private void playAgain() {
        playAgainView = (ImageView) findViewById(R.id.play_again);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_bomb_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playAgainView.setImageResource(R.mipmap.again);
                playAgainView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ClassicScore.this, ClassicSnake.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        playAgainView.startAnimation(animation);

    }

    // If enter the button, users will go to main menu.
    private void goToMainMenu() {
        gotoMainMenu = (ImageView) findViewById(R.id.goto_main_menu);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_setting_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gotoMainMenu.setImageResource(R.mipmap.menu);
                gotoMainMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        scoreTextView.setBackgroundResource(R.mipmap.menu_options);
                        scoreTextView.setTextColor(Color.BLACK);
                        scoreTextView.setText("");
                        highScoreTextView.setBackgroundResource(R.mipmap.menu_options);
                        highScoreTextView.setTextColor(Color.BLACK);
                        highScoreTextView.setText("");
                        gotoMainMenu.setImageResource(R.mipmap.menu_options);
                        playAgainView.setImageResource(R.mipmap.menu_options);
                        Animation animation1 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.reverse_for_classic_button);
                        animation1.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation2 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.reverse_for_no_button);
                        animation2.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation3 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.reverse_for_setting_button);
                        animation3.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation4 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.reverse_for_bomb_button);
                        animation4.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation5 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.anim_for_title_left);
                        animation5.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animation6 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.anim_for_title_middle);
                        animation6.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animation7 = AnimationUtils.loadAnimation(ClassicScore.this, R.anim.anim_for_title_right);
                        animation7.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        scoreTextView.startAnimation(animation1);
                        playAgainView.startAnimation(animation4);
                        highScoreTextView.startAnimation(animation2);
                        gotoMainMenu.startAnimation(animation3);
                        gameOverLeft.startAnimation(animation5);
                        gameOverMiddle.startAnimation(animation6);
                        gameOverRight.startAnimation(animation7);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent settingsIntent = new Intent(ClassicScore.this, MainMenu.class);
                                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(settingsIntent);

                            }
                        }, GameSettings.START_NEW_ACTIVITY_DURATION);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gotoMainMenu.startAnimation(animation);

    }

    // Display best score of the player in classic mode.
    private void initialHighScore() {
        highScoreTextView = (TextView) findViewById(R.id.high_score_mode);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_no_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                setHighScore();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        highScoreTextView.startAnimation(animation);
    }

    // update best score in classic mode of the player.
    private void setHighScore() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int highScore = preferences.getInt(GameSettings.BEST_SCORE_CLASSIC, 0);
        int lastScore = preferences.getInt(GameSettings.SCORE, 0);
        if(lastScore > highScore) {
            editor.putInt(GameSettings.BEST_SCORE_CLASSIC, lastScore);
            editor.commit();
            highScore = lastScore;
        }
        highScoreTextView.setText("Best Score: " + String.valueOf(highScore));
        highScoreTextView.setTextColor(Color.WHITE);
        highScoreTextView.setGravity(Gravity.CENTER);
        highScoreTextView.setBackgroundResource(R.mipmap.menu_options);
    }

    @Override
    public void onBackPressed() {

    }
}
