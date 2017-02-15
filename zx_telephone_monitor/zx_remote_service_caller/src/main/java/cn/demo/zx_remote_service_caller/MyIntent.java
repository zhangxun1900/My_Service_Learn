package cn.demo.zx_remote_service_caller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 15:05
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 * Created by xun on 2017/2/14.
 */

public class MyIntent {

    public static Intent createExplicitFromImplicitIntent(Context context,Intent implicitIntent){

        /**
         * 获取包管理器
         * 然后通过包管理器获取所有的能够匹配implicitIntent意图的服务入口信息
         */
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentServices(implicitIntent, 0);

        if(resolveInfos==null || resolveInfos.size()!=1){
            return null;
        }

        /**
         * 获取其中一个匹配的服务入口信息
         * 然后通过这个入口信息获取包名和服务的名称
         * 然后通过包名和服务名称创建一个组件
         * 这个组件包含服务所在项目的包名和服务的名称。
         */
        ResolveInfo resolveInfo = resolveInfos.get(0);
        String packageName = resolveInfo.serviceInfo.packageName;
        String name = resolveInfo.serviceInfo.name;
        ComponentName componentName = new ComponentName(packageName,name);

        /**
         * 根据原先意图在创建一个Intent，然后给这个意图加上要打开的组件信息
         * 通过这个Intent就能打开指定包名和服务名称的服务了
         */
        Intent intent = new Intent(implicitIntent);
        intent.setComponent(componentName);




        return intent;
    }
}
