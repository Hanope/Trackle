package com.example.yep.myapplication;

/**
 * Created by yep on 2017. 9. 16..
 */
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class Util_ImageView extends AppCompatImageView {
    public int type = 0;
    static private Context context;

    public Util_ImageView(Context context) {
        super(context);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    public Util_ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Util_ImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }


        Bitmap b = null;

        b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();
/*
        Log.e("canvas", canvas.getWidth() + " "+ canvas.getHeight());
        Log.e("view", getWidth()+ " "+ getHeight());
        Log.e("bitmap", b.getWidth()+ " "+ b.getHeight());
*/

        Bitmap roundBitmap = getCroppedBitmap(bitmap, w, h, type);

        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int width, int height, int type) {
        Bitmap sbmp;
        if (type == 1 || type == 2 || type == 3 || type == 4 || type == 11 || type == 12) {
            sbmp = Bitmap.createScaledBitmap(bmp, height, height, false);
        } else if (type == 5 || type == 6 || type == 7 || type == 8 ||
                type == 9 || type == 10) {
            sbmp = Bitmap.createScaledBitmap(bmp, height, height, false);
        } else {
            sbmp = bmp;
        }

//        if(type != 11){
//            RenderScript rs = RenderScript.create(context);
//            final Allocation input = Allocation.createFromBitmap(rs, sbmp);
//            final Allocation output2 = Allocation.createTyped(rs, input.getType());
//            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//
//            script.setRadius(25.f);
//            script.setInput(input);
//            script.forEach(output2);
//            output2.copyTo(sbmp);
//
//        }

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));

        switch (type) {
            case 1:
            case 11:
                canvas.drawCircle(width / 2, height / 2, width / 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;

            case 2:
            case 4:
                sbmp = Bitmap.createBitmap(sbmp, sbmp.getWidth() / 4, 0, (sbmp.getWidth() / 4) * 2, height);
                canvas.drawCircle(height / 2, height / 2, width, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;
            case 3:
                sbmp = Bitmap.createBitmap(sbmp, sbmp.getWidth() / 4, 0, (sbmp.getWidth() / 4) * 2, height);
                canvas.drawCircle(-2, height / 2, width, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;

            case 5:
            case 9:
                canvas.drawCircle(0, height, width, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;
            case 6:
            case 10:
                canvas.drawCircle(0, 0, width, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;

            case 7:
                canvas.drawCircle(width, height, width, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;
            case 8:
                canvas.drawCircle(width, 0, width, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(sbmp, rect, rect, paint);
                break;
            case 12:
                canvas.drawBitmap(sbmp, rect,rect,paint);
                break;

        }

/*
        canvas.drawCircle(0,height/2, width, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
*/

        return output;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setImageBitmap(Bitmap bm, int type) {
        super.setImageBitmap(bm);
        this.type = type;
        //initializeBitmap();
    }

}