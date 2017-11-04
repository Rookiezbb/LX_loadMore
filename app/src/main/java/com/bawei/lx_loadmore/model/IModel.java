package com.bawei.lx_loadmore.model;

/**
 * Created by Zhang on 2017/10/25.
 */

public interface IModel {
    void RequestData(String url,int page,OnRequestLintener onRequestLintener);



}
