package cn.demo.zx_music_app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.demo.zx_music_app.domain.MusicInfo;
import cn.demo.zx_music_app.service.IService;
import cn.demo.zx_music_app.service.MusicService;

import static cn.demo.zx_music_app.R.id.tv;

public class MainActivity extends AppCompatActivity {

    public ListView lv = null;

    public List<MusicInfo>list = null;


    public Intent intent = null;
    public MusicServiceConnection conn = null;

    public IService mIService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        initData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIService.playMusic(list,position);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    class MusicServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIService = (IService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new MusicInfo("国歌","/mnt/sdcard/国歌.mp3"));
        list.add(new MusicInfo("老男孩","/mnt/sdcard/老男孩.mp3"));
        list.add(new MusicInfo("恋曲1990","/mnt/sdcard/恋曲1990.mp3"));
        list.add(new MusicInfo("沧海一声笑","/mnt/sdcard/沧海一声笑.mp3"));
        list.add(new MusicInfo("大海","/mnt/sdcard/大海.mp3"));
        list.add(new MusicInfo("海阔天空","/mnt/sdcard/海阔天空.mp3"));
        list.add(new MusicInfo("水手","/mnt/sdcard/水手.mp3"));


        lv.setAdapter(new ArrayAdapter<MusicInfo>(this, R.layout.item, tv,list));

        intent = new Intent(MainActivity.this, MusicService.class);
        startService(intent);

        conn = new MusicServiceConnection();
        bindService(intent,conn,BIND_AUTO_CREATE);
    }
}
