package org.xphnx.ameixa.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import org.xphnx.ameixa.R;
import org.xphnx.ameixa.utils.ScreenUtils;

public class LicenseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createLayout();
    }

    private void createLayout() {
        LayoutParams smallLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, 1f);
        float scale = ScreenUtils.densityScale(getApplicationContext());
        int padding = Math.round(64 * scale);

        LinearLayout frameLayout = new LinearLayout(this);
        frameLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorLight));
        frameLayout.setGravity(Gravity.CENTER);
        setContentView(frameLayout);

        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT));
        baseLayout.setGravity(Gravity.START);
        frameLayout.addView(baseLayout);

        // code
        LinearLayout sourceLayout = new LinearLayout(this);
        sourceLayout.setOrientation(LinearLayout.HORIZONTAL);
        sourceLayout.setLayoutParams(smallLayoutParams);
        sourceLayout.setGravity(Gravity.CENTER);
        baseLayout.addView(sourceLayout);

        LinearLayout sourceClickLayout = new LinearLayout(this);
        sourceClickLayout.setOrientation(LinearLayout.HORIZONTAL);
        sourceClickLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        sourceClickLayout.setGravity(Gravity.CENTER);
        sourceLayout.addView(sourceClickLayout);
        sourceClickLayout.setOnClickListener(this::gplLink);

        TextView sourceText = new TextView(this);
        sourceText.setText(getResources().getString(R.string.codelicense));
        sourceText.setTextSize(24);
        sourceText.setTextColor(getResources().getColor(R.color.textLight));
        sourceText.setPadding(padding, padding, padding, padding);
        sourceClickLayout.addView(sourceText);

        // images
        LinearLayout imgLayout = new LinearLayout(this);
        imgLayout.setOrientation(LinearLayout.HORIZONTAL);
        imgLayout.setLayoutParams(smallLayoutParams);
        imgLayout.setGravity(Gravity.CENTER);
        imgLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        baseLayout.addView(imgLayout);

        LinearLayout imgClickLayout = new LinearLayout(this);
        imgClickLayout.setOrientation(LinearLayout.HORIZONTAL);
        imgClickLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        imgClickLayout.setGravity(Gravity.CENTER);
        imgLayout.addView(imgClickLayout);
        imgClickLayout.setOnClickListener(this::ccLink);

        TextView imgText = new TextView(this);
        imgText.setText(getResources().getString(R.string.imageslicense));
        imgText.setTextSize(24);
        imgText.setTextColor(getResources().getColor(R.color.textDark));
        imgText.setPadding(padding, padding, padding, padding);
        imgClickLayout.addView(imgText);
    }

    public void gplLink(View v) {
        Uri uri = Uri.parse(getResources().getString(R.string.urlgplv3));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void ccLink(View v) {
        Uri uri = Uri.parse(getResources().getString(R.string.urlccbysa4));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
