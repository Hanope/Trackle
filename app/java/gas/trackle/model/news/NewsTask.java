package gas.trackle.model.news;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by seowo on 2017-09-16.
 */

public class NewsTask extends AsyncTask<Void, Void, Void> {
    String keyword;
    SearchResult result;

    public NewsTask(String keyword) {
    this.keyword = keyword;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        News news = new News();

        if(!keyword.isEmpty()) {
            news.setKeyword(keyword);

            try {
                JSONObject json = new JSONObject(news.search());
                result = new SearchResult();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
