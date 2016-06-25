package com.chinahr.patch;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.chinahr.along.paperApp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 58 on 2016/5/19.
 */
public class PatchApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        dosomething();
    }
    public void dosomething() {
        final File pathFile = new File(getFilesDir()
                + File.separator + "test.jar");
        final File optFile = new File(getFilesDir().getAbsolutePath());
        if (!pathFile.exists()) {
            try {
                pathFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.test);
            FileOutputStream outputStream = new FileOutputStream(pathFile);
            int result;
            while ((result = inputStream.read()) != -1) {
                outputStream.write(result);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            PatchManager patchManager = PatchManager.getInstance();
            patchManager.init(optFile.getAbsolutePath(), this);
        } catch (Exception e) {
            Log.e("longlonglong", e.toString() + "***********");
        }
    }
}
