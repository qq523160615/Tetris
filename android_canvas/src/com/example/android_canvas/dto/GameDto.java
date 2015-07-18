package com.example.android_canvas.dto;

import java.util.Random;

import android.graphics.Bitmap;

import com.example.android_canvas.database.DataBase;
import com.example.android_canvas.entity.Box;
import com.example.android_canvas.media.Music;

public class GameDto {

	public int width;

	public int height;

	public static int type;

	public static int nextType;

	public Box box;

	public Bitmap pic_bg = null;

	public Bitmap pic_window = null;

	public Bitmap pic_rect = null;
	
	public Bitmap pic_gameover = null;
	
	public Bitmap pic_next = null;
	
	public Bitmap pic_point = null;

	public Bitmap pic_level = null;
	
	public static Bitmap next[];

	public static int reLine = 0;

	public static Bitmap button[];
	
	public static int point ;
	
	public static boolean flag;
	
	public static int level;
	
	public static boolean pause;
	
	public static Music bgMusic;
	
	public static Music moveMusic;
	
	public static Music loseMusic;
	
	public static Music removeMusic;
	
	public static int colorType;
	
	public static DataBase dataBase;
	
	public static Bitmap rank;
	
	public static Bitmap shandow;
	
	public static boolean isBgMusic;
	
	public static boolean isSpMusic;
	
	public static boolean isShandow;
	
	public static int typeNum;

	public  int windowSize[][] = {{10,10,540, 1050},{550,10,710,340},{550,360,710,680},{550,700,710,1050},{0,1050,720,1280},{0,0,720,1280}
	};

	public static boolean gameMap[][];

	public static String layerName[] = {"com.example.android_canvas.ui.LayerGame",
		"com.example.android_canvas.ui.LayerNext",
		"com.example.android_canvas.ui.LayerPoint",
		"com.example.android_canvas.ui.LayerLevel",
		"com.example.android_canvas.ui.LayerButton",
	"com.example.android_canvas.ui.LayerBg"};

	public GameDto(){
		gameMap = new boolean[10][20];
		init();
	}

	public void init(){

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				gameMap[i][j]=false;
			}
		}

		Random random = new Random();
		type = random.nextInt(7);
	}

	public Bitmap getPic_bg() {
		return pic_bg;
	}

	public void setPic_bg(Bitmap pic_bg) {
		this.pic_bg = pic_bg;
	}

	public Bitmap getPic_window() {
		return pic_window;
	}

	public void setPic_window(Bitmap pic_window) {
		this.pic_window = pic_window;
	}

	public Bitmap getPic_rect() {
		return pic_rect;
	}

	public void setPic_rect(Bitmap pic_rect) {
		this.pic_rect = pic_rect;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public static void setNext(Bitmap[] next) {
		GameDto.next = next;
	}

	public static void setButton(Bitmap[] button) {
		GameDto.button = button;
	}

	public static void setRank(Bitmap rank) {
		GameDto.rank = rank;
	}

	public void setPic_gameover(Bitmap pic_gameover) {
		this.pic_gameover = pic_gameover;
	}

	public void setPic_next(Bitmap pic_next) {
		this.pic_next = pic_next;
	}

	public void setPic_point(Bitmap pic_point) {
		this.pic_point = pic_point;
	}

	public void setPic_level(Bitmap pic_level) {
		// TODO Auto-generated method stub
		this.pic_level = pic_level;
	}

	public static void setShandow(Bitmap shandow) {
		GameDto.shandow = shandow;
	}

	public void setBox(Box box) {
		this.box = box;
	}
	
}
