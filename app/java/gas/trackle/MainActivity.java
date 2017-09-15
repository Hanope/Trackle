package gas.trackle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import gas.trackle.application.Trackle;
import gas.trackle.model.NetworkService;
import gas.trackle.model.news.NewsTask;

public class MainActivity extends AppCompatActivity {
    NetworkService service;

    public MainActivity(NetworkService service) {
        service = Trackle.getInstance().getNetworkService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = Trackle.getInstance().getPreferences();
        new NewsTask("apple").execute();
    }
}
