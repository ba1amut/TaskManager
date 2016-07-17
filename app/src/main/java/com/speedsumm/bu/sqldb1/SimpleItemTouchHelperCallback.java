package com.speedsumm.bu.sqldb1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by bu on 15.07.2016.
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;
    public static int position;
    private Paint paint = new Paint();

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;

    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;

        //Icon position block START
        int iconBorderWidth = 6;
        float height = itemView.getBottom() - itemView.getTop();
        float iconBorder = height/iconBorderWidth;
        //Icon position block END

        if (MainActivity.dbHandler.getTasksListFlag()==0) {

            if (dX > 0) {
                paint.setColor(Color.parseColor(MainActivity.colorCompleted));
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                c.drawRect(background, paint);
                Bitmap icon = BitmapFactory.decodeResource(MainActivity.res,R.drawable.ic_done_white_48dp);
                RectF icon_dest = new RectF((float)itemView.getLeft()+ iconBorder,(float)itemView.getTop()+iconBorder,(float)itemView.getLeft()+iconBorder*(iconBorderWidth-1),(float)itemView.getBottom()-iconBorder);
                c.drawBitmap(icon , null,icon_dest,paint);


            } else {
                paint.setColor(Color.parseColor(MainActivity.colorDelete));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, paint);
                Bitmap icon = BitmapFactory.decodeResource(MainActivity.res,R.drawable.ic_delete_white_48dp);
                RectF icon_dest = new RectF((float)itemView.getRight()-iconBorder*(iconBorderWidth-1),(float)itemView.getTop()+iconBorder,(float)itemView.getRight()-iconBorder,(float)itemView.getBottom()-iconBorder);
                c.drawBitmap(icon , null,icon_dest,paint);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        position = viewHolder.getAdapterPosition();

        switch (direction){
            case ItemTouchHelper.START:
                MainActivity.dbHandler.deleteTask(MainActivity.taskArrayList.get(viewHolder.getAdapterPosition()).get_id());
                mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
                break;

            case ItemTouchHelper.END:
                MainActivity.dbHandler.updateTask(MainActivity.taskArrayList.get(viewHolder.getAdapterPosition()).get_id());
                mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
                break;
        }

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        if (MainActivity.dbHandler.getTasksListFlag() == 0) {
            return true;
        } else {
            return false;
        }

    }
}
