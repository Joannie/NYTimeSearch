package codepathbootcamp.nytimesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codepathbootcamp.nytimesearch.ArticleArrayAdapter;
import codepathbootcamp.nytimesearch.R;
import codepathbootcamp.nytimesearch.models.Articles;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    EditText etQuery;
    Button btnButton;
    ArrayList<Articles> articlesList;
    ArticleArrayAdapter adapter;
    StaggeredGridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();

    }

    public void setupViews(){
        etQuery = (EditText) findViewById(R.id.etSearch);
        btnButton = (Button) findViewById(R.id.btnSearch);
        articlesList = new ArrayList<>();
        adapter = new ArticleArrayAdapter(this, articlesList);
        recyclerView = (RecyclerView) findViewById(R.id.rvArticles);
        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        //Setup the gridLayoutManager with RecyclerView
        //gridLayoutManager.item
        /*gridLayoutManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);
                Articles articles = articlesList.get(position);
                i.putExtra("article", articles);

                startActivity(i);
            }
        })*/

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onArticleSearch(View view) {
        String query = etQuery.getText().toString();
        Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();;
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("api-key", "61fe7be1aae74d12a977cc92c8255830");
        params.put("q", query);
        params.put("begin_date",);
        params.put("sort");
        params.put("fq", );

        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try{
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");

                    //adapter.addAll(Articles.fromJSONArray(articleJsonResults));
                    //Log.d("DEBUG", articlesList.toString());

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });




    }
}
