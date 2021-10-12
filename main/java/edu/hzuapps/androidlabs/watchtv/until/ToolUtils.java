package edu.hzuapps.androidlabs.watchtv.until;

import androidx.fragment.app.FragmentActivity;

public class ToolUtils {

    public static int dip2px(FragmentActivity context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
