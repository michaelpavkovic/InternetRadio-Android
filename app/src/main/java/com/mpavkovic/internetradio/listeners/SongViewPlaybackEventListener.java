package com.mpavkovic.internetradio.listeners;

import com.google.android.youtube.player.YouTubePlayer;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.ui.radio_ui.ChangeToPause;
import com.mpavkovic.internetradio.ui.radio_ui.ChangeToPlay;

/**
 * Class that listens for playback events in songView
 */
public class SongViewPlaybackEventListener implements YouTubePlayer.PlaybackEventListener
{
    @Override
    public void onPlaying()
    {
        Radio.currentSong.isPlaying = true;
        //Change the status of the play button depending on status of song
        Radio.radioHandler.post(new ChangeToPause());
    }

    @Override
    public void onPaused()
    {
        //Change the status of the play button depending on status of song
        Radio.playerInitialized = true;
        Radio.currentSong.isPlaying = false;
        Radio.radioHandler.post(new ChangeToPlay());
    }

    @Override
    public void onStopped()
    {
        Radio.currentSong.isPlaying = false;
    }

    @Override
    public void onBuffering(boolean b)
    {
        Radio.currentSong.isPlaying = false;
    }

    @Override
    public void onSeekTo(int i)
    {
        System.out.println("onSeekTo ran.");
    }
}
