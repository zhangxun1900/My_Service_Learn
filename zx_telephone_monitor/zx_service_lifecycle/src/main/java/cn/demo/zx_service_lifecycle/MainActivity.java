package cn.demo.zx_service_lifecycle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent = null;

    public MyServiceConnect conn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this,TestService.class);

    }

    /**
     * 开启服务
     * @param v
     */
    public void startService(View v){
        startService(intent);
    }

    /**
     * 停止服务
     */
    public void stopService(View v){
        stopService(intent);
    }

    /**
     * 绑定服务
     * @param v
     */
    public void bindService(View v){
        conn = new MyServiceConnect();
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    /**
     * 移除绑定
     * @param view
     */
    public void unbindService(View view){
        unbindService(conn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    class MyServiceConnect implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
