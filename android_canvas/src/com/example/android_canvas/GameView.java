package com.example.android_canvas;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_canvas.control.GameControl;
import com.example.android_canvas.dto.GameDto;
import com.example.android_canvas.entity.Box;
import com.example.android_canvas.ui.Layer;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.demo.R;
import com.weibo.sdk.android.sso.SsoHandler;
import com.weibo.sdk.android.util.AccessTokenKeeper;

@SuppressLint({ "ViewConstructor", "DrawAllocation", "SimpleDateFormat" })
public class GameView extends View implements OnKeyListener{

	public Bitmap pic_bg = ((BitmapDrawable)getResources().getDrawable(R.drawable.bg)).getBitmap();
	public Bitmap pic_window = ((BitmapDrawable)getResources().getDrawable(R.drawable.window)).getBitmap();
	public Bitmap pic_rect = ((BitmapDrawable)getResources().getDrawable(R.drawable.rect)).getBitmap();
	public Bitmap pic_gameover = ((BitmapDrawable)getResources().getDrawable(R.drawable.gameover)).getBitmap();
	public Bitmap pic_next = ((BitmapDrawable)getResources().getDrawable(R.drawable.next)).getBitmap();
	public Bitmap pic_point = ((BitmapDrawable)getResources().getDrawable(R.drawable.point)).getBitmap();
	public Bitmap pic_level = ((BitmapDrawable)getResources().getDrawable(R.drawable.level)).getBitmap();
	public Bitmap pic_shadow = ((BitmapDrawable)getResources().getDrawable(R.drawable.shadow)).getBitmap();
	private Bitmap next[] = {((BitmapDrawable)getResources().getDrawable(R.drawable.one)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.two)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.three)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.four)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.five)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.six)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.seven)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.eight)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.nine)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.ten)).getBitmap()};

	private Bitmap button[]={((BitmapDrawable)getResources().getDrawable(R.drawable.up)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.down)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.left)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.right)).getBitmap(),
			((BitmapDrawable)getResources().getDrawable(R.drawable.fast)).getBitmap()};

	private List<Layer> layers= null;


	private Box box = null;

	Layer layer = null;

	GameDto gameDto = null;

	GameControl gameControl =null;

	private Context context;

	private Activity activity;

	/** 显示认证后的信息，如AccessToken */
	private TextView mText;

	/** 微博API接口类，提供登陆等功能 */
	private Weibo mWeibo;

	/** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能 */
	private Oauth2AccessToken mAccessToken;

	/** 注意：SsoHandler 仅当sdk支持sso时有效 */
	private SsoHandler mSsoHandler;

	private Intent intent;

	@SuppressWarnings("static-access")
	public GameView(Context context,int width,int height,Activity activity) {
		super(context);
		this.context = context;
		this.activity = activity;
		gameDto = new GameDto();
		initLayer();
		gameDto.setPic_bg(pic_bg);
		gameDto.setPic_window(pic_window);
		gameDto.setPic_rect(pic_rect);
		gameDto.setNext(next);
		gameDto.setButton(button);
		gameDto.setPic_gameover(pic_gameover);
		gameDto.setPic_next(pic_next);
		gameDto.setPic_point(pic_point);
		gameDto.setPic_level(pic_level);
		gameDto.setShandow(pic_shadow);
		gameDto.setWidth(width);
		gameDto.setHeight(height);
		box = new Box();
		gameDto.setBox(box);
		gameDto.flag = true;
		gameDto.pause = true;
		gameDto.point=0;
		gameDto.level = 1;
		gameDto.typeNum = 7;
		this.gameControl = new GameControl(box, gameDto, this,context);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//	layers.get(4).drawImage(canvas, pic_bg, 0, 0, 1280, 800, 0, 0, width, height);
		for(int i = layers.size()- 1;i >= 0 ;i--){
			//ˢ�²㴰��
			layers.get(i).setGameDto(gameDto);
			layers.get(i).point(canvas);
		}
		if(!gameDto.flag){
			savePoint();
		}
		if(gameDto.level > 4 && gameDto.level < 10 )
			gameDto.typeNum = 8;
		if(gameDto.level > 10 && gameDto.level < 20 )
			gameDto.typeNum = 9;
		if(!gameDto.flag && gameDto.isSpMusic){
			gameDto.loseMusic.startMusic();
		}
	}

	public void initLayer(){
		layers = new ArrayList<Layer>(6); 
		for (int i = 0; i < 6; i++) {

			try {
				Class<?> cls = Class.forName(GameDto.layerName[i]);
				Constructor<?> ctr = cls.getConstructor(int.class,int.class,int.class,int.class);
				Layer layer = (Layer)ctr.newInstance(
						gameDto.windowSize[i][0],gameDto.windowSize[i][1],gameDto.windowSize[i][2],gameDto.windowSize[i][3]
						);
				layers.add(layer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.gameControl.onTouch(this, event);
		return super.onTouchEvent(event);


	}

	public Bitmap getPic_bg() {
		return pic_bg;
	}

	public Bitmap getPic_window() {
		return pic_window;
	}

	public Bitmap getPic_rect() {
		return pic_rect;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			// �����˳��Ի���  
			AlertDialog isExit = new AlertDialog.Builder(context).create();  
			// ���öԻ������  
			isExit.setTitle("ϵͳ��ʾ");  
			// ���öԻ�����Ϣ  
			isExit.setMessage("ȷ��Ҫ�˳���");  

			// ��ʾ�Ի���  
			isExit.show();  
		}
		return super.onKeyDown(keyCode, event);
	}


	@SuppressWarnings("static-access")
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		gameDto.pause = false;
		gameDto.bgMusic.puase();
		if(keyCode == event.KEYCODE_BACK){	
			AlertDialog.Builder customBuilder = new  
					AlertDialog.Builder(context);  
			customBuilder.setTitle("返回游戏")  
			.setMessage("是否返回主界面")  
			.setNegativeButton("取消",   
					new DialogInterface.OnClickListener() {  
				public void onClick(DialogInterface dialog, int which) {  
					gameDto.pause = true;
					gameDto.bgMusic.startMusic();
				}  
			})  
			.setPositiveButton("确定",   
					new DialogInterface.OnClickListener() {  
				public void onClick(DialogInterface dialog, int which) {  
					gameDto.pause = true;
					Intent intent = new Intent(context,MainGame.class);
					context.startActivity(intent);
					System.exit(0);
				}  
			});  
			customBuilder.create();
			customBuilder.show();
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public void savePoint(){
		final Dialog dialog = new Dialog(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.savepoint, null);
		final EditText editText = (EditText) v.findViewById(R.id.editText1);
		TextView textView = (TextView) v.findViewById(R.id.textView3);
		textView.setText(Integer.toString(gameDto.point));
		Button button = (Button) v.findViewById(R.id.button1);
		dialog.setContentView(v);
		dialog.setTitle("保存分数:");  
		dialog.show();  
		takeScreenShot();
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = editText.getText().toString();
				SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");       
				String    date  =  sDateFormat.format(new    java.util.Date()); 
				gameDto.dataBase.AddData(name, gameDto.point,date);
				dialog.dismiss();
				Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show();
			}
		});

		Button btn_share = (Button) v.findViewById(R.id.btn_share);
		btn_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mWeibo = Weibo.getInstance(ConstantS.APP_KEY, ConstantS.REDIRECT_URL,
						ConstantS.SCOPE);
				mSsoHandler = new SsoHandler(activity, mWeibo);
				mSsoHandler.authorize(new AuthDialogListener(), null);
				Intent intent = new Intent();
				intent.setClass(activity, ShareActivity.class);
				activity.startActivity(intent);
			}
		});
	}

	// 获取指定Activity的截屏，保存到png文件
	public Bitmap takeScreenShot() {
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b = view.getDrawingCache();
		view.destroyDrawingCache();
		return b;
	}

	/**
	 * 微博认证授权回调类。 1. SSO登陆时，需要在{@link #onActivityResult}
	 * 中调用mSsoHandler.authorizeCallBack后， 该回调才会被执行。 2. 非SSO登陆时，当授权后，就会被执行。
	 * 当授权成功后，请保存该access_token、expires_in等信息到SharedPreferences中。
	 */
	class AuthDialogListener implements WeiboAuthListener {

		@Override
		public void onComplete(Bundle values) {

			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			mAccessToken = new Oauth2AccessToken(token, expires_in);
			if (mAccessToken.isSessionValid()) {
				String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(new java.util.Date(mAccessToken
						.getExpiresTime()));
				mText.setText("认证成功: \r\n access_token: " + token + "\r\n"
						+ "expires_in: " + expires_in + "\r\n有效期：" + date);

				AccessTokenKeeper.keepAccessToken(activity,
						mAccessToken);
				Toast.makeText(activity, "认证成功", Toast.LENGTH_SHORT)
				.show();
			}
		}

		@Override
		public void onError(WeiboDialogError e) {
			Toast.makeText(activity.getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(activity.getApplicationContext(), "Auth cancel",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(activity.getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}

}
