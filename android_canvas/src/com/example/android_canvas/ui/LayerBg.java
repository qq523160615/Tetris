package com.example.android_canvas.ui;

import android.graphics.Canvas;

public class LayerBg extends Layer {

	
	public LayerBg(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void point(Canvas canvas) {
		// TODO Auto-generated method stub
		this.drawImage(canvas, gameDto.getPic_bg(), 0, 0, gameDto.pic_bg.getWidth(), gameDto.pic_bg.getHeight(), 0, 0, gameDto.getWidth(), gameDto.getHeight());
	}

}
