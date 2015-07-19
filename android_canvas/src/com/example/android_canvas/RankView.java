package com.example.android_canvas;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.example.android_canvas.database.Player;
import com.example.android_canvas.dto.GameDto;

public class RankView extends View
{
	private List<Player> players;
	
	private Paint mPaint;
	
	private Bitmap rank = ((BitmapDrawable)getResources().getDrawable(R.drawable.rank)).getBitmap();

	public RankView(Context context) {
		super(context);
		players = GameDto.dataBase.SearchPlayer();
		mPaint = new Paint();
		mPaint.setTextSize(50);
		mPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.drawImage(canvas, rank, 0, 0, rank.getWidth(), rank.getHeight(), 0, 0, 720, 1280);
		int i = 0;
		for (Player player:players) {
			canvas.drawText(player.getName(), 200, 200 + i * 100, mPaint);
			canvas.drawText(Integer.toString(player.getPoint()), 550, 200 + i * 100 , mPaint);
			i++;
		}
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

}
