package com.bawei.lx_loadmore.model;

import com.bawei.lx_loadmore.Mybean;

import java.util.List;

/**
 * Created by Zhang on 2017/10/25.
 */

public interface OnRequestLintener {
    void OnSuccess(List<Mybean.StoriesBean>sblist);
    void OnError(Exception e);
}
