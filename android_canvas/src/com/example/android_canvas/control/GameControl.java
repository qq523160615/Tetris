package com.example.android_canvas.control;

import java.util.Random;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;

import com.example.android_canvas.GameView;
import com.example.android_canvas.dto.GameDto;
import com.example.android_canvas.entity.Box;
import com.weibo.sdk.android.demo.R;

public class GameControl implements OnTouchListener {


	private Box box;

	private GameDto gameDto;

	private com.example.android_canvas.GameView gameView;

	private Context context;

	@SuppressWarnings("static-access")
	public GameControl(Box box,GameDto gameDto,GameView gameView,Context context){
		this.box = box;
		gameDto.setBox(box);
		this.gameDto = gameDto;
		this.gameView = gameView;
		this.context = context;
		Random random = new Random();
		gameDto.nextType=random.nextInt(gameDto.typeNum);
		new MainThread().start();
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		synchronized (gameDto.gameMap) {
			if(event.getAction() == event.ACTION_DOWN){  
				int x = (int) event.getX();
				int y = (int) event.getY();
				if( x<130 && y > 1030 && gameDto.flag){			//��
					String falg = "";
					if(isOverZoneLeft()&& isMapB()){
						for (int i = 0; i < 4; i++) {
							box.point[gameDto.type][i].x--;
							if(gameDto.isSpMusic){
								GameDto.moveMusic.puase();
								GameDto.moveMusic.startMusic();
							}
							falg =falg +  Integer.toString(box.point[gameDto.type][i].x) + "," + Integer.toString(box.point[gameDto.type][i].y) + ";" ;
						}
					}
					Log.i("�����ƶ�һ��",falg);
				}
				if( x>592 && y >1030 && gameDto.flag){ 		//����
					String falg = "";
					if(isOverZoneRight()&& isMapA()){
						for (int i = 0; i< 4; i++) {
							box.point[gameDto.type][i].x++;
							if(gameDto.isSpMusic){
								GameDto.moveMusic.puase();
								GameDto.moveMusic.startMusic();
							}
							falg =falg +  Integer.toString(box.point[gameDto.type][i].x) + "," + Integer.toString(box.point[gameDto.type][i].y) + ";" ;
						}
					}
					Log.i("�����ƶ�һ��",falg);
				}

				if( x>148 && x < 278 && y >1030 && gameDto.flag){ 		//��ת
					String falg = "";
					if(isOverZoneRound()&&isMap()){
						for (int i = 1; i < 4; i++) {
							int point_x=box.point[gameDto.type][0].x - box.point[gameDto.type][0].y +  box.point[gameDto.type][i].y;
							int point_y=box.point[gameDto.type][0].x + box.point[gameDto.type][0].y -  box.point[gameDto.type][i].x;
							box.point[gameDto.type][i].x = point_x;
							box.point[gameDto.type][i].y = point_y;
							falg =falg +  Integer.toString(box.point[gameDto.type][i].x) + "," + Integer.toString(box.point[gameDto.type][i].y) + ";" ;
						}
					}
					Log.i("��תһ��",falg);
				}

				if( x<574 && x >444 && y > 1030 && gameDto.flag){
					moveFast();
				}


				if( x<426 && x >296 && y > 1030 && gameDto.flag){	//����
					String falg = "";
					if(isOverZoneDown() && isMap()){
						if(gameDto.isSpMusic){
							GameDto.moveMusic.puase();
							GameDto.moveMusic.startMusic();
						}
						for (int i = 0; i< 4; i++) {
							box.point[gameDto.type][i].y++;
							falg =falg +  Integer.toString(box.point[gameDto.type][i].x) + "," + Integer.toString(box.point[gameDto.type][i].y) + ";" ;
						}
						Log.i("�����ƶ�",falg);
						Log.i("_______","_________________________________________________");	
					}
					else{
						for (int i = 0; i < 4; i++) {
							gameDto.gameMap[box.point[gameDto.type][i].x][box.point[gameDto.type][i].y]=true;
						}
						rmLine();
						box = new Box();
						gameDto.setBox(box);
						gameDto.type = gameDto.nextType;
						Random random = new Random();
						gameDto.nextType=random.nextInt(gameDto.typeNum);
						gameDto.colorType = random.nextInt(7);
					}
				}
				gameView.postInvalidate();
			}
			return false;
		}
	}

	@SuppressWarnings("static-access")
	public boolean isOverZoneLeft(){
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][i].x > 0 ){  //|| box.point[box.type][i].x < 9 || box.point[box.type][i].y < 19

			}
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean isOverZoneRight(){
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][i].x < 9 ){  

			}
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean isOverZoneDown(){
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][i].y < 19 ){  //|| box.point[box.type][i].x < 9 || box.point[box.type][i].y < 19

			}
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean isOverZoneRound(){
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][0].x + box.point[gameDto.type][0].y -  box.point[gameDto.type][i].x < 20 && 
					box.point[gameDto.type][0].x - box.point[gameDto.type][0].y +  box.point[gameDto.type][i].y < 10 && 
					box.point[gameDto.type][0].x - box.point[gameDto.type][0].y +  box.point[gameDto.type][i].y >= 0){ 

			}
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean isMap(){
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][i].y < 19){
				if(!(gameDto.gameMap[box.point[gameDto.type][i].x][box.point[gameDto.type][i].y + 1])){

				}
				else {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean isMapA(){
		boolean flag = true;
		int point_x;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][i].x == 9){
				point_x= box.point[gameDto.type][i].x;
				flag = false;
				break;
			}else{
				point_x = box.point[gameDto.type][i].x + 1;
			}
			if(!(gameDto.gameMap[point_x][box.point[gameDto.type][i].y])){

			}
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean isMapB(){
		boolean flag = true;
		int point_x;
		for (int i = 0; i < 4; i++) {
			if(box.point[gameDto.type][i].x == 0){
				point_x= box.point[gameDto.type][i].x;
				flag = false;
				break;
			}else{
				point_x = box.point[gameDto.type][i].x - 1;
			}
			if(!(gameDto.gameMap[point_x][box.point[gameDto.type][i].y])){

			}
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	private class MainThread extends Thread{

		@SuppressWarnings("static-access")
		@Override
		public void run(){
			while(true){
				if(gameDto.flag && gameDto.pause){
					try {
						Thread.sleep(500 - (gameDto.level * 50));
						if(isOverZoneDown() && isMap()){
							String falg = "";
							for (int i = 0; i< 4; i++) {
								box.point[gameDto.type][i].y++;
								falg =falg +  Integer.toString(box.point[gameDto.type][i].x) + "," + Integer.toString(box.point[gameDto.type][i].y) + ";" ;
							}
							Log.i("�߳��ƶ�",falg);
							Log.i("_______","_________________________________________________");	
						}
						else{
							for (int i = 0; i < 4; i++) {
								gameDto.gameMap[box.point[gameDto.type][i].x][box.point[gameDto.type][i].y]=true;
							}
							if(gameDto.gameMap[box.point[gameDto.type][0].x][box.point[gameDto.type][0].y] && box.point[gameDto.type][0].y ==1){
								gameDto.flag = false;
								GameDto.bgMusic.puase();
								//TODO ��Ϸ�����󵯳����ڱ������
							}else{
								Random random = new Random();
								gameDto.colorType = random.nextInt(7);
								box = new Box();
								gameDto.setBox(box);
								rmLine();
								gameDto.type = gameDto.nextType;
								gameDto.nextType=random.nextInt(gameDto.typeNum);
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gameView.postInvalidate();
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public void rmLine(){
		for(int i=0;i<20;i++){
			if(isRmLine(i)){
				if(gameDto.isSpMusic){
					gameDto.removeMusic.startMusic();
				}
				gameDto.reLine++;
				if(gameDto.reLine == 1){
					gameDto.point = gameDto.point + 10;
				}
				if(gameDto.reLine == 2){
					gameDto.point = gameDto.point + 20;
				}
				if(gameDto.reLine == 3){
					gameDto.point = gameDto.point + 30;
				}
				if(gameDto.reLine == 4){
					gameDto.point = gameDto.point + 40;
				}
				for (int z = i; z > 0 ; z--) {
					for (int j = 0; j < 10 ; j++) {
						gameDto.gameMap[j][z] = gameDto.gameMap[j][z-1];
					}
				}	
			}
		}
		if(gameDto.point > gameDto.level*100){
			gameDto.level++;
		}
		gameDto.reLine = 0 ;
	}

	@SuppressWarnings("static-access")
	public boolean isRmLine(int x){
		boolean flag = true;
		for(int i = 0;i < 10; i++){
			if(!(gameDto.gameMap[i][x])){
				flag = false;
				break;
			}
		}
		return flag;
	}

	public void savePoint(){
		Dialog dialog = new Dialog(context);  
		dialog.setContentView(R.layout.savepoint);  
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();  
	}

	@SuppressWarnings("static-access")
	public void moveFast(){
		boolean flag = true;
		String falg = "";
		while(flag){
			if(isOverZoneDown() && isMap()){
				for (int i = 0; i< 4; i++) {
					box.point[gameDto.type][i].y++;
					if(gameDto.isSpMusic){
						GameDto.moveMusic.puase();
						GameDto.moveMusic.startMusic();
					}
					falg = falg + Integer.toString(box.point[gameDto.type][i].x) + "," + Integer.toString(box.point[gameDto.type][i].y) + ";";
				}
				Log.i("�����ƶ�",falg);
				Log.i("_______","_________________________________________________");	
			}else{
				flag = false;
				for (int i = 0; i < 4; i++) {
					gameDto.gameMap[box.point[gameDto.type][i].x][box.point[gameDto.type][i].y]=true;
				}
				rmLine();
				box = new Box();
				gameDto.setBox(box);
				gameDto.type = gameDto.nextType;
				Random random = new Random();
				gameDto.nextType=random.nextInt(gameDto.typeNum);
				gameDto.colorType = random.nextInt(7);
				gameView.postInvalidate();
			}
		}
	}

}
