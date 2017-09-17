package com.example.yep.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yep.myapplication.model.article.ArticleResult;

import java.util.ArrayList;

/**
 * Created by yep on 2017. 9. 16..
 */

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.ViewHolder> {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList//
    private ArrayList<ArticleResult.Article> listViewItemList;
    private Context context;

    public Main_Adapter(Context context, ArrayList<ArticleResult.Article> item) {
        this.context = context;
        this.listViewItemList = item;
    }

    public void setData(ArrayList<ArticleResult.Article> listViewItemList){
        this.listViewItemList = listViewItemList;
    }
    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public Main_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a_main, viewGroup, false);
        return new Main_Adapter.ViewHolder(view, 1);
    }


    /**
     * listView getView 를 대체
     * 넘겨 받은 데이터를 화면에 출력하는 역할
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(final Main_Adapter.ViewHolder viewHolder, final int position) {
        viewHolder.title.setText(listViewItemList.get(position).getTitle());
        viewHolder.tag.setText(listViewItemList.get(position).getKeyword());
        Glide.with(context).load(listViewItemList.get(position).getImage()).asBitmap().into(viewHolder.image);

        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent article_intent = new Intent(context, ArticleActivity.class);
                article_intent.putExtra("article", listViewItemList.get(position));
                context.startActivity(article_intent);
            }
        });

        viewHolder.tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tag = viewHolder.tag;

                if(tag.getBackground().getConstantState().equals(
                        context.getResources().getDrawable(R.drawable.round_white_color).getConstantState())){
                    tag.setBackgroundResource(R.drawable.round_main_color);
                    tag.setTextColor(Color.parseColor("#fcfdff"));
                }
                else{
                    tag.setBackgroundResource(R.drawable.round_white_color);
                    tag.setTextColor(Color.parseColor("#4990e2"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listViewItemList.size() != 0) ? listViewItemList.size() : 0;
    }


    /**
     * 뷰 재활용을 위한 viewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView title, tag;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.main_photo);
            title = (TextView) itemView.findViewById(R.id.title);
            tag = (TextView) itemView.findViewById(R.id.tag);
        }
    }
}



