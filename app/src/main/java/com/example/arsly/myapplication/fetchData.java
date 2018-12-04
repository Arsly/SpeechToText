package com.example.arsly.myapplication;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//AsyncTask：耗時工作設計
//參考教學：https://litotom.com/2016/03/26/asynctask%EF%BC%8D%E8%80%97%E6%99%82%E5%B7%A5%E4%BD%9C%E8%A8%AD%E8%A8%88/
public class fetchData extends AsyncTask<Void,Void,Void> {
    //存解析過的網頁原始碼
    String parse ="";
    //背景工作
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //url連線網址
            URL url = new URL("http://www.nhu.edu.tw/");
            //HttpURLConnection連線機制
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //輸入資料
            InputStream input = conn.getInputStream();
            //讀取輸入的資料
            BufferedReader Reader = new BufferedReader(new InputStreamReader(input));
            //line儲存每一行讀進來的資料
            String line = "";
            while(line != null){
                line = Reader.readLine();
                //parse存到全域變數
                parse = parse + line;
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //執行結果
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //傳資料到MainActivity介面裡的，TextView【data】標籤
        MainActivity.data.setText(this.parse);
    }
}
