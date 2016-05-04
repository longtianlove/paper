package com.chinahr.along.paperApp;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by long on 2016/5/4.
 */
public class MyApplication extends Application {
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
            DexClassLoader cl = new DexClassLoader(pathFile.getAbsolutePath(),
                    optFile.getAbsolutePath(), null, getClassLoader().getParent());
            Class newClazz = cl.loadClass("com.chinahr.along.paperApp.dynamic.IDynamicDemo");
            Object o1 = newClazz.newInstance();
            Method method1 = newClazz.getMethod("hello", null);
            Log.e("long--new", method1.invoke(o1, null) + "***********");
            Object baseDexElements = getDexElements(getPathList(getClassLoader()));
            Object newDexElements = getDexElements(getPathList(cl));
            Object allDexElements = combineArray(newDexElements, baseDexElements);
            Object pathList = getPathList(getClassLoader());
            ReflectionUtils.setField(pathList, pathList.getClass(), "dexElements", allDexElements);
        } catch (Exception e) {
            Log.e("longlonglong", e.toString() + "***********");
        }
    }
    private static Object getDexElements(Object paramObject)
            throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        return ReflectionUtils.getField(paramObject, paramObject.getClass(), "dexElements");
    }
    private static Object getPathList(Object baseDexClassLoader)
            throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        return ReflectionUtils.getField(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }
    private static Object combineArray(Object firstArray, Object secondArray) {
        Class<?> localClass = firstArray.getClass().getComponentType();
        int firstArrayLength = Array.getLength(firstArray);
        int allLength = firstArrayLength + Array.getLength(secondArray);
        Object result = Array.newInstance(localClass, allLength);
        for (int k = 0; k < allLength; ++k) {
            if (k < firstArrayLength) {
                Array.set(result, k, Array.get(firstArray, k));
            } else {
                Array.set(result, k, Array.get(secondArray, k - firstArrayLength));
            }
        }
        return result;
    }
}
