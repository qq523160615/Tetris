package com.example.android_canvas.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LayerPoint extends Layer {

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
 
	@SuppressWarnings("static-access")
	@Override
	public void point(Canvas canvas) {
		// TODO Auto-generated method stub
		this.drawWindow(canvas, gameDto.getPic_window());
		Paint mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setTextSize(60);
		this.drawImage(canvas, gameDto.pic_point, 0, 0, gameDto.pic_point.getWidth(), gameDto.pic_point.getHeight(),
				this.x + 20 , this.y + 50 , this.x + 120, this.y + 130);
		canvas.drawText(Integer.toString(gameDto.point), this.x + 40, this.y + 230, mPaint);
	}

}
