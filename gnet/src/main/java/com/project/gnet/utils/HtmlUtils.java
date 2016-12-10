package com.project.gnet.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

/**
 * Created by GaoNan on 2016/8/16.
 */
public class HtmlUtils {


    /**
     * 字符串
     *
     * @return
     */
    public static String descString(int imagid, String text) {
//        <img alt="" src=""></img> <span style="width: 10px;line-height: 30px;" ></span>
//        <div style="padding:20px;">assss</div>
//        return "<img src='" + imagid
//                + "'/><span style=\"line-height:500px;padding:500px;\" >"+text+"</span>";
        return "<img src='" + imagid + "' style='margin-bottom:500px;'/>"+text;

    }
    /**
     * ImageGetter用于text图文混排
     *
     * @return
     */
    public static Html.ImageGetter getImageGetterInstance(final Context context, final int width, final int height) {
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
//                int fontH = (int) (context.getResources().getDimension(sizeid) * 1.5);
                int id = Integer.parseInt(source);
                Drawable d = context.getResources().getDrawable(id);
//                int height = fontH;
//                int width = (int) ((float) d.getIntrinsicWidth() / (float) d
//                        .getIntrinsicHeight()) * fontH;
//                if (width == 0) {
//                    width = d.getIntrinsicWidth();
//                }
                d.setBounds(0, 0, width, height);
                return d;
            }
        };
        return imgGetter;
    }

}
