package com.example.android_canvas.ui;

import android.graphics.Canvas;

public class LayerNext extends Layer{

	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("static-access")
	@Override
	public void point(Canvas canvas) {
		// TODO Auto-generated method stub
		this.drawWindow(canvas, gameDto.getPic_window());
		this.drawImage(canvas, gameDto.pic_next, 0, 0, gameDto.pic_next.getWidth(), gameDto.pic_next.getHeight(),
						this.x + 20 , this.y + 50 , this.x + 120, this.y + 130);
	//	this.drawImage(canvas, gameDto.next[gameDto.nextType], 0, 0 , gameDto.next[gameDto.nextType].getWidth(), gameDto.next[gameDto.nextType].getHeight(), 
	//			this.x + 40 , this.y + 160, this.x + 140, this.y + 220);
	this.drawImage(canvas, gameDto.next[gameDto.nextType], 0, 0 , gameDto.next[gameDto.nextType].getWidth(), gameDto.next[gameDto.nextType].getHeight(), 
				this.x + 40 , this.y + 160, this.x + 140, this.y + 220);
	}

}
