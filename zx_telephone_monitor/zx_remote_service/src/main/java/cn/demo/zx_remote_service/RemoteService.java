package cn.demo.zx_remote_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteBinder();
    }


    public void method(){
        System.out.println("调用远程服务。。。。。。。。。。。。。");
    }

    /**
     *
     */
    private class RemoteBinder extends IService.Stub {
        public void callMethod(){
            method();
        }
    }
}
