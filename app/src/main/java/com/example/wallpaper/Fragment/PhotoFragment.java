package com.example.wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.wallpaper.Adapter.PhotosAdapter;
import com.example.wallpaper.Model.Photo;
import com.example.wallpaper.R;
import com.example.wallpaper.WebScrvices.ApiInterface;
import com.example.wallpaper.WebScrvices.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2018/7/28.
 */

public class PhotoFragment extends Fragment {
    private final String TAG = PhotoFragment.class.getSimpleName();
    @BindView(R.id.fragment_photo_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_photo_recyclerview)
    RecyclerView recyclerView;

    private PhotosAdapter photosAdapter;
    private Unbinder unbinder;
    private List<Photo> photos = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        unbinder = ButterKnife.bind(this, view);

        //RECYCLERIVEW部分
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        photosAdapter = new PhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(photosAdapter);
        Log.d("testApi","testAPI_PhotoFragment");
        showProgressBar(true);
        getPhotos();
        return view;
    }

    private void getPhotos() {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Photo>> call = apiInterface.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Loading successfully, size: " + response.body().size());
                    photos.addAll(response.body());
//                    for (Photo photo : response.body()) {
//                        photos.add(photo);
//                        Log.d(TAG, photo.getUrl().getFull());
//                    }
                    photosAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "fial" + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d(TAG, "fial" + t.getMessage());
                showProgressBar(false);
            }
        });
    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
