package com.chinahr.along.paperApp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.chinahr.patch.PatchApplication;
import com.chinahr.patch.PatchManager;
import com.chinahr.patch.ReflectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;

/**
 * Created by long on 2016/5/4.
 */
public class MyApplication extends PatchApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
