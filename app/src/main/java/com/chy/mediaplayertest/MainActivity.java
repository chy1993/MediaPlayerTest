package com.chy.mediaplayertest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;

    private VideoView videoView ;
    /**
     * 设置view播放控制条
     */
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView)this.findViewById(R.id.videoView );

        //关于权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
        } else
        {
            //本地的视频 需要在手机SD卡根目录添加一个视频
            String videoUrl1 = "/sdcard/Download/KLSW_1.mp4" ;
//            Log.i("11111111111111111111111",videoUrl1);
            Uri uri = Uri.parse(videoUrl1);
            //设置视频控制器
            mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            //设置显示控制条
            mediaController.show(1);
            //播放完成回调
            videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());

            //设置视频路径
            videoView.setVideoURI(uri);

            //开始播放视频
            videoView.start();
        }

    }


    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( MainActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
