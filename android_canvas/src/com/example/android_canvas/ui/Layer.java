package com.example.android_canvas.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.android_canvas.dto.GameDto;

public abstract class Layer {


	public Paint mPaint = null;

	protected int x;

	protected int y;

	protected int w;

	protected int h;

	protected GameDto gameDto = null;

	public Layer(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}


	public void drawImage(Canvas canvas,Bitmap Image,int src_left,int src_top,int src_right,int src_bottom
			,int dst_left,int dst_top,int dst_right,int dst_bottom){
		Rect src = new Rect();
		Rect dst = new Rect();

		src.left = src_left;
		src.top = src_top;
		src.right = src_right;
		src.bottom = src_bottom;

		dst.left = dst_left;
		dst.top = dst_top;
		dst.right = dst_right;
		dst.bottom = dst_bottom;
		canvas.drawBitmap(Image, src, dst, null);
	}

	public void drawWindow(Canvas canvas,Bitmap pic){
		this.drawImage(canvas, pic,0, 0, 8, 8, x, y, 10+x,10+y );  //左上
		this.drawImage(canvas, pic,8, 0, 72, 8,x + 10, y ,w - 10 ,y + 10 ); //中上
		this.drawImage(canvas, pic,72, 0, 80, 8, w - 10, y ,w , y + 10 ); //右上
		this.drawImage(canvas, pic,0, 8, 8, 72,x ,y + 10 ,x + 10 , h - 10); //左中 
		this.drawImage(canvas, pic,0, 72, 8,80,x,h - 10,x + 10,h); //左下
		this.drawImage(canvas, pic,8, 72, 72,80,x + 10,h - 10 ,w - 10,h); //中下
		this.drawImage(canvas, pic,72, 72, 80,80,w - 10,h -10,w ,h); //右下
		this.drawImage(canvas, pic,72,8,80,72,w - 10 ,y  + 10 ,w,h - 10); //右中
		this.drawImage(canvas, pic,10,10,70,70,x + 10,y + 10 ,w -10,h - 10); //正中
	}

	public void drawText(Canvas canvas,String str,int x){
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setTextSize(30);
		canvas.drawText(str, x, x, mPaint);
	}

	@SuppressWarnings("static-access")
	public void drawAect(Canvas canvas,Bitmap pic){
		for(int i =0;i<4;i++){
			drawImage(canvas, pic, 43 * gameDto.colorType  , 0, 43 * (gameDto.colorType + 1), 43, 20 + gameDto.box.point[gameDto.type][i].x * 51 , 20 + gameDto.box.point[gameDto.type][i].y * 51 , 71 + gameDto.box.point[gameDto.type][i].x * 51, 71 + gameDto.box.point[gameDto.type][i].y * 51);
		}
	}

	abstract public void point(Canvas canvas);



	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}


}
