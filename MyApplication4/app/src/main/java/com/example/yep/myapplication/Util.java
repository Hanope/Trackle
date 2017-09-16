package com.example.yep.myapplication;

/**
 * Created by yep on 2017. 9. 16..
 */

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.AsyncTask;
        import android.util.Log;

        import java.io.*;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * Created by its on 2017-01-10.
 */
public class Util extends AsyncTask<String, Void, String> {

    HttpURLConnection conn = null;
    DataOutputStream dos = null;
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    Context context;
    FileInputStream fileInputStream1, fileInputStream2, fileInputStream3;
    int maxBufferSize = 1 * 1024 * 1024;

    Util(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        Log.e("adatper", "pre");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Log.e("adatper", "post");
        super.onPostExecute(s);

        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private void writeFormField(String fieldName, String fieldValue) {
        try {
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"" + fieldName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.write(fieldValue.getBytes());
            dos.writeBytes(lineEnd);
        } catch (Exception e) {
            //System.out.println("AndroidUploader.writeFormField: got: " + e.getMessage());
            Log.e("ASDf", "AndroidUploader.writeFormField: " + e.getMessage());
        }
    }

    private void writeFileField(
            String fieldName,
            String fieldValue,
            FileInputStream fis) {
        try {
            // opening boundary line
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\""
                    + fieldName + "\";filename=\""
                    + fieldValue + "\"" + lineEnd);
            //dos.writeBytes("Content-Type: " + type +  lineEnd);
            dos.writeBytes(lineEnd);


            // create a buffer of  maximum size
            int bytesAvailable = fis.available();

            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            // read file and write it into form...
            int bytesRead = fis.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fis.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fis.read(buffer, 0, bufferSize);

            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
        } catch (Exception e) {
            //System.out.println("GeoPictureUploader.writeFormField: got: " + e.getMessage());
            Log.e("file", "AndroidUploader.writeFormField: got: " + e.getMessage());
        }
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            URL url = new URL(params[params.length - 1]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            dos = new DataOutputStream(conn.getOutputStream());


            if (params[params.length - 2].equals("main")) {

                dos.flush();
                dos.close();

                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json;

                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }

                return sb.toString().trim();
            }


            return "fail";

        } catch (Exception e) {
            Log.e("util", new String("Exception: " + e.getMessage()));
            return new String("Exception: " + e.getMessage());
        }

    }


}

