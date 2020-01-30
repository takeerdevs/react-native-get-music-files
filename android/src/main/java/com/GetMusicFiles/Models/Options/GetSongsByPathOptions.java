package com.GetMusicFiles.Models.Options;

import android.net.Uri;

import com.facebook.react.bridge.ReadableMap;

public class GetSongsByPathOptions {
    public Uri path;
    public boolean blurred;
    public boolean cover;
    public Uri coverFolder;
    public int minimumSongDuration;
    public int batchSize;
    public int batchNumber;
    public String sortBy;
    public boolean sortOrder;

    public GetSongsByPathOptions(ReadableMap options) {
        this.path = options.hasKey("path") ? Uri.parse(options.getString("path")) : null;
        this.blurred = options.hasKey("blurred") && options.getBoolean("blurred");
        this.cover = options.hasKey("cover") && options.getBoolean("cover");
        this.coverFolder = options.hasKey("coverFolder") ? Uri.parse(options.getString("coverFolder")) : null;
        this.minimumSongDuration = options.hasKey("minimumSongDuration") ? options.getInt("minimumSongDuration") : 0;
        this.batchSize = options.hasKey("batchSize") ? options.getInt("batchSize") : -1;
        this.batchNumber = options.hasKey("batchNumber") ? options.getInt("batchNumber") : -1;
        this.sortBy = options.hasKey("sortBy") ? options.getString("sortBy") : null;
        this.sortOrder = options.hasKey("sortOrder") && options.getBoolean("sortOrder");
    }
}
