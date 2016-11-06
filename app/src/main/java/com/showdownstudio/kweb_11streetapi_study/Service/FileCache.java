package com.showdownstudio.kweb_11streetapi_study.Service;

/**
 * Created by bjh970913 on 07/11/2016 - 00:28
 */

import android.content.Context;
import android.util.Log;

import java.io.File;

class FileCache {

    private File cacheDir;

    FileCache(Context context) {
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "LazyList");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists()) {
            if (!cacheDir.mkdirs()) {
                Log.i("Perms", "No file permisions");
            }
        }
    }

    File getFile(String url) {
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename = String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        return new File(cacheDir, filename);

    }

    void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files) {
            if (!f.delete()) {
                Log.i("Perms", "No file permisions");
            }
        }

    }

}