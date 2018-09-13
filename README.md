# 阿里支付
## 网址
    https://docs.open.alipay.com/204/105296/
## 导入开发资源
    dependencies {
        ......
        compile files('libs/alipaySdk-20170725.jar')
        ......
    }
## 修改Manifest
    <activity
        android:name="com.alipay.sdk.app.H5PayActivity"
        android:configChanges="orientation|keyboardHidden|navigation|screenSize"
        android:exported="false"
        android:screenOrientation="behind"
        android:windowSoftInputMode="adjustResize|stateHidden" >
    </activity>
     <activity
        android:name="com.alipay.sdk.app.H5AuthActivity"
        android:configChanges="orientation|keyboardHidden|navigation"
        android:exported="false"
        android:screenOrientation="behind"
        android:windowSoftInputMode="adjustResize|stateHidden" >
    </activity>
    和权限声明：
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
## 添加混淆规则
    -keep class com.alipay.android.app.IAlixPay{*;}
    -keep class com.alipay.android.app.IAlixPay$Stub{*;}
    -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
    -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
    -keep class com.alipay.sdk.app.PayTask{ public *;}
    -keep class com.alipay.sdk.app.AuthTask{ public *;}
    -keep class com.alipay.sdk.app.H5PayCallback {
        <fields>;
        <methods>;
    }
    -keep class com.alipay.android.phone.mrpc.core.** { *; }
    -keep class com.alipay.apmobilesecuritysdk.** { *; }
    -keep class com.alipay.mobile.framework.service.annotation.** { *; }
    -keep class com.alipay.mobilesecuritysdk.face.** { *; }
    -keep class com.alipay.tscenter.biz.rpc.** { *; }
    -keep class org.json.alipay.** { *; }
    -keep class com.alipay.tscenter.** { *; }
    -keep class com.ta.utdid2.** { *;}
    -keep class com.ut.device.** { *;}
## 支付接口调用
    请看 UtilAlipay
    
# 后台配置
    