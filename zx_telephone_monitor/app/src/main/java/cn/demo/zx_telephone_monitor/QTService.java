package cn.demo.zx_telephone_monitor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class QTService extends Service {
    public QTService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tm.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);
    }

    class MyPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state){

                case TelephonyManager.CALL_STATE_IDLE://空闲状态
                    System.out.println("关闭录音机，上传录音文件到服务器。。。。。。。。。。。。");
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK://响铃状态
                    System.out.println("开始录音。。。。。。。。。。。。");
                    break;

                case TelephonyManager.CALL_STATE_RINGING://响铃状态
                    System.out.println("准备录音。。。。。。。。。。。。");
                    break;

                default:break;
            }
        }
    }
}
