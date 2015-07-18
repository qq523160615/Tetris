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
					/*�����ļ���������¼�*/ 
					public void onCompletion(MediaPlayer arg0)   
					{   
						try   
						{   
							/*�����Դ��MediaPlayer�ĸ�ֵ��ϵ  
							 * ����Դ����Ϊ������������*/ 
							mediaPlayer.release();   
							/*�ı�TextViewΪ���Ž���*/ 
						}   
						catch (Exception e)   
						{   
							e.printStackTrace();   
						}   
					}   
				});   

		/* ��MediaPlayer.OnErrorListener�����е�Listener */ 
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()  
		{  
			@Override 
			/*���Ǵ������¼�*/ 
			public boolean onError(MediaPlayer arg0, int arg1, int arg2)  
			{  
				// TODO Auto-generated method stub  
				try 
				{  
					/*��������ʱҲ�����Դ��MediaPlayer�ĸ�ֵ*/ 
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
