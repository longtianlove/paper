package com.chinahr.patch;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;

/**
 * Created by dragon on 2016/5/5.
 */
public class PatchManager {
    private static PatchManager managerInstance = new PatchManager();

    public static PatchManager getInstance() {
        return managerInstance;
    }

    public void init(String outputPath, Context context) {
        try {
            DexClassLoader cl = new DexClassLoader(outputPath + File.separator + "test.jar",
                    outputPath, null, context.getClassLoader().getParent());
//            Log.e("longtianlove",cl.toString());
//            Log.e("longtianlove",context.getClassLoader().toString());
            Object baseDexElements = getDexElements(getPathList(context.getClassLoader()));
            Object newDexElements = getDexElements(getPathList(cl));
            Object allDexElements = combineArray(newDexElements, baseDexElements);
            Object pathList = getPathList(context.getClassLoader());
            ReflectionUtils.setField(pathList, pathList.getClass(), "dexElements", allDexElements);
        } catch (Exception e) {
            Log.e("dragon---patch", e.toString() + "***********");
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

    private PatchManager() {
    }
}
