package cn.demo.zx_buy_ticket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 17:16
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 * Created by xun on 2017/2/13.
 */

public class RailwaySectorService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TicketWindow();
    }

    /**
     * 提供票的方法
     */
    public void ticket(){
        Toast.makeText(this, "给，您的票，祝你旅途愉快。。。。。。。。。。", Toast.LENGTH_SHORT).show();
    }

    /**
     * TicketWindow就是售票窗口，它可以提供卖票和卖饮料的方法。
     * 但是实际中售票窗口只能售票，不能卖饮料。卖饮料是售票窗口自己的方法，不是铁路部门给他的方法。
     * 这里铁路部门通过IService接口和将该类定义成private给它做了限制，它只能卖票
     *
     */
    private class TicketWindow extends Binder implements IService{

        /**
         * 卖票
         * @param money
         */
        public void sellTickets(double money){
            if(money<350.0){
                Toast.makeText(RailwaySectorService.this, "还差"+(350-money)+"元", Toast.LENGTH_SHORT).show();
            }else{
                ticket();
            }
        }

        /**
         * 卖饮料
         * @param money
         */
        public void sellDrinks(double money){
            if(money<3.0){
                Toast.makeText(RailwaySectorService.this, "这些钱不够买饮料的", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RailwaySectorService.this, "这是你的饮料", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
