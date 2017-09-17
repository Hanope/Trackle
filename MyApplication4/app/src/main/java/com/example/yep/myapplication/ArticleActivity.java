package com.example.yep.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yep.myapplication.model.article.ArticleResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {
    TextView article_provider;
    TextView article_title;

    ImageView article_image;
    TextView article_text;

    TextView article_reporter;
    TextView article_date;

    LinearLayout tag_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ArticleResult.Article article = (ArticleResult.Article) getIntent().getSerializableExtra("article");

        article_provider = (TextView)findViewById(R.id.article_provider);
        article_title = (TextView)findViewById(R.id.article_title);

        article_image = (ImageView)findViewById(R.id.article_content_img);
        article_text = (TextView)findViewById(R.id.article_content_txt);

        article_reporter = (TextView)findViewById(R.id.article_reporter);
        article_date = (TextView)findViewById(R.id.article_date);

        tag_layout = (LinearLayout)findViewById(R.id.article_tag_layout);

        article_provider.setText(article.company);
        article_title.setText(article.title);

        final ArrayList<String> article_name = new ArrayList<>();
        article_name.add(article.name);

        Picasso.with(getApplicationContext()).load(article.image).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                article_image.setImageBitmap(bitmap);

                Display display = getWindowManager().getDefaultDisplay();
                Log.i("content", article_name.get(0));
                ArticleTextHelper.tryFlowText(article_name.get(0), width, height, article_text, display, 10);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });

        article_reporter.setText(article.author);
        article_date.setText(article.date);

    }
}
