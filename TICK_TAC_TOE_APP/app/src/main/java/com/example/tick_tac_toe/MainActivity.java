package com.example.tick_tac_toe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    ImageView[] images;
    boolean toggle = false;

    int usedColumns = 0;
    Animation fade_in_animation;
    Animation fade_out_animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = new ImageView[9];
        images[0] = findViewById(R.id.img1);
        images[1] = findViewById(R.id.img2);
        images[2] = findViewById(R.id.img3);
        images[3] = findViewById(R.id.img4);
        images[4] = findViewById(R.id.img5);
        images[5] = findViewById(R.id.img6);
        images[6] = findViewById(R.id.img7);
        images[7] = findViewById(R.id.img8);
        images[8] = findViewById(R.id.img9);
        fade_in_animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fade_out_animation = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        clearTable(true);
    }

    public void Check(View view){
        ImageView img = (ImageView) view;
        fillValues(img);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void fillValues(ImageView img){
        Toast toast;
        int resIdEmpty = R.drawable.empty;
        int resIdImg = (int)img.getTag();

        // Checking if the place is already used.
        if(resIdImg != resIdEmpty){
            toast = Toast.makeText(this,"Place is already used",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        // Decision of writing O or X at the current location
        if(toggle){
            img.setImageResource(R.drawable.zero);
            img.setTag(R.drawable.zero);
        }else{
            img.setImageResource(R.drawable.cross);
            img.setTag(R.drawable.cross);
        }
        img.startAnimation(fade_in_animation);
        usedColumns++;

        if(usedColumns>=5){
            if(matchFinished()){
                if(toggle){
                    toast = Toast.makeText(this,"Player with symbol O won this match",Toast.LENGTH_LONG);
                }else{
                    toast = Toast.makeText(this,"Player with symbol X won this match",Toast.LENGTH_LONG);
                }
                toast.show();
                clearTable(false);
            }else if(usedColumns>=9){
                clearTable(false);
            }
        }
        toggle = !toggle;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void clearTable(boolean firstTime){
        if(firstTime){
            for(int i=0;i<9;i++){
                images[i].setImageResource(R.drawable.empty);
                images[i].setTag(R.drawable.empty);
            }
        }else{
            clearTable2(0);
        }
        usedColumns = 0;
    }
    public void clearTable2(int index){
        if (index >= 9) {
            // All cells are cleared, do any necessary post-cleanup
            return;
        }

        final Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        animation.setDuration(50); // Set the duration of the animation in milliseconds

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                images[index].setImageResource(R.drawable.empty);
                images[index].setTag(R.drawable.empty);
                clearTable2(index+1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        images[index].startAnimation(animation);
    }

    public boolean matchFinished(){
        int emptyId = R.drawable.empty;

        if((int)images[0].getTag()!=emptyId && (int)images[0].getTag()== (int)images[1].getTag()&& (int)images[1].getTag()== (int)images[2].getTag()){
            return true;
        }
        else if((int)images[3].getTag()!=emptyId && (int)images[3].getTag()== (int)images[4].getTag()&& (int)images[4].getTag()== (int)images[5].getTag()){
            return true;
        }
        else if((int)images[6].getTag()!=emptyId && (int)images[6].getTag()== (int)images[7].getTag()&& (int)images[7].getTag()== (int)images[8].getTag())
        {
            return true;
        }
        else if((int)images[0].getTag()!=emptyId && (int)images[0].getTag()== (int)images[3].getTag()&& (int)images[3].getTag()== (int)images[6].getTag()){
            return true;
        }
        else if((int)images[1].getTag()!=emptyId && (int)images[1].getTag()== (int)images[4].getTag()&& (int)images[4].getTag()== (int)images[7].getTag()){
            return true;
        }
        else if((int)images[2].getTag()!=emptyId && (int)images[2].getTag()== (int)images[5].getTag()&& (int)images[5].getTag()== (int)images[8].getTag()){
            return true;
        }
        else if((int)images[0].getTag()!=emptyId && (int)images[0].getTag()== (int)images[4].getTag()&& (int)images[4].getTag()== (int)images[8].getTag()){
            return true;
        }
        else if((int)images[2].getTag()!=emptyId && (int)images[2].getTag()== (int)images[4].getTag()&& (int)images[4].getTag()== (int)images[6].getTag()){
            return true;
        }else{
            return false;
        }
    }

}