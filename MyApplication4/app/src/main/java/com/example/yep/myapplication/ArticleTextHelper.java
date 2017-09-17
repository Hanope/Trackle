package com.example.yep.myapplication;

import android.text.SpannableString;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by seowo on 2017-09-16.
 */

public class ArticleTextHelper {
    private static boolean mNewClassAvailable;

    /* class initialization fails when this throws an exception */
    static {
        try {
            Class.forName("android.text.style.LeadingMarginSpan$LeadingMarginSpan2");
            mNewClassAvailable = true;
        } catch (Exception ex) {
            mNewClassAvailable = false;
        }
    }

    public static void tryFlowText(String text, int w, int h, TextView messageView, Display display, int addPadding){
        // There is nothing I can do for older versions, so just return
        if(!mNewClassAvailable) return;

        // Get height and width of the image and height of the text line

        int height = h;
        int width = w + addPadding;

        messageView.measure(width, height); //to allow getTotalPaddingTop
        int padding = messageView.getTotalPaddingTop();
        float textLineHeight = messageView.getPaint().getTextSize();

        // Set the span according to the number of lines and width of the image
        int lines =  (int)Math.round((height - padding) / textLineHeight);
        SpannableString ss = new SpannableString(text);
        //For an html text you can use this line: SpannableStringBuilder ss = (SpannableStringBuilder)Html.fromHtml(text);
        ss.setSpan(new ArticleTextSpan(lines, width), 0, ss.length(), 0);
        messageView.setText(ss);

        // Align the text with the image by removing the rule that the text is to the right of the image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)messageView.getLayoutParams();
        int[]rules = params.getRules();
        rules[RelativeLayout.RIGHT_OF] = 0;
    }


}
