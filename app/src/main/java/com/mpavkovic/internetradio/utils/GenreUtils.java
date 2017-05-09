package com.mpavkovic.internetradio.utils;

import com.mpavkovic.internetradio.utils.Genres.genres;

/**
 * Some accessory methods useful for dealing with genres
 */
public class GenreUtils
{
    //Constructor
    public GenreUtils()
    {

    }

    public int getSiriusXmChannelNumber(genres genre)
    {
        int channel = -1;

        switch (genre)
        {
            case TRENGING_2: channel = 2;
                break;
            case POP_DANCE_3: channel = 3;
                break;
            case ACOUSTIC_14: channel = 14;
                break;
            case POP_15: channel = 15;
                break;
            case CLASSIC_ROCK_25: channel = 25;
                break;
            case ROCK_28: channel = 28;
                break;
            case HEAVY_METAL_40: channel = 40;
                break;
            case ALTERNATIVE_ROCK_36: channel = 36;
                break;
            case HIP_HOP_44: channel = 44;
                break;
            case R_AND_B_46: channel = 46;
                break;
            case R_AND_B_OLD_47: channel = 47;
                break;
            case UPBEAT_51: channel = 51;
                break;
            case ELECTRONIC_52: channel = 52;
                break;
            case DEEP_HOUSE_CHILL_53: channel = 53;
                break;
            case DISCO_54: channel = 54;
                break;
            case COUNTRY_56: channel = 56;
                break;
            case DANCE_55: channel = 55;
                break;
            case CHRISTIAN_ROCK_63: channel = 63;
                break;
            case JAZZ_68: channel = 68;
                break;
            case CLASSICAL_76: channel = 76;
                break;
        }
        return channel;
    }
}
