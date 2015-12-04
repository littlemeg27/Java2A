/**
 * Created by Brenna Pavlinchak on 11/27/15.
 */

package com.example.ravenmargret.java2project1;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SongUtil
{
    public static void save(ArrayList<Song> songs, Context context)
    {
        try
        {
            FileOutputStream fileOut = context.openFileOutput("test.txt", Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(songs);
            objectOut.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Song> load(Context context)
    {
        //ArrayList<Song> object = new ArrayList<Song>();
        try
        {
            FileInputStream fileIn = context.openFileInput("test.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ArrayList<Song> savedSong = (ArrayList<Song>)objectIn.readObject();
            objectIn.close();
            return savedSong;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
