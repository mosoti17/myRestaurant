package com.mosoti.myrestaurants.util;

/**
 * Created by mosoti on 9/24/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
