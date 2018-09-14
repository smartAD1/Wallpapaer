package com.example.wallpaper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wallpaper.Model.Photo;
import com.example.wallpaper.R;
import com.example.wallpaper.Utils.GlideApp;
import com.example.wallpaper.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/8/1.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private Context context;
    private List<Photo> photos;

    public PhotosAdapter(Context context , List<Photo> photos){
        this.context = context;
        this.photos = photos;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        //findViewById 部分
        @BindView(R.id.item_photo_user_avater)
        CircleImageView userAvatar;
        @BindView(R.id.item_photo_username)
        TextView username;
        @BindView(R.id.item_photo_photo)
        SquareImage photo;
        public ViewHolder(View itemView) {
            super(itemView);
            //用Butter Knife這個套件的使用方法注入
            ButterKnife.bind(this, itemView);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //取得itemphoto這個XML
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo,parent,false);
        // 返回一個新的ViewHolder
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.username.setText(photo.getUser().getUsername());
        GlideApp
                .with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600, 600)
                .into(holder.photo);

        GlideApp
                .with(context)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(holder.userAvatar);
//        //取得photo數量
//        Photo photo = photos.get(position);
//        //取得usernmae
//        String username = photo.getUser().getUsernmae();
//        //取得photo_url
//        String photo_url = photo.getUrl().getRegular();
//        //圖片位置
//        SquareImage holder_photo = holder.photo;
//        //顯示user的名字
//        holder.username.setText(username);
//
//        //Glide緩存類設定
////        RequestOptions requestOptions = new RequestOptions()
////                .placeholder(R.drawable.placeholder)
////                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .override(600,600);
////        GLIDE動畫設定
////        DrawableTransitionOptions transitionOptions = new DrawableTransitionOptions()
////                .crossFade();
////
////        GlideApp.with(context).load(photo_url)
////                .apply(requestOptions)
////                .transition(transitionOptions)
////                .into(holder_photo);
//        //會把網址圖片顯示出來,如果無法顯示會出現placeholder的這個圖片
//        GlideApp.with(context).load(photo.getUrl().getRegular())
//                .placeholder(R.drawable.placeholder)
//                .override(600,600)
//                .into(holder.photo);
//        Log.d("testAPi",photo.getUrl().toString());
//        Log.d("testAPI",photo.getUrl().getRegular().toString());
//        //顯示user 圖片
//        GlideApp.with(context)
//                .load(photo.getUser().getProfilesImage().getSmall())
//                .into(holder.userAvater);
    }

    @Override
    public int getItemCount() {
        //返回photos的數量
        return photos.size();
    }


}
