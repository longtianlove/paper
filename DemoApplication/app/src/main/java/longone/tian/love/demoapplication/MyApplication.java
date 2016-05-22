package longone.tian.love.demoapplication;

import android.app.Application;
import android.util.Log;

import com.chinahr.patch.PatchManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 58 on 2016/5/19.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
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
        PatchManager patchManager=PatchManager.getInstance();
        patchManager.init(optFile.getAbsolutePath(), this);
        } catch (Exception e) {
            Log.e("longlonglong", e.toString() + "***********");
        }
    }
}
