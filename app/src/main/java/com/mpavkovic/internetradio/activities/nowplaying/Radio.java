package com.mpavkovic.internetradio.activities.nowplaying;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.listeners.RadioOnClickListener;
import com.mpavkovic.internetradio.listeners.RadioOnSeekBarChangeListener;
import com.mpavkovic.internetradio.listeners.SongViewPlaybackEventListener;
import com.mpavkovic.internetradio.listeners.SongViewStateChangeListener;
import com.mpavkovic.internetradio.song.Song;
import com.mpavkovic.internetradio.song.SongManager;
import com.mpavkovic.internetradio.ui.radio_ui.RadioUI;
import com.mpavkovic.internetradio.utils.Constants;
import com.mpavkovic.internetradio.utils.GenreUtils;
import com.mpavkovic.internetradio.utils.Genres.genres;
import com.mpavkovic.internetradio.utils.SongTimer;

public class Radio extends AppCompatActivity implements YouTubePlayer.OnInitializedListener
{
    public static RadioUI radioUi;
    static RadioOnClickListener listener;
    static Thread timerThread;
    public static Handler radioHandler;
    public static YouTubePlayer player;
    public static Song currentSong;
    public static genres selectedGenre;
    public static GenreUtils genreUtils;
    public static Context radioContext;
    public static SongTimer timer;
    public static SongManager songManager;
    public static boolean playerInitialized = false, skippedBack = false, stopTimer = false;
    public static boolean pauseClicked = false;

    public static int currentIndex = -1, currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        radioContext = getApplicationContext();

        radioUi = new RadioUI();
        listener = new RadioOnClickListener();

        radioHandler = new Handler();

        genreUtils = new GenreUtils();

        //Initialize dummy song
        currentSong = new Song();

        //Initialize timer
        timer = new SongTimer();
        timerThread = new Thread(timer);
        timerThread.start();

        //Initialize YouTube player
        radioUi.songView = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.songView);
        radioUi.songView.initialize(Constants.YOUTUBE_API_KEY_ANDROID, this);

        radioUi.fragmentManager = getFragmentManager();
        radioUi.fragmentTransaction = radioUi.fragmentManager.beginTransaction();
        radioUi.fragmentTransaction.replace(R.id.songView, radioUi.songView);
        radioUi.fragmentTransaction.commit();

        //Initialize Extra Controls Layout
        radioUi.extraControls = (RelativeLayout) findViewById(R.id.extraControls);

        //Initialize buttons
        radioUi.imageButtonShow = (ImageButton) findViewById(R.id.imageButtonShow);
        radioUi.imageButtonShow.setOnClickListener(listener);

        radioUi.imageButtonHide = (ImageButton) findViewById(R.id.imageButtonHide);
        radioUi.imageButtonHide.setOnClickListener(listener);

        radioUi.imageButtonPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        radioUi.imageButtonPlay.setOnClickListener(listener);

        radioUi.imageButtonSkipPrevious = (ImageButton) findViewById(R.id.imageButtonSkipPrevious);
        radioUi.imageButtonSkipPrevious.setOnClickListener(listener);

        radioUi.imageButtonSkipNext = (ImageButton) findViewById(R.id.imageButtonSkipNext);
        radioUi.imageButtonSkipNext.setOnClickListener(listener);

        //Initialize SeekBars
        radioUi.seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);
        radioUi.seekBarVolume.setOnSeekBarChangeListener(new RadioOnSeekBarChangeListener());

        radioUi.seekBarSeek = (SeekBar) findViewById(R.id.seekBarSeek);
        radioUi.seekBarSeek.setOnSeekBarChangeListener(new RadioOnSeekBarChangeListener());

        //Initialize TextViews
        radioUi.lblTimePased = (TextView) findViewById(R.id.lblTimePassed);
        radioUi.lblTimeRemaining = (TextView) findViewById(R.id.lblTimeRemaining);
        radioUi.lblMetadata = (TextView) findViewById(R.id.lblMetadata);

        //Initialize AudioManager
        radioUi.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        radioUi.setInitialVolumeSeekBarPosition();

        //Show genre selector
        showGenreSelector();

        System.out.println("[UI] UI for Radio was successfully created.");
    }

    //YouTubePlayer methods
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored)
    {
        if (!wasRestored)
        {
            System.out.println("[Song Playback] YouTube connection successful.");

            player = youTubePlayer;
            playerInitialized = true;
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            player.setPlaybackEventListener(new SongViewPlaybackEventListener());
            player.setPlayerStateChangeListener(new SongViewStateChangeListener());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
    {
        System.out.println("[Song Playback] YouTube connection failed.");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        //Stop the song timer
        stopTimer = true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    //Extra methods

    //Method that shows Genre Selector
    public void showGenreSelector()
    {

        //Testing
        selectedGenre = genres.DEEP_HOUSE_CHILL_53;
    }
}
