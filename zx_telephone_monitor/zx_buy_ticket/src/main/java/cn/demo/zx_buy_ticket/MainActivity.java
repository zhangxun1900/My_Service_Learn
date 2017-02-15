package cn.demo.zx_buy_ticket;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent = null;
    public MyServiceConnection conn = null;

    public IService tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this,RailwaySectorService.class);
        conn = new MyServiceConnection();
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    public void buyTicket(View view){

        tw.sellTickets(360.0);
//        tw.sellDrinks(4.0);
    }

    class MyServiceConnection implements ServiceConnection {

        /**
         * 当RailwaySectorService中的onBind被调用，并且返回TicketWindow的对象时候，才调用
         * @param name
         * @param service  TicketWindow,售票窗口
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //因为RailwaySectorService中的TicketWindow是私有的，所以只能转换成它的接口IService
            //接口IService中只有卖票的方法
            tw = (IService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
