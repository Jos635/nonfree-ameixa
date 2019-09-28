package org.xphnx.ameixa.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

import org.xphnx.ameixa.R;
import org.xphnx.ameixa.utils.ImageUtils;
import org.xphnx.ameixa.utils.ScreenUtils;

import java.util.ArrayList;

public class IconActivity extends BaseActivity {
    private ArrayList<LinearLayout> layoutList = new ArrayList<>();
    private ArrayList<ImageView> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ScreenUtils.isPortrait(getApplicationContext())) {
            createLayout(7);
        } else {
            createLayout(12);
        }
    }

    private void createLayout(int width) {
        float scale = ScreenUtils.densityScale(getApplicationContext());
        int margin = 16 * Math.round(scale);

        LayoutParams baseParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        LayoutParams containerParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        LayoutParams imageParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1f);

        ScrollView baseScroller = new ScrollView(this);
        baseScroller.setLayoutParams(baseParams);
        baseScroller.setBackgroundColor(getResources().getColor(R.color.colorLight));
        setContentView(baseScroller);
        baseScroller.setVisibility(View.VISIBLE);

        // display width hack
        Rect windowRect = new Rect();
        baseScroller.getWindowVisibleDisplayFrame(windowRect);
        int windowWidth = windowRect.right - windowRect.left;

        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setLayoutParams(layoutParams);
        baseLayout.setPadding(margin, margin, 0, 0);
        baseScroller.addView(baseLayout);

        String[] images = getResources().getStringArray(R.array.icon_pack);
        for (int i = 0; i < images.length; i++) {
            if ((i % width) == 0) {
                layoutList.add((i / width), new LinearLayout(this));
                layoutList.get(i / width).setOrientation(LinearLayout.HORIZONTAL);
                layoutList.get(i / width).setGravity(Gravity.START);
                layoutList.get(i / width).setLayoutParams(containerParams);

                baseLayout.addView(layoutList.get(i / width));
            }
            imageList.add(i, new ImageView(this));
            imageList.get(i).setLayoutParams(imageParams);
            imageList.get(i).setScaleType(ImageView.ScaleType.FIT_XY);
            imageList.get(i).setPadding(0, 0, margin, margin);
            imageList.get(i).setAdjustViewBounds(true);

            final int resId = getResources().getIdentifier(images[i], "drawable", getPackageName());
            int width1 = (windowWidth / width) - (margin * width + margin) / width;
            ImageUtils.bitmapLoadAsync(imageList.get(i), getApplicationContext().getResources(), resId, width1, width1);

            layoutList.get(i / width).addView(imageList.get(i));
        }
    }
}
