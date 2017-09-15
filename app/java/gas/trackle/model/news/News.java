package gas.trackle.model.news;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import gas.trackle.application.Trackle;
import retrofit2.Retrofit;

/**
 * Created by seowo on 2017-09-16.
 */

public class News {
    String keyword;

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    public String search() {
        String clientId = "TudzNF44yZepUN3gkeQK";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "7dLV8dxBPi";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = Trackle.naverUrl + text; // json 결과

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
            return new String("검색 결과가 없습니다.");
        }
    }
}
