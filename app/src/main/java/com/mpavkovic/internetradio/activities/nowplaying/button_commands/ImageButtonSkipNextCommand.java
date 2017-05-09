package com.mpavkovic.internetradio.activities.nowplaying.button_commands;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.utils.Constants;

/**
 * Button Command for imageButtonSkipNext
 */
public class ImageButtonSkipNextCommand
{
    public void start()
    {
        if (Radio.currentIndex < Constants.DEFAULT_PLAYLIST_LENGTH - 1)
        {
            //play next song by ending the current song
            Radio.currentSong.setEnded(true);
            Radio.skippedBack = false;
        }
    }
}