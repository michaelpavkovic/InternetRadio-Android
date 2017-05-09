package com.mpavkovic.internetradio.listeners;

import com.google.android.youtube.player.YouTubePlayer;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Player state change listener for SongView
 */
public class SongViewStateChangeListener implements YouTubePlayer.PlayerStateChangeListener
{
    @Override
    public void onLoading()
    {

    }

    @Override
    public void onLoaded(String s)
    {

    }

    @Override
    public void onAdStarted()
    {

    }

    @Override
    public void onVideoStarted()
    {

    }

    @Override
    public void onVideoEnded()
    {
        Radio.currentSong.setEnded(true);
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason)
    {

    }
}
