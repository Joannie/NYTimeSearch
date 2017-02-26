package codepathbootcamp.nytimesearch.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by joanniehuang on 2017/2/22.
 */

public class Articles implements Serializable{

    String webUrl;
    String headline;
    String thumbnail;
    String begin_date

    public Articles(JSONObject jsonObject) throws JSONException {

        this.webUrl = jsonObject.getString("web_url");
        this.headline = jsonObject.getJSONObject("headline").getString("main");

        JSONArray multimedia = jsonObject.getJSONArray("multimedia");

        try{
            if (multimedia.length() > 0){
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbnail = "http://www.nytimes.com/" + multimediaJson.getString("url");
                Log.d("DEBUG", thumbnail.toString());
            }else{
                this.thumbnail = "";

            }
        }catch(JSONException e){
          e.printStackTrace();
        }

    }


    public static ArrayList<Articles> fromJSONArray(JSONArray array){
        ArrayList<Articles> resultsDocs = new ArrayList<>();

        for(int i=0; i<array.length(); i++){
            try {
                resultsDocs.add(new Articles(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultsDocs;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbnail() {
        return thumbnail;
    }

}
