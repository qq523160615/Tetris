package com.example.android_canvas;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.android_canvas.dto.GameDto;
import com.example.android_canvas.media.Music;
import com.weibo.sdk.android.demo.R;

public class MainActivity extends Activity {

	public GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		DisplayMetrics dm = new DisplayMetrics();  
		getWindowManager().getDefaultDisplay().getMetrics(dm);  
		int width = dm.widthPixels;  
		int height = dm.heightPixels;
		gameView = new GameView(this, width, height,MainActivity.this);
		this.setContentView(gameView);
		GameDto.bgMusic = new Music(this,R.raw.tt);
		GameDto.moveMusic = new Music(this, R.raw.move);
		GameDto.loseMusic = new Music(this, R.raw.lose);
		GameDto.removeMusic = new Music(this, R.raw.delete1);
		if(GameDto.isBgMusic){
			GameDto.bgMusic.startMusic();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		GameDto.bgMusic.puase();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(GameDto.isBgMusic){
			GameDto.bgMusic.startMusic();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		gameView.onKey(gameView, keyCode, event);
		return super.onKeyDown(keyCode, event);
	}
}


