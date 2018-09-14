package com.example.wallpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wallpaper.Model.Collection;
import com.example.wallpaper.Model.Photo;
import com.example.wallpaper.R;
import com.example.wallpaper.Utils.GlideApp;
import com.example.wallpaper.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/9/2.
 */

public class CollectionsAdapter extends BaseAdapter {
    private Context context;
    private List<Collection> collections;

    public CollectionsAdapter(Context context, List<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int i) {
        return collections.get(i).getId();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //取得item_collections的xml
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_collections, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //把下方ViewHolder class注入
        ButterKnife.bind(this, view);
        Collection collection = collections.get(i);
        //取得title
        if (collection.getTitle() != null) {
            holder.title.setText(collection.getTitle());
        }
        holder.totalphoto.setText(String.valueOf(collection.getCoverPhoto()) + "photos");
        //讀取圖片
        GlideApp.with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(holder.collectionphoto);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.item_collection_title)
        TextView title;
        @BindView(R.id.item_collection_total_photo)
        TextView totalphoto;
        @BindView(R.id.item_collection_photo)
        SquareImage collectionphoto;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
