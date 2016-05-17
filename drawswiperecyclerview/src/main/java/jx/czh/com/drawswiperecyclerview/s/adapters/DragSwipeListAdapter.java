package jx.czh.com.drawswiperecyclerview.s.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import jx.czh.com.drawswiperecyclerview.R;
import jx.czh.com.drawswiperecyclerview.s.interfaces.ISuperItemOpeartion;
import jx.czh.com.drawswiperecyclerview.s.interfaces.onItemClickListener;
import jx.czh.com.drawswiperecyclerview.s.interfaces.startDragListener;

/**
 * Created by czh on 2016/5/10.
 * 拖动排序+滑动删除列表适配器
 */
public class DragSwipeListAdapter extends RecyclerView.Adapter<DragSwipeListAdapter.MyViewHolder> implements ISuperItemOpeartion {

    private onItemClickListener itemClickListener;
    @Override
    public void onItemMove(int srcPostion, int aimPostion) {
        //交换集合中，这两个位置的数据
        Collections.swap(this.data, srcPostion, aimPostion);
        notifyItemMoved(srcPostion, aimPostion);
    }

    @Override
    public void onItemSwipe(int postion) {
        this.data.remove(postion);
        notifyItemRemoved(postion);
    }

    private List<String> data;
    private startDragListener listener;
    public DragSwipeListAdapter(List<String> data, startDragListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dray_swip_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvItemName.setText(data.get(position));
        holder.tvItemName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() ==  MotionEvent.ACTION_DOWN)
                {
                    listener.startDragListener(holder);
                }
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(holder.tvItemName.getText().toString());
            }
        });
    }


    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.item_tv_drag_swipe_name);
        }
    }
}
