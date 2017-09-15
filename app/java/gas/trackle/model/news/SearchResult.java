package gas.trackle.model.news;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-09-16.
 */

public class SearchResult {

    String lastBuildDate;
    int total;
    int start;
    int display;

    ArrayList<Content> items;

    class Content{
        String title;
        String originallink;
        String link;
        String description;
        String pubDate;
    }

}
