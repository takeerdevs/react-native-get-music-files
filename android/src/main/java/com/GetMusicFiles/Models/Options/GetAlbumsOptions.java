package com.GetMusicFiles.Models.Options;

import com.facebook.react.bridge.ReadableMap;

public class GetAlbumsOptions {
    public String artist;
    public String song;
    public int batchSize;
    public int batchNumber;
    public String sortBy;
    public boolean sortOrder;

    public GetAlbumsOptions(ReadableMap options) {
        this.artist = options.getString("artist");
        this.sortBy = options.getString("sortBy");
        this.sortOrder = options.getBoolean("sortOrder");
        this.song = options.getString("song");
        this.batchSize = options.getInt("batchSize");
        this.batchNumber = options.getInt("batchNumber");
    }
}
