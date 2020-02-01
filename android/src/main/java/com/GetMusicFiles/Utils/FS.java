package com.GetMusicFiles.Utils;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FS {

    public static void saveToStorage(@NonNull String pathToImg, @NonNull byte[] imageBytes) throws IOException {

        FileOutputStream fos = null;
        try {
            File filePath = new File(pathToImg);
            fos = new FileOutputStream(filePath, false);
            fos.write(imageBytes);
        } catch (IOException e) {
            if (fos != null) {
                fos.flush();
                fos.close();
            }
            throw new IOException(e);
        } finally {
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
    }
}
