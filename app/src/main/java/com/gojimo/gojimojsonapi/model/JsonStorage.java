package com.gojimo.gojimojsonapi.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * JsonStorage for storing json string in internal memory
 * Created by RouhAlavi
 */
public class JsonStorage {
    public static void writeFileInternalStorage(String strWrite, Context context,String fileName)
    {
        try
        {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write(strWrite.getBytes());
            fos.flush();
            fos.close();

        }
        catch (Exception e)
        {
            // Your Code
        }
    }

    public static String readFileInternalStorage(String fileName, Context context)
    {
        String stringToReturn = "";
        try {

            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                stringToReturn = stringBuilder.toString();
            }

        }
        catch (FileNotFoundException e)
        {
            Log.e("TAG", "File not found: " + e.toString());
        }
        catch (IOException e)
        {
            Log.e("TAG", "Can not read file: " + e.toString());
        }

        return stringToReturn;
    }

    public static boolean isSdReadable() {

        boolean mExternalStorageAvailable = false;
        try {
            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                mExternalStorageAvailable = true;
                Log.i("isSdReadable", "External storage card is readable.");
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                // We can only read the media
                Log.i("isSdReadable", "External storage card is readable.");
                mExternalStorageAvailable = true;
            } else {
                // Something else is wrong. It may be one of many other
                // states, but all we need to know is we can neither read nor
                // write
                mExternalStorageAvailable = false;
            }
        } catch (Exception ex) {

        }
        return mExternalStorageAvailable;
    }

}
