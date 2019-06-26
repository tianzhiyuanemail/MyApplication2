package com.example.myapplication;

import android.app.Application;
import android.widget.Toast;

import com.ali.auth.third.core.callback.InitResultCallback;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;

import java.util.HashMap;

/**
 * Created by sospartan on 1/28/16.
 */
public class DemoApplication extends Application {

  public static DemoApplication mActivity;

  @Override
  public void onCreate() {
    super.onCreate();

     mActivity = this;
    /**
     * 初始化
     */
    AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
      @Override
      public void onSuccess() {
        // 初始化成功，设置相关的全局配置参数

        // 是否使用支付宝
        AlibcTradeSDK.setShouldUseAlipay(true);

        // 设置是否使用同步淘客打点
        AlibcTradeSDK.setSyncForTaoke(true);

        // 是否走强制H5的逻辑，为true时全部页面均为H5打开
        AlibcTradeSDK.setForceH5(true);

        // 设置全局淘客参数，方便开发者用同一个淘客参数，不需要在show接口重复传入
        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams();
        alibcTaokeParams.pid = "mm_114747138_45538443_624654015";
        alibcTaokeParams.subPid = "mm_114747138_45538443_624654015";
        alibcTaokeParams.adzoneid = "624654015";
        alibcTaokeParams.extraParams = new HashMap<>();
        alibcTaokeParams.extraParams.put("taokeAppkey", "24900413");//一定要是淘宝开放平台appKey

        AlibcTradeSDK.setTaokeParams(alibcTaokeParams);

        // 设置渠道信息(如果有渠道专享价，需要设置)
//        AlibcTradeSDK.setChannel(typeName, channelName);

        //初始化成功，设置相关的全局配置参数
        Toast.makeText(mActivity, "/初始化成功 ", Toast.LENGTH_LONG).show();
      }

      @Override
      public void onFailure(int code, String msg) {
        //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
        Toast.makeText(mActivity, "初始化失败 ", Toast.LENGTH_LONG).show();
      }
    });

  }


}
