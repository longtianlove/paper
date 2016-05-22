
package com.chinahr.file;

import android.content.Context;

import com.chinahr.download.DownloadManager;
import com.chinahr.patch.Patch;
import com.chinahr.vertify.VertifyManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by 58 on 2016/5/20.
 */
public class FileManager {
    public boolean isvertify = false;
    VertifyManager vertifyManager;
    DownloadManager downloadManager;
    Context mcontext;
    Patch apkPatch;

    FileManager(Context context) {
        mcontext = context;
        vertifyManager = new VertifyManager(mcontext);
        downloadManager = new DownloadManager();
    }

    public boolean vertify() {
        return vertifyManager.verifyApk();
    }

    public File getFile() {
        try {
            return downloadManager.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * copy file
     *
     * @param src  source file
     * @param dest target file
     * @throws IOException
     */
        public static void copyFile(File src, File dest) throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    /**
     * delete file
     *
     * @param file file
     * @return true if delete success
     */
    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }
        return file.delete();
    }
}
