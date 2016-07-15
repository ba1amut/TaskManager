package com.speedsumm.bu.sqldb1;

/**
 * Created by bu on 13.07.2016.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
