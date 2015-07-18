package com.example.android_canvas.media;

import android.content.Context;
import android.media.MediaPlayer;

public class Music{


	private MediaPlayer mediaPlayer;

	private boolean isPaused = false;  

	@SuppressWarnings("static-access")
	public Music(Context context,int id){
		mediaPlayer = mediaPlayer.create(context,id);
	}

	public void startMusic(){
		try {       
			if(mediaPlayer != null)  
			{  
				mediaPlayer.stop();  
			}      
			mediaPlayer.prepare();  
			mediaPlayer.start();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}      
	}        

	public void puase(){
		try {  
			if(mediaPlayer !=null)  
			{  
				mediaPlayer.stop();  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

	}

	public void stop(){
		try {  
			if(mediaPlayer !=null)  
			{  
				if(isPaused==false)  
				{  
					mediaPlayer.pause();  
					isPaused=true;  
				}  
				else if(isPaused==true)  
				{  
					mediaPlayer.start();  
					isPaused = false;  
				}  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  


		mediaPlayer.setOnCompletionListener(  
				new MediaPlayer.OnCompletionListener()   
				{   
					// @Override   
					/*覆盖文件播出完毕事件*/ 
					public void onCompletion(MediaPlayer arg0)   
					{   
						try   
						{   
							/*解除资源与MediaPlayer的赋值关系  
							 * 让资源可以为其它程序利用*/ 
							mediaPlayer.release();   
							/*改变TextView为播放结束*/ 
						}   
						catch (Exception e)   
						{   
							e.printStackTrace();   
						}   
					}   
				});   

		/* 当MediaPlayer.OnErrorListener会运行的Listener */ 
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()  
		{  
			@Override 
			/*覆盖错误处理事件*/ 
			public boolean onError(MediaPlayer arg0, int arg1, int arg2)  
			{  
				// TODO Auto-generated method stub  
				try 
				{  
					/*发生错误时也解除资源与MediaPlayer的赋值*/ 
					mediaPlayer.release();  
				}  
				catch (Exception e)  
				{  
					e.printStackTrace();   
				}   
				return false;   
			}   
		});   
	}   
}
