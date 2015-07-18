package com.example.android_canvas.ui;

import android.graphics.Canvas;

import com.example.android_canvas.dto.GameDto;


public class LayerGame extends Layer{

	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("static-access")
	@Override
	public void point(Canvas canvas) {
		// TODO Auto-generated method stub
		this.drawWindow(canvas, gameDto.getPic_window());
		this.drawAect(canvas, gameDto.getPic_rect());
		if(gameDto.isShandow){
			drawShandow(canvas);
		}
		int z = gameDto.level % 8 ;
		if(z == 0){
			z = 8;
		}
		if(gameDto.flag){
			this.drawMap(canvas,z - 1);
		}
		else{
			this.drawMap(canvas,8);
			this.drawImage(canvas, gameDto.pic_gameover, 0, 0, gameDto.pic_gameover.getWidth(), gameDto.pic_gameover.getHeight(), 
					this.x + 30 , this.y + 400, this.x + 650 , this.y + 700);
		}
	}

	@SuppressWarnings("static-access")
	public void drawMap(Canvas canvas,int x){
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if(gameDto.gameMap[i][j]){
					this.drawImage(canvas, gameDto.getPic_rect(), 43 * x  , 0,  43 + 43 * x, 43 , 20 + i * 51 , 20 + j * 51 , 71 + i * 51, 71 + j * 51);
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public void drawShandow(Canvas canvas){
		int max = gameDto.box.point[GameDto.type][0].x;
		int min = gameDto.box.point[GameDto.type][0].x;
		for (int i = 1; i < 4; i++) {
			if(gameDto.box.point[GameDto.type][i].x > max)
				max = gameDto.box.point[GameDto.type][i].x;
			if(gameDto.box.point[GameDto.type][i].x < min)
				min = gameDto.box.point[GameDto.type][i].x;
		}
		this.drawImage(canvas, gameDto.shandow, 0, 0, gameDto.shandow.getWidth(), gameDto.shandow.getHeight(), 
				this.x  + 8 + min  * 51 , this.y + 8, this.x +  (max + 1 ) * 51 + 8 , this.y + 1030);
	}
}
