package com.chinahr.download;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 58 on 2016/5/20.
 */
public class DownloadManager {
    OkHttpClient client;
    public DownloadManager() {
        client = new OkHttpClient();
    }

    private void download() {
//        //这个是非ui线程回调，不可直接操作UI
//        final ProgressResponseListener progressResponseListener = new ProgressResponseListener() {
//            @Override
//            public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
//                Log.e("TAG", "bytesRead:" + bytesRead);
//                Log.e("TAG", "contentLength:" + contentLength);
//                Log.e("TAG", "done:" + done);
//                if (contentLength != -1) {
//                    //长度未知的情况下回返回-1
//                    Log.e("TAG", (100 * bytesRead) / contentLength + "% done");
//                }
//                Log.e("TAG", "================================");
//            }
//        };


//        //这个是ui线程回调，可直接操作UI
//        final UIProgressResponseListener uiProgressResponseListener = new UIProgressResponseListener() {
//            @Override
//            public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
//                Log.e("TAG", "bytesRead:" + bytesRead);
//                Log.e("TAG", "contentLength:" + contentLength);
//                Log.e("TAG", "done:" + done);
//                if (contentLength != -1) {
//                    //长度未知的情况下回返回-1
//                    Log.e("TAG", (100 * bytesRead) / contentLength + "% done");
//                }
//                Log.e("TAG", "================================");
//                //ui层回调
////                downloadProgeress.setProgress((int) ((100 * bytesRead) / contentLength));
//                //Toast.makeText(getApplicationContext(), bytesRead + " " + contentLength + " " + done, Toast.LENGTH_LONG).show();
//            }
//        };

        //构造请求
        final Request request1 = new Request.Builder()
                .url("http://121.41.119.107:81/test/1.doc")
                .build();
        try {
            getFile();
        } catch (IOException e) {

        }
//        //包装Response使其支持进度回调
//        ProgressHelper.addProgressResponseListener(client, uiProgressResponseListener).newCall(request1).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.e("TAG", "error ", e);
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                Log.e("TAG", response.body().string());
//            }
//        });
    }

    public File getFile() throws IOException {
        File apkfile= new File(
                 File.separator + "test.jar");
//        HttpURLConnection connection = client.open(url);
        InputStream in = null;
        try {
            // Read the response.
//            in = connection.getInputStream();
//            byte[] response = readFully(in);
//            return new String(response, "UTF-8");
        } finally {
            if (in != null) in.close();
        }
        return apkfile;
    }
}
