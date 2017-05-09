package com.mpavkovic.internetradio.ui.radio_ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.AudioManager;
import android.os.PowerManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerFragment;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.song.internet.metadata_getters.AlbumArtUrlGetter;
import com.mpavkovic.internetradio.ui.UI;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Handles the instantiation of the Radio UI
 */
public class RadioUI implements UI
{
    //YouTube stuff
    public YouTubePlayerFragment songView;
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    public AudioManager audioManager;
    public PowerManager powerManager;

    //Layouts
    public RelativeLayout extraControls;

    //Buttons
    public ImageButton imageButtonShow, imageButtonHide;
    public ImageButton imageButtonPlay; public boolean firstPlay = true;
    public ImageButton imageButtonSkipPrevious, imageButtonSkipNext;

    //Seek Bars
    public SeekBar seekBarSeek, seekBarVolume;

    //Labels
    public TextView lblTimePased, lblTimeRemaining, lblMetadata;

    //Methods
    public void setInitialVolumeSeekBarPosition()
    {
        double volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        double maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        double maxPos = seekBarVolume.getMax();

        int positionToSet = (int) (volume / maxVolume * maxPos);
        seekBarVolume.setProgress(positionToSet);
    }

    public void updateSongProgressBarPosition()
    {
        double timeInMillis = Radio.player.getCurrentTimeMillis();
        double songDurationInMillis = Radio.player.getDurationMillis();
        double maxPos = seekBarSeek.getMax();

        int positionToSet = (int) (timeInMillis / songDurationInMillis * maxPos);
        seekBarSeek.setProgress(positionToSet);
    }

    public void updateSongTimeLabels()
    {
        int timePassedInMillis = Radio.player.getCurrentTimeMillis();
        int songDurationInMillis = Radio.player.getDurationMillis();
        int timeRemainingInMillis = songDurationInMillis - timePassedInMillis;

        String timePassed = "00:00";
        String timeRemaining = "00:00";

        timePassed = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timePassedInMillis),
                TimeUnit.MILLISECONDS.toSeconds(timePassedInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timePassedInMillis))
        );
        timeRemaining = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeRemainingInMillis),
                TimeUnit.MILLISECONDS.toSeconds(timeRemainingInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeRemainingInMillis))
        );

        Radio.radioUi.lblTimePased.setText(timePassed);
        Radio.radioUi.lblTimeRemaining.setText(timeRemaining);
    }

    public void updateAlbumArt()
    {
        AlbumArtUrlGetter albumArtUrlGetter = new AlbumArtUrlGetter();
        try
        {
            albumArtUrlGetter.getAlbumArtUrlFromSong(Radio.currentSong);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
