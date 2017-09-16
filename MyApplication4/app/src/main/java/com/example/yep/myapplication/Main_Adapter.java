package com.example.yep.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by yep on 2017. 9. 16..
 */

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.ViewHolder> {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList//
    private ArrayList<Main_Item> listViewItemList;
    private Context context;


    public Main_Adapter(ArrayList<Main_Item> item, Context context) {
        this.context = context;
        this.listViewItemList = item;
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
        viewHolder.tag.setText(listViewItemList.get(position).getTag());
        Glide.with(context).load(listViewItemList.get(position).getImage()).asBitmap().into(viewHolder.image);
    }


    @Override
    public int getItemCount() {
        return (null != listViewItemList ? listViewItemList.size() : 0);
    }


    /**
     * 뷰 재활용을 위한 viewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView title, tag;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            tag = (TextView) itemView.findViewById(R.id.tag);
        }
    }
}



