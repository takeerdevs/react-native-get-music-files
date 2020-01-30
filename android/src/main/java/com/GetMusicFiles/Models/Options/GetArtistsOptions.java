package com.GetMusicFiles.Models.Options;

import com.facebook.react.bridge.ReadableMap;

public class GetArtistsOptions {
    public String album;
    public String song;
    public int batchSize;
    public int batchNumber;
    public String sortBy;
    public boolean sortOrder;

    public GetArtistsOptions(ReadableMap options) {
        this.album = options.hasKey("album") ? options.getString("album") : null;
        this.song = options.hasKey("song") ? options.getString("song") : null;
        this.batchSize = options.hasKey("batchSize") ? options.getInt("batchSize") : -1;
        this.batchNumber = options.hasKey("batchNumber") ? options.getInt("batchNumber") : -1;
        this.sortBy = options.hasKey("sortBy") ? options.getString("sortBy") : null;
        this.sortOrder = options.hasKey("sortOrder") && options.getBoolean("sortOrder");
    }
}
