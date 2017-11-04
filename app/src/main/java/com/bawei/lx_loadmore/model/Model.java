package com.bawei.lx_loadmore.model;

import com.bawei.lx_loadmore.MyOkhttp;
import com.bawei.lx_loadmore.Mybean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Zhang on 2017/10/25.
 */

public class Model implements IModel {
    @Override
    public void RequestData(String url, int page, final OnRequestLintener onRequestLintener) {

        MyOkhttp.getAsync(url + page, new MyOkhttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                onRequestLintener.OnError(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson g = new Gson();
                Mybean mybean = g.fromJson(result, Mybean.class);
                List<Mybean.StoriesBean> stories = mybean.getStories();
                onRequestLintener.OnSuccess(stories);
            }
        });
    }
}
