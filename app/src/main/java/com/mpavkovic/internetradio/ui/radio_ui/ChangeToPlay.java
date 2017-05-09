package com.mpavkovic.internetradio.ui.radio_ui;

import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Class that runs on the UI thread to change the play/pause button to play
 */
public class ChangeToPlay implements Runnable
{
    @Override
    public void run()
    {
        Radio.radioUi.imageButtonPlay.setImageResource(R.mipmap.play);
        Radio.currentSong.statusChange = false;
        System.out.println("The button changed from pause to play.");

        //Get current time
        Radio.currentTime = Radio.player.getCurrentTimeMillis();
    }
}
