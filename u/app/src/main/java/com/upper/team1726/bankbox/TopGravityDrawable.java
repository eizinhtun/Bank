package com.upper.team1726.bankbox;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by USER on 11/8/2017.
 */

public class TopGravityDrawable extends BitmapDrawable {

    public TopGravityDrawable(Resources res, Bitmap bitmap) {
        super(res, bitmap);
    }

    @Override
    public void draw(Canvas canvas) {
        int halfCanvas = canvas.getHeight() / 2;
        int halfDrawable = getIntrinsicHeight() / 2;
        canvas.save();
        canvas.translate(0, -halfCanvas + halfDrawable + 30);
        super.draw(canvas);
        canvas.restore();
    }
}