package com.wenyiyang.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Activity used to create no wall snake mode, in which if users hit the snake itself,
 * game over and automatically go to no wall score.
 */
public class NoWallsSnake extends AppCompatActivity {
    private boolean playMusic;
    private MediaPlayer musicPlayer;
    private RelativeLayout noWallsSnakeLayout;
    private boolean isInitialized;
    private GestureDetector gestureDetector;
    private boolean isPaused;
    private boolean isGoingLeft;
    private boolean isGoingRight;
    private boolean isGoingUp;
    private boolean isGoingDown;
    private boolean clickRight;
    private boolean clickLeft;
    private boolean clickDown;
    private boolean clickUp;
    private ImageView btnRight;
    private ImageView btnLeft;
    private ImageView btnUp;
    private ImageView btnDown;
    private boolean useBtns;
    private int playerScore;
    private boolean gameOver;
    private ArrayList<ImageView> parts;
    private int screenWidth;
    private int screenHeight;
    private ArrayList<ImageView> points;
    private boolean isCollide;
    private Handler myHandler;
    private ImageView head;
    private TextView scoreView;

    // Initialize this activity and no wall mode of the snake game.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_no_walls_snake);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        noWallsSnakeLayout = (RelativeLayout) findViewById(R.id.no_walls_snake_layout);
        noWallsSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
        noWallsSnakeLayout.setPaddingRelative(GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING,
                GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING);
        musicOnOff();
    }

    // Create MediaPlayer and turn on or turn off the background music.
    private void musicOnOff() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, Context.MODE_PRIVATE);
        playMusic = preferences.getBoolean(GameSettings.PLAY_MUSIC, true);
        musicPlayer = MediaPlayer.create(this, R.raw.music);
        if(playMusic) {
            musicPlayer.setLooping(true);
            musicPlayer.start();
        }
        else {
            musicPlayer.stop();
        }
    }

    // Detect whether the event is a swipe gesture.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    // Called automatically when the game is paused.
    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
        musicPlayer.release();
    }

    private void onSwipeRight() {
        if(!isGoingRight && !isGoingLeft) {
            isGoingRight = true;
            isGoingUp = false;
            isGoingDown = false;
        }
    }

    private void onSwipeLeft() {
        if(!isGoingRight && !isGoingLeft) {
            isGoingLeft = true;
            isGoingUp = false;
            isGoingDown = false;
        }
    }

    private void onSwipeUp() {
        if(!isGoingUp && !isGoingDown) {
            isGoingUp = true;
            isGoingLeft = false;
            isGoingRight = false;
        }
    }

    private void onSwipeDown() {
        if(!isGoingUp && !isGoingDown) {
            isGoingDown = true;
            isGoingLeft = false;
            isGoingRight = false;
        }
    }

    private void clickRight() {
        if(!clickRight && !clickLeft) {
            clickRight = true;
            clickUp = false;
            clickDown = false;
        }
    }

    private void clickLeft() {
        if(!clickRight && !clickLeft) {
            clickLeft = true;
            clickUp = false;
            clickDown = false;
        }
    }

    private void clickUp() {
        if(!clickUp && !clickDown) {
            clickUp = true;
            clickLeft = false;
            clickRight = false;
        }
    }

    private void clickDown() {
        if(!clickUp && !clickDown) {
            clickDown = true;
            clickLeft = false;
            clickRight = false;
        }
    }

    // If the button control mode is selected, show buttons otherwise hide buttons.
    private void buttonsDirectionInit() {
        btnRight = (ImageView) findViewById(R.id.btn_right);
        btnLeft = (ImageView) findViewById(R.id.btn_left);
        btnUp = (ImageView) findViewById(R.id.btn_up);
        btnDown = (ImageView) findViewById(R.id.btn_down);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeft();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUp();
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDown();
            }
        });

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, Context.MODE_PRIVATE);
        useBtns = preferences.getBoolean(GameSettings.USE_BUTTON_CONTROL, true);
        if (useBtns) {
            btnRight.setVisibility(View.VISIBLE);
            btnLeft.setVisibility(View.VISIBLE);
            btnUp.setVisibility(View.VISIBLE);
            btnDown.setVisibility(View.VISIBLE);
        } else {
            btnRight.setVisibility(View.INVISIBLE);
            btnLeft.setVisibility(View.INVISIBLE);
            btnUp.setVisibility(View.INVISIBLE);
            btnDown.setVisibility(View.INVISIBLE);
        }

    }

    // Create shake animation. Every time when the snake eat new food, the screen will shake.
    private void shake() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        shake.setDuration(GameSettings.SHAKE_DURATION);
        noWallsSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
        noWallsSnakeLayout.startAnimation(shake);
    }

    // Create fade animation. Every time when the score is n*4, the screen will fade in first, then
    // fade out.
    private void fadeAnim() {
        if(playerScore % GameSettings.POINTS_ANIMATION == 0) {
            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            noWallsSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake_change);
            noWallsSnakeLayout.startAnimation(fadeIn);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Animation fadeOut = AnimationUtils.loadAnimation(NoWallsSnake.this, R.anim.fade_out);
                    noWallsSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
                    noWallsSnakeLayout.startAnimation(fadeOut);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    // Check whether the snake bite itself.
    private void checkBitten() {
        ImageView snakeHead = parts.get(0);
        for(int i = 1; i < parts.size(); i++) {
            if(snakeHead.getX() == parts.get(i).getX() && snakeHead.getY() == parts.get(i).getY()) {
                gameOver();
                break;
            }
        }
    }

    // If game over, it will go to no wall score automatically.
    private void gameOver() {
        gameOver = true;
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(GameSettings.SCORE, playerScore);
        editor.commit();
        Intent intentScore = new Intent(this, NoWallsScore.class);
        intentScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentScore);
    }

    // After eat new food, add one unit to the snake.
    private void addTail() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.head);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((screenWidth*20)/450), ((screenHeight*30)/450));
        imageView.setLayoutParams(layoutParams);
        noWallsSnakeLayout.addView(imageView);
        parts.add(imageView);
    }

    // After a food was eaten, add a new food to a ramdom location.
    private void setNewPoint() {
        Random random = new Random();
        ImageView newPoint = new ImageView(this);
        float x = random.nextFloat()*(screenWidth - newPoint.getWidth());
        float y = random.nextFloat()*(screenHeight - newPoint.getHeight());
        newPoint.setImageResource(R.mipmap.food);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((screenWidth*20)/450), ((screenHeight*30)/450));
        newPoint.setLayoutParams(layoutParams);
        newPoint.setX(x);
        newPoint.setY(y);
        isCollide = false;
        noWallsSnakeLayout.addView(newPoint);
        points.add(newPoint);
    }

    // Initialize foods locations.
    private void setFoodPoints() {
        Random random = new Random();
        for(int i = 0; i < GameSettings.FOOD_POINTS; i++) {
            ImageView foodItem = new ImageView(this);
            float x = random.nextFloat() * (screenWidth - foodItem.getWidth());
            float y = random.nextFloat() * (screenHeight - foodItem.getHeight());
            foodItem.setImageResource(R.mipmap.food);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((screenWidth * 20) / 450), ((screenHeight * 30) / 450));
            foodItem.setLayoutParams(layoutParams);
            foodItem.setX(x);
            foodItem.setY(y);
            isCollide = false;
            noWallsSnakeLayout.addView(foodItem);
            points.add(foodItem);
        }
    }

    // If the game is not paused and over, update the location of snake, the scores of player, and
    // so on. Update every changes made.
    private void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!gameOver && !isPaused) {
                    try {
                        Thread.sleep(GameSettings.GAME_THREAD);
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0; i < points.size(); i++) {
                                    float left = head.getX() - head.getWidth();
                                    float top = head.getY() - head.getHeight();
                                    float right = head.getX() + head.getWidth();
                                    float bottom = head.getY() + head.getHeight();
                                    if(!isCollide) {
                                        ImageView food = points.get(i);
                                        float left1 = food.getX() - food.getWidth();
                                        float top1 = food.getY() - food.getHeight();
                                        float right1 = food.getX() + food.getWidth();
                                        float bottom1 = food.getY() + food.getHeight();
                                        Rect rc2 = new Rect();
                                        rc2.set((int) left1, (int) top1, (int) right1, (int) bottom1);
                                        Rect rc1 = new Rect();
                                        rc1.set((int) left, (int) top, (int) right, (int) bottom);

                                        food.getHitRect(rc2);
                                        if(Rect.intersects(rc1, rc2)) {
                                            noWallsSnakeLayout.removeView(food);
                                            points.remove(i);
                                            playerScore++;
                                            isCollide = true;
                                            scoreView.setText("Score: " + playerScore);
                                            setNewPoint();
                                            addTail();
                                            shake();
                                            fadeAnim();
                                        }
                                        checkBitten();
                                    }
                                }
                                isCollide = false;
                                if(isGoingRight || clickRight) {
                                    for(int i = parts.size() -1; i>=0; i--) {
                                        ImageView imageView = parts.get(i);
                                        if(i>0) {
                                            ImageView imageView2 = parts.get(i - 1);
                                            imageView.setX(imageView2.getX());
                                            imageView.setY(imageView2.getY());
                                        } else {
                                            imageView.setX(imageView.getX() + GameSettings.SPEED_X);
                                            if(imageView.getX() + imageView.getWidth() >= screenWidth) {
                                                imageView.setX(0);
                                            }
                                        }
                                    }
                                } else if(isGoingLeft ||  clickLeft) {
                                    for(int i=parts.size()-1; i>=0; i--) {
                                        ImageView imageView = parts.get(i);
                                        if(i>0) {
                                            ImageView imageView2 = parts.get(i-1);
                                            imageView.setX(imageView2.getX());
                                            imageView.setY(imageView2.getY());
                                        } else {
                                            imageView.setX(imageView.getX() - GameSettings.SPEED_X);
                                            if(imageView.getX()<=0) {
                                                imageView.setX(screenWidth - imageView.getWidth() / 2);
                                            }
                                        }
                                    }
                                } else if(isGoingDown || clickDown) {
                                    for(int i=parts.size()-1; i>=0; i--) {
                                        ImageView imageView = parts.get(i);
                                        if(i>0) {
                                            ImageView imageView2 = parts.get(i-1);
                                            imageView.setX(imageView2.getX());
                                            imageView.setY(imageView2.getY());
                                        } else {
                                            imageView.setY(imageView.getY() + GameSettings.SPEED_Y);
                                            if((imageView.getY() + imageView.getHeight() >=screenHeight)) {
                                                imageView.setY(0);
                                            }
                                        }
                                    }
                                } else if(isGoingUp || clickUp) {
                                    for(int i=parts.size()-1; i>=0; i--) {
                                        ImageView imageView = parts.get(i);
                                        if (i > 0) {
                                            ImageView imageView2 = parts.get(i - 1);
                                            imageView.setX(imageView2.getX());
                                            imageView.setY(imageView2.getY());
                                        } else {
                                            imageView.setY(imageView.getY() - GameSettings.SPEED_Y);
                                            if ((imageView.getY() <= 0)) {
                                                imageView.setY(screenHeight - imageView.getHeight() / 2);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                    catch(InterruptedException e) {
                        Thread.currentThread().interrupt();

                    }
                }
            }
        }).start();
    }

    // Detect the direction of a swipe gesture: up, down, left, and right.
    public class SwipeGestureDirector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;

            if(!useBtns) {
                try {
                    float diffX = e2.getX() - e1.getX();
                    float diffY = e2.getY() - e1.getY();
                    if(Math.abs(diffX) > Math.abs(diffY)) {
                        // Horizontal swipe
                        if(Math.abs(diffX) > GameSettings.SWIPE_THRESH_HOLD
                                && Math.abs(velocityX) > GameSettings.SWIPE_VELOCITY_THRESH_HOLD) {
                            if(diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                        result = true;
                    } else if (Math.abs(diffY) > GameSettings.SWIPE_THRESH_HOLD
                            && Math.abs(velocityY) > GameSettings.SWIPE_VELOCITY_THRESH_HOLD) {
                        // Vertical swipe
                        if(diffY >0) {
                            onSwipeDown();
                        } else {
                            onSwipeUp();
                        }
                        result = true;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
            return result;
        }
    }

    // when get focus at the first time, do initialization.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(!isInitialized) {
            isInitialized = true;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;
            myHandler = new Handler();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            gestureDetector = new GestureDetector(null, new SwipeGestureDirector());
            head = new ImageView(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ((screenWidth * 20) / 450), ((screenHeight * 30) / 450));
            head.setImageResource(R.mipmap.head);
            head.setLayoutParams(layoutParams);
            head.setX(screenWidth / 2 - head.getWidth());
            head.setY(screenHeight / 2 - head.getHeight());
            noWallsSnakeLayout.addView(head);

            parts = new ArrayList<ImageView>();
            points = new ArrayList<ImageView>();
            scoreView = (TextView) findViewById(R.id.score);
            parts.add(0, head);


            layoutParams.setMargins(GameSettings.LAYOUT_MARGIN,
                    GameSettings.LAYOUT_MARGIN,
                    GameSettings.LAYOUT_MARGIN,
                    GameSettings.LAYOUT_MARGIN);

            setFoodPoints();
            buttonsDirectionInit();
        }
        if (hasFocus) {
            isPaused = false;
            update();
        }
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onBackPressed() {

    }
}
