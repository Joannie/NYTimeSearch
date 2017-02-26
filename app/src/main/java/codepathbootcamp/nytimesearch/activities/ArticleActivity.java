
package codepathbootcamp.nytimesearch.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import codepathbootcamp.nytimesearch.R;
import codepathbootcamp.nytimesearch.models.Articles;

public class ArticleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Articles article = (Articles) getIntent().getSerializableExtra("article");

        WebView wvArticle = (WebView) findViewById(R.id.wvArticle);

        wvArticle.setWebViewClient(new WebViewClient(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                view.loadUrl(url);
                return true;
            }
        });

        wvArticle.loadUrl(article.getWebUrl());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_filter:

                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
