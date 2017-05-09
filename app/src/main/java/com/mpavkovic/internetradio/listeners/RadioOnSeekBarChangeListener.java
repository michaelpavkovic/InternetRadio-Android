package com.mpavkovic.internetradio.listeners;

import android.media.AudioManager;
import android.widget.SeekBar;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Class that handles the changing of progress of SeekBars
 */
public class RadioOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener
{
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        if (seekBar == Radio.radioUi.seekBarVolume)
        {
            Radio.timer.volumeIsBeingChanged = true;
        }
        else if (seekBar == Radio.radioUi.seekBarSeek)
        {
            Radio.timer.seekIsBeingChanged = true;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        if (seekBar == Radio.radioUi.seekBarVolume)
        {
            System.out.println("[SeekBar Listeners] Changing Music Volume based on position of seekBarVolume");

            float volumePositionFromSeekBar = seekBar.getProgress();
            float maxVolume = Radio.radioUi.audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float maxSeekBarPosition = seekBar.getMax();

            float volumePercentage = volumePositionFromSeekBar / maxSeekBarPosition;
            int volumeToSet = (int) (volumePercentage * maxVolume);

            Radio.radioUi.audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeToSet, 0);

            Radio.timer.volumeIsBeingChanged = false;
            System.out.println("[Internet Radio AudioManager] Set music volume: " + volumeToSet);
        }
        else if (seekBar == Radio.radioUi.seekBarSeek)
        {
            float progressFromSeekBar = seekBar.getProgress();
            float maxProgress = Radio.player.getDurationMillis();
            float maxSeekBarPosition = seekBar.getMax();

            float progressPercentage = progressFromSeekBar / maxSeekBarPosition;
            int progressToSet = (int) (progressPercentage * maxProgress);

            Radio.player.seekToMillis(progressToSet);

            Radio.timer.seekIsBeingChanged = false;
            System.out.println("[SeekBar Listeners] Changed progress of current song based on position of seekBarSeek");
        }
    }
}
