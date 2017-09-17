package com.example.yep.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/**
 * Created by seowo on 2017-09-16.
 */

public class ArticleTextSpan implements LeadingMarginSpan.LeadingMarginSpan2 {
    private int margin;
    private int lines;
    private boolean wasDrawCalled = false;
    private int drawLineCount = 0;

    public ArticleTextSpan(int lines, int margin) {
        this.margin = margin;
        this.lines = lines;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        if (first) {
            return margin;
        }
        else
        {
            //Offset for all other Layout layout ) { }
     /*Returns * the number of rows which should be applied *     indent returned by getLeadingMargin (true)
     * Note:* Indent only applies to N lines of the first paragraph.*/
            return 0;
        }
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        this.wasDrawCalled = true;
    }

    @Override
    public int getLeadingMarginLineCount() {
        return this.lines;
    }
}
