package gas.trackle.model;

import gas.trackle.model.news.SearchResult;
import retrofit2.http.GET;

/**
 * Created by seowo on 2017-09-15.
 */

public interface NetworkService {
    @GET()
    SearchResult getResult();
}
