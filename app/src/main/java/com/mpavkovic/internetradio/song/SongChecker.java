package com.mpavkovic.internetradio.song;

/**
 * Some methods used in making sure a title represents an actual song, and not an advertisement from SiriusXm
 */
public class SongChecker
{
    //Constructor
    public SongChecker()
    {

    }

    public boolean isSong(String artist, String title)
    {
        boolean isSong = true;

        if (title.matches("[sS]irius|[xX][mM]|#+|@+|[fF]acebook|[tT]witter"))
            isSong = false;
        else if (artist.matches("[sS]irius|[xX][mM]|#+|@+|[fF]acebook|[tT]witter"))
            isSong = false;

        return isSong;
    }
}
