package com.mpavkovic.internetradio.ui.radio_ui;

import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Class that runs on the UI thread to change the play/pause button to pause
 */
public class ChangeToPause implements Runnable
{
    @Override
    public void run()
    {
        Radio.radioUi.imageButtonPlay.setImageResource(R.mipmap.pause);
        Radio.currentSong.statusChange = false;
        System.out.println("The button changed from play to pause.");

        //Get current time
        Radio.currentTime = Radio.player.getCurrentTimeMillis();
    }
}
