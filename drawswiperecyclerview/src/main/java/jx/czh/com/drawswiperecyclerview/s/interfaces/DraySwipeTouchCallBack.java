package jx.czh.com.drawswiperecyclerview.s.interfaces;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

/**
 * Created by czh on 2016/5/10.
 * 拖拽排序+滑动删除列表手势监听事件
 */
public class DraySwipeTouchCallBack  extends Callback {

    private ISuperItemOpeartion itemMove;

    public DraySwipeTouchCallBack(ISuperItemOpeartion adapter) {
        this.itemMove = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置列表滑动方向
        int dragFlag = ItemTouchHelper.DOWN|ItemTouchHelper.UP;
        int swipeFlag = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    /**
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        //更换RecyclerView中两个对象的在数据的位置
        //更新RecyclerView
        itemMove.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemMove.onItemSwipe(viewHolder.getAdapterPosition());
    }
}
