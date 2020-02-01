package com.GetMusicFiles.Methods;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.util.Log;

import com.GetMusicFiles.C;
import com.GetMusicFiles.Models.Options.GetAllOptions;
import com.GetMusicFiles.Utils.FS;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.GetMusicFiles.Utils.GeneralUtils.LOG;
import static com.GetMusicFiles.Utils.OrderByGenerator.generateSortOrder;

public class GetAll {

    public static WritableMap getAllSongs(GetAllOptions options, ContentResolver contentResolver) throws Exception {

        WritableArray jsonArray = new WritableNativeArray();
        String[] projection = new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID};


        String Selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";

        if (options.minimumSongDuration > 0) {
            Selection += " AND " + MediaStore.Audio.Media.DURATION + " >= " + options.minimumSongDuration;
        }

        String orderBy = null;

        if (options.sortBy != null) {
            orderBy = generateSortOrder(options.sortBy, options.sortOrder);
        }

        if(options.cover){

            File f = new File(options.coverFolder);

            if(f.exists() && !f.isDirectory()){
                Log.e(LOG, "coverPath is a file");
                throw new IOException("coverPath is a file");
            }
            if(!f.exists()){
                f.mkdir();
                Log.d(LOG, "coverFolder created");
            }

        }
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, Selection, null, orderBy);

        int cursorCount = Objects.requireNonNull(cursor).getCount();

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

        if (cursorCount > (options.batchSize * options.batchNumber)) {
            cursor.moveToPosition(options.batchSize * options.batchNumber);
            do {
                String path = cursor.getString(4);

                String CoverPath =  options.coverFolder + cursor.getString(5);

                WritableMap item = new WritableNativeMap();
                item.putString("title", cursor.getString(0));
                item.putString("artist", cursor.getString(1));
                item.putString("album", cursor.getString(2));
                item.putString("duration", cursor.getString(3));
                item.putString("path", cursor.getString(4));
                item.putString("id", cursor.getString(5));

                if (options.cover) {
                    mmr.setDataSource(path);
                    try {
                        byte[] albumImageData = mmr.getEmbeddedPicture();
                        FS.saveToStorage(CoverPath, albumImageData);
                        Log.e(LOG, "File saved");
                        item.putString("cover", CoverPath);
                    }catch (Exception e){
                        Log.e(LOG, String.valueOf(e));
                        item.putString("cover", "");
                    }
                }
                jsonArray.pushMap(item);
            } while ((options.batchSize == 0 || cursor.getPosition() + 1 < options.batchSize * (options.batchNumber + 1)) & cursor.moveToNext());
        } else {

            if (cursor != null || cursorCount == 0) {
                cursor.close();
                throw new Exception(C.ErrorEnum.EMPTY_CURSOR.toString());
            }
            cursor.close();
            String msg = "cursor is either null or empty ";
            Log.e(LOG, msg);
            throw new Exception(C.ErrorEnum.INDEX_OUT_OF_BOUND.toString());
        }
        cursor.close();
        WritableMap results = new WritableNativeMap();
        results.putInt("length", cursorCount);
        results.putArray("results", jsonArray);
        return results;
    }

}
