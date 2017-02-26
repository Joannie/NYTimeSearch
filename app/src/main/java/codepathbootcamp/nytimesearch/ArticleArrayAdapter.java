package codepathbootcamp.nytimesearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import codepathbootcamp.nytimesearch.models.Articles;

/**
 * Created by joanniehuang on 2017/2/22.
 */

public class ArticleArrayAdapter extends RecyclerView.Adapter<ArticleArrayAdapter.ViewHolder>{

    private Context context;
    private Articles data;
    private ArrayList<Articles> articles;
    private static OnItemClickListener listener;

    public ArticleArrayAdapter(Context context, ArrayList<Articles> articles) {
        this.articles = articles;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemText;
        public ImageView itemImage;

        public ViewHolder(final View itemView){
            super(itemView);

            itemText = (TextView) itemView.findViewById(R.id.itemText);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public ArticleArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article_result, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Articles mArticles = articles.get(position);
        holder.itemImage.setImageResource(0);
        holder.itemText.setText(mArticles.getHeadline());

        String thumbnail = mArticles.getThumbnail();
        if(!TextUtils.isEmpty(thumbnail)){
            Picasso.with(context).load(thumbnail).into(holder.itemImage);
        }

        JSONArray articleJsonResults = null;
        articles.addAll(Articles.fromJSONArray(articleJsonResults));
    }

    @Override
    public void onBindViewHolder(ArticleArrayAdapter.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }



}
