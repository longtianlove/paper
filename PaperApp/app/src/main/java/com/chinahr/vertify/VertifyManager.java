package com.chinahr.vertify;

import android.content.Context;

import com.chinahr.patch.Patch;

import java.io.File;

/**
 * Created by 58 on 2016/5/20.
 */
public class VertifyManager {
    Context mcontext;
    SecurtyChecker checker;
    Patch patch;
    public VertifyManager(Context context){
        mcontext=context;
        checker=new SecurtyChecker(mcontext);
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }

    public  boolean verifyApk(){
        File file=patch.getFile();
      return checker.verifyApk(file);
   }
}
