package cn.demo.zx_service_lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class TestService extends Service {

    /**
     * 绑定服务的时候调用此方法
     * @param intent
     * @return
     */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind。。。。。。。。。。");
        return null;
    }

    /**
     * 移除绑定的时候调用此方法
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind。。。。。。。。。。");
        return super.onUnbind(intent);
    }


    /**
     * 服务第一次创建的时候调用此方法，服务创建完成之后，就不会在创建了
     */
    @Override
    public void onCreate() {
        System.out.println("onCreate。。。。。。。。。。");
        super.onCreate();

    }

    /**
     * 启动服务的时候调用此方法，服务可以被多次启动
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand。。。。。。。。。。");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务被销毁的时候调用
     */
    @Override
    public void onDestroy() {
        System.out.println("onDestroy。。。。。。。。。。");
        super.onDestroy();
    }
}
