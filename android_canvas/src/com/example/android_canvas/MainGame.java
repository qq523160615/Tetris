package com.example.android_canvas;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.android_canvas.database.DataBase;
import com.example.android_canvas.dto.GameDto;

public class MainGame extends Activity {

	private Button btn_start;

	private Button btn_end;

	private Button btn_cfg;
	
	private Button btn_rank;
	
	private CheckBox checkBox_bg;
	
	private CheckBox checkBox_sp;
	
	private CheckBox checkBox_sh;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.maingame);
		GameDto.dataBase = new DataBase(this);
		GameDto.isBgMusic = true;
		GameDto.isSpMusic = true;
		GameDto.isShandow = true;
		
		this.btn_start = (Button) this.findViewById(R.id.button1);
		this.btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainGame.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});


		this.btn_end = (Button) this.findViewById(R.id.button4);
		this.btn_end.setOnClickListener(new OnClickListener(
				) {

			@Override
			public void onClick(View v) {
				System.exit(0);
			}
		});



		this.btn_cfg = (Button) this.findViewById(R.id.button2);
		this.btn_cfg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createCustomDialog1();
			}
		});
		
		
		this.btn_rank = (Button) this.findViewById(R.id.button3);
		this.btn_rank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainGame.this, RankActivity.class);
				startActivity(intent);
			}
		});
		
		
	}
	
		

	public void createCustomDialog1(){  
		final Dialog dialog1 = new Dialog(this);  
		dialog1.setTitle("游戏设置:");  
		dialog1.show();  
		LayoutInflater inflater = LayoutInflater.from(this);
		View v = inflater.inflate(R.layout.dialog, null);
		dialog1.setContentView(v);  
		checkBox_bg = (CheckBox) v.findViewById(R.id.checkBox1);
		checkBox_sp = (CheckBox) v.findViewById(R.id.checkBox2);
		checkBox_sh = (CheckBox) v.findViewById(R.id.checkBox3);
		checkBox_bg.setChecked(GameDto.isBgMusic);
		checkBox_sp.setChecked(GameDto.isSpMusic);
		checkBox_sh.setChecked(GameDto.isShandow);
		Button btn_ok = (Button) v.findViewById(R.id.cfg1);
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!checkBox_bg.isChecked()){
					GameDto.isBgMusic = false;
				}
				if(!checkBox_sp.isChecked()){
					GameDto.isSpMusic = false;
				}
				if(!checkBox_sh.isChecked()){
					GameDto.isShandow = false;
				}
				dialog1.dismiss();
			}
		});
		
	} 
}
