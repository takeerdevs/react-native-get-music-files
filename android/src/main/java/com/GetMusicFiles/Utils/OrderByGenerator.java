package com.GetMusicFiles.Utils;

import com.GetMusicFiles.C;

public class OrderByGenerator {
    public static String generateSortOrder(C.SortBy sortBy, C.SortOrder sortOrder) {
        return (sortBy.getValue() + " " + sortOrder.getValue());
    }
}
