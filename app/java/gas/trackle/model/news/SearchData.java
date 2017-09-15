package gas.trackle.model.news;

/**
 * Created by seowo on 2017-09-16.
 */

public class SearchData {
    String query;
    String display;
    String start;
    String sort;

    public SearchData(String query, String display, String start, String sort) {
        this.query = query;
        this.display = display;
        this.start = start;
        this.sort = sort;
    }
}
