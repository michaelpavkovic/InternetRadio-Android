package com.mpavkovic.internetradio.activities.nowplaying.button_commands;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Button Command for imageButtonShow
 */
public class ImageButtonHideCommand
{
    public void start()
    {
        //Animate the extraControls panel moving down to be hidden
        Animation animation = AnimationUtils.loadAnimation(Radio.radioContext, R.anim.slide_down);
        Radio.radioUi.extraControls.startAnimation(animation);

        Radio.radioUi.extraControls.setVisibility(View.INVISIBLE);
        Radio.radioUi.imageButtonShow.setVisibility(View.VISIBLE);
    }
}
