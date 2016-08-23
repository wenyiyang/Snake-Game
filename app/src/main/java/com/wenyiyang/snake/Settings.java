package com.wenyiyang.snake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity through which users can do game settings including turn on/off music and select button
 * operation and gesture operation.
 */
public class Settings extends AppCompatActivity {

    private Animation animation;
    private TextView titleLeft;
    private TextView titleMiddle;
    private TextView titleRight;
    private ImageView musicSwitchBtn;
    private ImageView swipeBtn;
    private ImageView homeBtn;
    private boolean isBtnOn;
    private boolean isMusicOn;

    // Initialize this activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initialTitle();
        initialMusicSwitchBtn();
        initialHomeBtn();
        initialSwipeBtn();
    }

    // Initialize the button through which users can select button operation or gesture operation.
    private void initialSwipeBtn() {
        swipeBtn = (ImageView) findViewById(R.id.swipe);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_classic_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, MODE_PRIVATE);
                isBtnOn = preferences.getBoolean(GameSettings.USE_BUTTON_CONTROL, true);
                if(!isBtnOn) swipeBtn.setImageResource(R.mipmap.swipe);
                else swipeBtn.setImageResource(R.mipmap.buttons);
                swipeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipeBtn.setImageDrawable(null);
                        if(isBtnOn) {
                            isBtnOn = false;
                            swipeBtn.setImageResource(R.mipmap.swipe);
                        }
                        else {
                            isBtnOn = true;
                            swipeBtn.setImageResource(R.mipmap.buttons);
                        }
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(GameSettings.USE_BUTTON_CONTROL, isBtnOn);
                        editor.commit();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        swipeBtn.startAnimation(animation);
    }

    // Initialize the button through which users can turn on/off music.
    private void initialMusicSwitchBtn() {
        musicSwitchBtn = (ImageView) findViewById(R.id.music);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_no_button);
        animation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, MODE_PRIVATE);
                isMusicOn = preferences.getBoolean(GameSettings.PLAY_MUSIC, true);
                if(isMusicOn) musicSwitchBtn.setImageResource(R.mipmap.music_on);
                else musicSwitchBtn.setImageResource(R.mipmap.music_off);
                musicSwitchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        musicSwitchBtn.setImageDrawable(null);
                        if(isMusicOn) {
                            isMusicOn = false;
                            musicSwitchBtn.setImageResource(R.mipmap.music_off);
                        }
                        else {
                            isMusicOn = true;
                            musicSwitchBtn.setImageResource(R.mipmap.music_on);
                        }
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(GameSettings.PLAY_MUSIC, isMusicOn);
                        editor.commit();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        musicSwitchBtn.startAnimation(animation);
    }

    // Though which users can go to main menu.
    private void initialHomeBtn() {
        homeBtn = (ImageView) findViewById(R.id.home);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_home_button);
        animation.setDuration(GameSettings.ANIMATION_SHOW_HOME_BUTTON_DURATION);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                homeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View v){
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        musicSwitchBtn.setImageResource(R.mipmap.menu_options);
                        swipeBtn.setImageResource(R.mipmap.menu_options);
                        Animation animation1 = AnimationUtils.loadAnimation(Settings.this, R.anim.reverse_for_classic_button);
                        animation1.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation2 = AnimationUtils.loadAnimation(Settings.this, R.anim.reverse_for_no_button);
                        animation2.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation3 = AnimationUtils.loadAnimation(Settings.this, R.anim.reverse_for_home_button);
                        animation3.setDuration(GameSettings.ANIMATION_HIDE_HOME_BUTTON_DURATION);
                        Animation animation5 = AnimationUtils.loadAnimation(Settings.this, R.anim.anim_for_title_left);
                        animation5.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animation6 = AnimationUtils.loadAnimation(Settings.this, R.anim.anim_for_title_middle);
                        animation6.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animation7 = AnimationUtils.loadAnimation(Settings.this, R.anim.anim_for_title_right);
                        animation7.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        swipeBtn.startAnimation(animation1);
                        musicSwitchBtn.startAnimation(animation2);
                        homeBtn.startAnimation(animation3);
                        titleLeft.startAnimation(animation5);
                        titleMiddle.startAnimation(animation6);
                        titleRight.startAnimation(animation7);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent settingsIntent = new Intent(Settings.this, MainMenu.class);
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
        homeBtn.startAnimation(animation);
    }

    // Display the title: "SNAKE GAME".
    private void initialTitle() {
        titleLeft = (TextView) findViewById(R.id.snake_left);
        titleMiddle = (TextView) findViewById(R.id.snake_middle);
        titleRight = (TextView) findViewById(R.id.snake_right);
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
        titleLeft.startAnimation(animation);

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
        titleMiddle.startAnimation(animation);

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
        titleRight.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {

    }
}
