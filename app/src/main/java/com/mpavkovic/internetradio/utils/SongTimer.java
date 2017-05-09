package com.mpavkovic.internetradio.utils;

import android.os.Looper;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.ui.radio_ui.LoadingDisplayer;
import com.mpavkovic.internetradio.ui.radio_ui.MetadataUpdater;
import com.mpavkovic.internetradio.ui.radio_ui.SongProgressUpdater;

/**
 * Class that defines a timer that runs whenever a song is played
 */
public class SongTimer implements Runnable
{
    public boolean volumeIsBeingChanged = false, seekIsBeingChanged = false;

    //Run the timer on a separate thread
    @Override
    public void run()
    {
        Looper.prepare();

        while (!Radio.stopTimer)
        {
            if (Radio.playerInitialized)
            {
                if (Radio.currentSong.isEnded() && !Radio.skippedBack)
                {
                    //Play next song
                    try
                    {
                        Radio.radioHandler.post(new LoadingDisplayer());
                    }
                    catch (Exception e)
                    {
                        System.out.println("[Internet Radio Warning] Song metadata could not be updated.");
                    }

                    Radio.skippedBack = false;
                    Radio.currentSong = Radio.songManager.findNextSong(Radio.currentIndex);
                    Radio.currentSong.clearPlays();
                    Radio.currentSong.play();

                    try
                    {
                        Radio.radioHandler.post(new MetadataUpdater());
                    }
                    catch (Exception e)
                    {
                        System.out.println("[Internet Radio Warning] Song metadata could not be updated.");
                    }

                    Radio.currentIndex++;
                    System.out.println("[Song Playback] Skipping to next song; current index after skip = " + Radio.currentIndex);
                }

                //If the device fell asleep, make sure the YouTube player is still recognized as initialized
//                if (Radio.sleepDetector.isAsleep()) Radio.playerInitialized = true;

                //Update progress bar based on progress of song
                try
                {
                    Radio.radioHandler.post(new SongProgressUpdater());
                }
                catch (Exception e)
                {
                    System.out.println("[Internet Radio Warning] seekBarSeek position could not be updated.");
                }

                //Sleep for 0.5 seconds
                try
                {
                    Thread.sleep(Constants.HALF_A_SECOND);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
