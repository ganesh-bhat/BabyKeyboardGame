package emacs.babykeyboardgame;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {


    private static final String TAG = "GameActivity";
    TextView tv;
    Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv = (TextView)findViewById(R.id.charPressed);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateText();

            }
        });
        setRandomColorToText();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);


    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        char textToSet =  event.getDisplayLabel();
        setRandomColorToText();
        Log.i(TAG, "setting text and randomizing color"+textToSet);
        tv.setText(String.valueOf(textToSet));
        animateText();
        playSound();
        return true;
    }

    private void setRandomColorToText() {
       /* int startColor = getRandomColor();
        int endColor = getRandomColor(); */

        Shader textShader=new LinearGradient(0, 0, 150, 150,
                new int[]{Color.BLACK, Color.BLUE},
                new float[]{0, 1}, Shader.TileMode.MIRROR);
        tv.getPaint().setShader(textShader);
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void playSound() {
        try {
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void animateText() {

        ScaleAnimation animation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 1.0f,
                ScaleAnimation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(300);
        animation.setRepeatMode(Animation.ABSOLUTE);
        animation.setRepeatCount(0);
        tv.startAnimation(animation);
    }
}
