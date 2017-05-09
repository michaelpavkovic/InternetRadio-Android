package com.mpavkovic.internetradio.song;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.utils.Constants;

/**
 * This class handles the playing of songs off of YouTube, and specifies a data type to store song metadata
 */
public class Song
{
    private String songArtist, songName, songGenre, songYouTubeVideoId;
    public boolean isPlaying = false, isEnded = false, statusChange = false, firstPlay = true;

    public void setSongArtist(String artist)
    {
        this.songArtist = artist;
    }
    public String getSongArtist()
    {
        return songArtist;
    }

    public void setSongName(String name)
    {
        this.songName = name;
    }
    public String getSongName()
    {
        return this.songName;
    }

    public void setSongGenre(String genre)
    {
        this.songGenre = genre;
    }
    public String getSongGenre()
    {
        return songGenre;
    }

    public void setSongYouTubeVideoId(String youTubeVideoId)
    {
        this.songYouTubeVideoId = youTubeVideoId;
    }
    public String getSongYouTubeVideoId()
    {
        return songYouTubeVideoId;
    }

    public void play()
    {
        if (firstPlay)
        {
            System.out.println("[Song Playback] Playing Song from Video: " + songYouTubeVideoId);

            Radio.player.loadVideo(songYouTubeVideoId);
            Radio.player.play();
            firstPlay = false;
        }
        else
        {
            System.out.println("[Song Playback] Resuming Song from Video: " + songYouTubeVideoId);

            if (!Radio.pauseClicked)
            {
                //If the video paused for a reason other than the user clicking pause, relaod the video and seek to where they were
                Radio.player.loadVideo(songYouTubeVideoId);
                Radio.player.play();
                Radio.player.seekToMillis(Radio.currentTime);

                Radio.pauseClicked = true;
            }
            else Radio.player.play();
        }
    }

    public void pause()
    {
        if (isPlaying)
        {
            Radio.player.pause();
        }
    }

    public boolean isPlaying()
    {
        isPlaying = false;

        try
        {
            if (Radio.playerInitialized) isPlaying = Radio.player.isPlaying();
        }
        catch (IllegalStateException e)
        {
            System.out.println("[Song Playback] isPlaying could not run because the player was released.");
        }

        return isPlaying;
    }

    public void clearPlays()
    {
        firstPlay = true;
        isEnded = false;
        isPlaying = false;
    }

    public void setEnded(boolean isEnded)
    {
        this.isEnded = isEnded;
    }

    public boolean isEnded()
    {
        return isEnded;
    }

    public boolean hasTitle()
    {
        if (this.songName.equals(Constants.title_univ))
        {
            return false;
        }
        else
        {
            return true;
        }
    }


}
