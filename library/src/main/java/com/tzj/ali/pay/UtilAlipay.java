package com.tzj.ali.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;

import java.util.Map;

/**
WebSettings settings = mWebView.getSettings();
 settings.setRenderPriority(RenderPriority.HIGH);
 settings.setJavaScriptEnabled(true);
 settings.setSavePassword(false);
 settings.setJavaScriptCanOpenWindowsAutomatically(true);
 settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
 settings.setAllowFileAccess(false);
 settings.setTextSize(WebSettings.TextSize.NORMAL);
 mWebView.setVerticalScrollbarOverlay(true);
 mWebView.setWebViewClient(new MyWebViewClient());
 mWebView.loadUrl(url);
 */
public class UtilAlipay {
    public static void pay(Activity act,String orderInfo,PayCallback calback){
        new Pay()
                .setOrderInfo(orderInfo)
                .setAct(act)
                .setCallBack(calback)
                .start();
    }

    public static boolean h5Pay(Activity act,String url,final PayCallback callback){
        return new PayTask(act).payInterceptorWithUrl(url, true, new H5PayCallback() {
            @Override
            public void onPayResult(H5PayResultModel h5PayResultModel) {
                if (callback!=null){
                    callback.threadCall(new PayResult(h5PayResultModel));
                }
            }
        });
    }

    public static abstract class PayCallback{
        private void threadCall(final PayResult result){
            if (Looper.myLooper() == Looper.getMainLooper()){
                CallBack(result);
            }else{
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        CallBack(result);
                    }
                });
            }
        }
        public abstract void CallBack(PayResult result);
    }

    private static class Pay extends Thread{
        private  String orderInfo;   // 订单信息
        private Activity act;
        private PayCallback callback;

        public Pay setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
            return this;
        }
        public Pay setAct(Activity act) {
            this.act = act;
            return this;
        }
        public Pay setCallBack(PayCallback callback) {
            this.callback = callback;
            return this;
        }
        @Override
        public void run() {
            Activity activity = act;
            PayCallback call = callback;
            if(activity!=null && call!=null){
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo,true);
                call = callback;
                if(call!=null){
                    call.threadCall(new PayResult(result));
                }
            }
        }
    }
}
