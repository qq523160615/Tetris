package com.example.android_canvas.ui;

import android.graphics.Canvas;

public class LayerButton extends Layer {

	public LayerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("static-access")
	@Override
	public void point(Canvas canvas) {
		// TODO Auto-generated method stub
		this.drawImage(canvas, gameDto.button[2], 0, 0, gameDto.button[3].getWidth(), gameDto.button[3].getHeight(), 
				0, 1100, 130, 1230);
		this.drawImage(canvas, gameDto.button[0], 0, 0, gameDto.button[0].getWidth(), gameDto.button[0].getHeight(), 
				148, 1100, 278, 1230);
		this.drawImage(canvas, gameDto.button[1], 0, 0, gameDto.button[1].getWidth(), gameDto.button[1].getHeight(), 
				296, 1100, 426, 1230);
		this.drawImage(canvas, gameDto.button[4], 0, 0, gameDto.button[4].getWidth(), gameDto.button[4].getHeight(), 
				444, 1100, 574, 1230);
		this.drawImage(canvas, gameDto.button[3], 0, 0, gameDto.button[1].getWidth(), gameDto.button[1].getHeight(), 
				592, 1100, 720, 1230);
	}

}
