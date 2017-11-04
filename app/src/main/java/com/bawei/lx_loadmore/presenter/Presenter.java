package com.bawei.lx_loadmore.presenter;

import com.bawei.lx_loadmore.Mybean;
import com.bawei.lx_loadmore.model.IModel;
import com.bawei.lx_loadmore.model.Model;
import com.bawei.lx_loadmore.model.OnRequestLintener;
import com.bawei.lx_loadmore.view.IView;

import java.util.List;

/**
 * Created by Zhang on 2017/10/25.
 */

public class Presenter implements IPresenter {
     IView iView;
     IModel iModel;

    public Presenter(IView iView) {
        this.iView = iView;
        iModel = new Model();
    }

    @Override
    public void loadList(String url, int page) {
       iModel.RequestData(url, page, new OnRequestLintener() {
           @Override
           public void OnSuccess(List<Mybean.StoriesBean> sblist) {
               iView.showlist(sblist);
           }

           @Override
           public void OnError(Exception e) {

           }
       });
    }
}
