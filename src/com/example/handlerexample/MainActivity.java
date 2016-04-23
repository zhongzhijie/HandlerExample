package com.example.handlerexample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {

	int [] imageIds = new int[]
	{
			R.drawable.draw1,
			R.drawable.draw2,
			R.drawable.draw3,
			R.drawable.draw4,
			R.drawable.draw5,
			R.drawable.draw6
	};
	int currentImageId = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView show = (ImageView)findViewById(R.id.iv01);
		final Handler myHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x1233)
				{
					// 修改显示的图片
					show.setImageResource(imageIds[currentImageId++]);
					if (currentImageId >= 6)
					{
						currentImageId = 0;
					}
				}
			}
		};
		
		new Timer().schedule(new TimerTask()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 新线程无法访问Activity界面组件
				Message msg = new Message();
				msg.what = 0x1233;
				myHandler.sendMessage(msg);
			}
			
		}, 0, 800);
	}
}
