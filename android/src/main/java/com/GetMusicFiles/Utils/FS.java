package com.GetMusicFiles.Utils;

import android.graphics.Bitmap;
import android.util.Base64;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FS {
    static String saveImage(@NonNull String pathToImg, @NonNull Bitmap songImage) throws IOException {


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                songImage.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                byte[] imageByte = Base64.decode(encodedImage, Base64.DEFAULT);
                saveToStorage(pathToImg, imageByte);

                return pathToImg;

    }


    static void saveToStorage(@NonNull String pathToImg, @NonNull byte[] imageBytes) throws IOException {
        FileOutputStream fos = null;
        try {
            File filePath = new File(pathToImg);
            fos = new FileOutputStream(filePath, true);
            fos.write(imageBytes);
        } catch (IOException e){
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
