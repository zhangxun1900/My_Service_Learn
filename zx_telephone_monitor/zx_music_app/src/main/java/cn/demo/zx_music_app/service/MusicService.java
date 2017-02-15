package cn.demo.zx_music_app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import cn.demo.zx_music_app.domain.MusicInfo;

public class MusicService extends Service {

    public boolean isStop = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    /**
     * 播放音乐
     * @param list
     */
    public void play(final List<MusicInfo>list, final int position){
        System.out.println("正在播放："+list.get(position));
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!isStop) {
                    play(list, (position + 1) % list.size());
                }
            }
        }.start();
    }

    private class MusicBinder extends Binder implements IService {
        public void playMusic(List<MusicInfo>list,int position){
            play(list,position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }
}
