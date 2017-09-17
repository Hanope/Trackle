package com.example.yep.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yep.myapplication.model.MyPageResult;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yep on 2017. 9. 16..
 */

public class My_Page_Adapter extends RecyclerView.Adapter<My_Page_Adapter.ViewHolder> {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList//
    private ArrayList<MyPageResult.History> listViewItemList;
    private Context context;


    public My_Page_Adapter(ArrayList<MyPageResult.History> item, Context context) {
        this.context = context;
        this.listViewItemList = item;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public My_Page_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a_my_page, viewGroup, false);
        return new My_Page_Adapter.ViewHolder(view, 1);
    }


    /**
     * listView getView 를 대체
     * 넘겨 받은 데이터를 화면에 출력하는 역할
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(final My_Page_Adapter.ViewHolder viewHolder, final int position) {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date day = desiredFormat.parse(listViewItemList.get(position).getDate());
            Log.i("date_a", day.toString());
            viewHolder.date.setText(format.format(day));
        } catch (ParseException e) {
            e.printStackTrace();
            viewHolder.date.setText("날 짜");
        }
        viewHolder.title.setText(listViewItemList.get(position).getTitle());

    }


    @Override
    public int getItemCount() {
        return (listViewItemList.size() != 0) ? listViewItemList.size() : 0;
    }


    /**
     * 뷰 재활용을 위한 viewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView title, date;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.page_title);
            date = (TextView)itemView.findViewById(R.id.page_date);
        }
    }
}




