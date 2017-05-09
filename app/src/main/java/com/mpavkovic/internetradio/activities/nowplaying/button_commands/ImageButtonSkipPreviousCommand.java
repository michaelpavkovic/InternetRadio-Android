package com.mpavkovic.internetradio.activities.nowplaying.button_commands;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.ui.radio_ui.LoadingDisplayer;
import com.mpavkovic.internetradio.ui.radio_ui.MetadataUpdater;

/**
 * Button Command for imageButtonSkipNext
 */
public class ImageButtonSkipPreviousCommand
{
    public void start()
    {
        if (Radio.currentIndex > 0)
        {
            //play previous song
            try
            {
                Radio.radioHandler.post(new LoadingDisplayer());
            }
            catch (Exception e)
            {
                System.out.println("[Internet Radio Warning] Song metadata could not be updated.");
            }

            Radio.skippedBack = true;
            Radio.currentSong = Radio.songManager.findPreviousSong(Radio.currentIndex);
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

            Radio.currentIndex--;
            System.out.println("[Song Playback] Skipping to previous song; current index = " + Radio.currentIndex);
        }
    }
}