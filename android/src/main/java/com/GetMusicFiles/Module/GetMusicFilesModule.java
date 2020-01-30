package com.GetMusicFiles.Module;

import android.content.ContentResolver;

import com.GetMusicFiles.Models.Options.SearchOptions;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.util.Objects;

import static com.GetMusicFiles.Methods.Search.searchDB;

public class GetMusicFilesModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public GetMusicFilesModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }


    @Override
    public String getName() {
        return "GetMusicFiles";
    }

    @ReactMethod
    public void getAll(ReadableMap options, Promise callback) {

    }

    @ReactMethod
    public void getSongsByPath(ReadableMap options, Promise callback) {

    }

    @ReactMethod
    public void getAlbums(ReadableMap options, Promise callback) {

    }

    @ReactMethod
    public void getArtists(ReadableMap options, Promise callback) {

    }

    @ReactMethod
    public void getSongs(ReadableMap options, Promise callback) {

    }

    @ReactMethod
    public void search(ReadableMap args, Promise callback) {
        try {
            SearchOptions options = new SearchOptions(args);
            ContentResolver contentResolver = Objects.requireNonNull(getCurrentActivity()).getContentResolver();
            WritableMap results = searchDB(options, contentResolver);
            callback.resolve(results);
        } catch (Exception e) {
            callback.reject(e);
        }

    }

    @ReactMethod
    public void query(ReadableMap options, Promise callback) {

    }

}
