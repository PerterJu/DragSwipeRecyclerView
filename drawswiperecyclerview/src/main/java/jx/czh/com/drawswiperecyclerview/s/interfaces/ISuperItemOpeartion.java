package jx.czh.com.drawswiperecyclerview.s.interfaces;

/**
 * Created by czh on 2016/5/12.14:00.
 * Tip: Android_Study
 */
public interface ISuperItemOpeartion {
    /**
     * 移动两个Item的位置
     * @param srcPostion 元数据位置
     * @param aimPostion 目标数据位置
     */
    void onItemMove(int srcPostion, int aimPostion);

    /**
     * 当item滑动的处理
     * @param postion
     */
    void onItemSwipe(int postion);
}
