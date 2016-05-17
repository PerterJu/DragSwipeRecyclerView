package jx.czh.com.drawswiperecyclerview.s.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jx.czh.com.drawswiperecyclerview.R;
import jx.czh.com.drawswiperecyclerview.s.adapters.DragSwipeListAdapter;
import jx.czh.com.drawswiperecyclerview.s.interfaces.DividerItemDecoration;
import jx.czh.com.drawswiperecyclerview.s.interfaces.DraySwipeTouchCallBack;
import jx.czh.com.drawswiperecyclerview.s.interfaces.onItemClickListener;
import jx.czh.com.drawswiperecyclerview.s.interfaces.startDragListener;

public class MainActivity extends FragmentActivity implements startDragListener {

    RecyclerView drag_swipe_recyclerView;
    ItemTouchHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //构造虚拟数据
        List<String> data = new ArrayList<String>();
        for (char i= 'A'; i<='Z'; ++i)
        {
            data.add(""+i);
        }
        //设置为线性列表布局
        drag_swipe_recyclerView = (RecyclerView)findViewById(R.id.drag_swipe_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置为垂直滑动的列表
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        drag_swipe_recyclerView.setLayoutManager(layoutManager);
        //设置各个布局高度一致加快加载速度
        drag_swipe_recyclerView.setHasFixedSize(true);
        //初始化列表
        DragSwipeListAdapter adapter = new DragSwipeListAdapter(data, this);
        drag_swipe_recyclerView.setAdapter(adapter);
        //设置列表删除和添加时的动画
        drag_swipe_recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置列表每个Item的间隔线
        drag_swipe_recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        // 设置点击事件
        adapter.setItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(Object obj) {
                Toast.makeText(MainActivity.this, "点击了"+obj.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //设置触摸监听事件
        ItemTouchHelper.Callback callback = new DraySwipeTouchCallBack(adapter);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(drag_swipe_recyclerView);
    }

    @Override
    public void startDragListener(RecyclerView.ViewHolder holder) {
        if (helper != null)
        {
            helper.startDrag(holder);
        }
    }
}
