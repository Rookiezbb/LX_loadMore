package com.bawei.lx_loadmore;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bawei.lx_loadmore.presenter.Presenter;
import com.bawei.lx_loadmore.view.IView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView,SwipeRefreshLayout.OnRefreshListener {

    private String url = "http://news-at.zhihu.com/api/4/theme/";
    int page = 5;
    private Presenter presenter;
    private RecyclerView mRcv;
    private SwipeRefreshLayout mSrl;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new Presenter(this);
        presenter.loadList(url, page);
        getloadmore();
    }

    private void getloadmore() {
        mRcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItemPosition+1==myAdapter.getItemCount()){

                    page++;
                    presenter.loadList(url,page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    @Override
    public void showlist(List<Mybean.StoriesBean> sblist) {
       myAdapter.SetData(sblist,page);
    }

    private void initView() {
        mRcv = (RecyclerView) findViewById(R.id.rcv);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mSrl.setOnRefreshListener(MainActivity.this);
        myAdapter = new MyAdapter(MainActivity.this);
        mRcv.setAdapter(myAdapter);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRcv.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onRefresh() {
        final int pages = 6;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                presenter.loadList(url,pages);
                mSrl.setRefreshing(false);
                Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
            }
        },2000);
    }
}
