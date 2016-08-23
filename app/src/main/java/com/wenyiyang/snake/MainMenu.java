package com.wenyiyang.snake;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Activity used to create main menu of the snake game, through which users can access classic mode,
 * bomb mode, no wall mode, and setting.
 */
public class MainMenu extends AppCompatActivity {

    private RelativeLayout snakeLayout;
    private Animation animation;
    private AdView adView;
    private ImageView classicBtn;
    private ImageView noWallsBtn;
    private ImageView bombBtn;
    private ImageView settingsBtn;
    private TextView snakeLeft;
    private TextView snakeMiddle;
    private TextView snakeRight;

    // Initialize this activity and main menu of the snake game.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        snakeLayout = (RelativeLayout) findViewById(R.id.snake_layout);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(GameSettings.MY_AD_UNIT_ID);
        snakeLayout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        // AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        initialClassic();
        initialNoWalls();
        initialBomb();
        initialTitle();
        initialSettings();
    }

    // Initialize classic mode button through which users can access classic mode.
    private void initialClassic() {
        classicBtn = (ImageView) findViewById(R.id.classic);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_classic_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                classicBtn.setImageResource(R.mipmap.classic);
                classicBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentClasssic = new Intent(MainMenu.this, ClassicSnake.class);
                        intentClasssic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentClasssic);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        classicBtn.startAnimation(animation);
    }

    // Initialize no wall mode button through which users can access no wall mode.
    private void initialNoWalls() {
        noWallsBtn = (ImageView) findViewById(R.id.no_walls);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_no_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                noWallsBtn.setImageResource(R.mipmap.no_walls);
                noWallsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentNoWalls = new Intent(MainMenu.this, NoWallsSnake.class);
                        intentNoWalls.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentNoWalls);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        noWallsBtn.startAnimation(animation);
    }

    // Initialize bomb mode button through which users can access bomb mode.
    private void initialBomb() {
        bombBtn = (ImageView) findViewById(R.id.bomb);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_bomb_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bombBtn.setImageResource(R.mipmap.bombsnake);
                bombBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentBomb = new Intent(MainMenu.this, BombSnake.class);
                        intentBomb.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentBomb);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bombBtn.startAnimation(animation);
    }

    // Initialize game title "SNAKE GAME".
    private void initialTitle() {
        snakeLeft = (TextView) findViewById(R.id.snake_left);
        snakeMiddle = (TextView) findViewById(R.id.snake_middle);
        snakeRight = (TextView) findViewById(R.id.snake_right);
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
        snakeLeft.startAnimation(animation);
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
        snakeMiddle.startAnimation(animation);
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
        snakeRight.startAnimation(animation);
    }

    // Initialize setting button through which users can access game setting.
    private void initialSettings() {
        settingsBtn = (ImageView) findViewById(R.id.settings);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_setting_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settingsBtn.setImageResource(R.mipmap.settings);
                settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View v){
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        classicBtn.setImageResource(R.mipmap.menu_options);
                        noWallsBtn.setImageResource(R.mipmap.menu_options);
                        settingsBtn.setImageResource(R.mipmap.menu_options);
                        bombBtn.setImageResource(R.mipmap.menu_options);
                        Animation animation1 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_classic_button);
                        animation1.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation2 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_no_button);
                        animation2.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation3 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_setting_button);
                        animation3.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation4 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_bomb_button);
                        animation4.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation5 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_left);
                        animation5.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animation6 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_middle);
                        animation6.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animation7 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_right);
                        animation7.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        classicBtn.startAnimation(animation1);
                        noWallsBtn.startAnimation(animation2);
                        settingsBtn.startAnimation(animation3);
                        bombBtn.startAnimation(animation4);
                        snakeLeft.startAnimation(animation5);
                        snakeMiddle.startAnimation(animation6);
                        snakeRight.startAnimation(animation7);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                           public void run() {
                                Intent settingsIntent = new Intent(MainMenu.this, Settings.class);
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
        settingsBtn.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {

    }
}
