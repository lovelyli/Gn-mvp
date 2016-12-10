package com.project.gnet.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by GaoNan on 2015/12/30.
 */
public class BaseRecylerViewHolder extends RecyclerView.ViewHolder{

    private final SparseArray<View> mViews= new SparseArray<View>();
    private View mConvertView;

    public BaseRecylerViewHolder(View itemView)
    {
        super(itemView);
        mConvertView=itemView;
    }

    public View getHolderView(){
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public TextView getTextView(int viewId) {
        return (TextView)getView(viewId);
    }

    public ProgressBar getProgressBar(int viewId) {
        return (ProgressBar)getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public LinearLayout getLinearLayout(int viewId) {
        return (LinearLayout) getView(viewId);
    }

    public RelativeLayout getRelativeLayout(int viewId) {
        return (RelativeLayout) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public RatingBar getRatingBar(int viewId) {
        return (RatingBar) getView(viewId);
    }
    public GridView getGridView(int viewId) {
        return (GridView) getView(viewId);
    }


}
