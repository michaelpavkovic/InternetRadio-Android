package com.mpavkovic.internetradio.ui.radio_ui;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Runnable that updates the position of the song progress bar
 */
public class SongProgressUpdater implements Runnable
{
    @Override
    public void run()
    {
        if (Radio.currentSong.isPlaying() && !Radio.timer.seekIsBeingChanged)
        {
            Radio.radioUi.updateSongTimeLabels();
            Radio.radioUi.updateSongProgressBarPosition();
        }
    }
}
