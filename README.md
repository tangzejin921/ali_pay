# 阿里支付
flutter 调用 alipay

## 为什么要做下面的 工程引入
    因为工程结构被我改了，

    原来是：
    android
    ios
    lib
    pubspec.yaml

    被我改为了
    android
        src
            main
            flutter
                pubspec.yaml

    所以要做一些改动


## 工程引入
- pub 加入
    ```pub
    dev_dependencies:
      ali_pay:
        git:
          url: git://github.com/tzjandroid/ali_pay.git
          path: ali_pay/src/flutter
    ```
- android 工程下的 settings.gradle 中改为如下
    ```Gradle
    def flutterProjectRoot = rootProject.projectDir.parentFile.toPath()

    def plugins = new Properties()
    def pluginsFile = new File(flutterProjectRoot.toFile(), '.flutter-plugins')
    if (pluginsFile.exists()) {
        pluginsFile.withReader('UTF-8') { reader -> plugins.load(reader) }
    }

    plugins.each { name, path ->
        def pluginDirectory = flutterProjectRoot.resolve(path).resolve('android').toFile()
        if(!pluginDirectory.exists()){
            pluginDirectory = flutterProjectRoot.resolve(path).getParent().getParent().toFile()
        }
        if(pluginDirectory.exists()){
            include ":$name"
            project(":$name").projectDir = pluginDirectory
        }
    }
    ```
- android 工程下的 build.gradle  加入
    ```Gradle
    rootProject.extensions.add("ali_pay",Type.isFlutterPlugin.name())
    enum Type{
        isAPP,
        isModule,
        isFlutterPlugin;
    }
    project.ext {
        ext._compileSdkVersion = 27
        ext._buildToolsVersion = '27.0.3'
        ext._minSdkVersion = 16
        ext._targetSdkVersion = 27
        ext._supportVersion = "27.1.1"
        ext.javaVersion = JavaVersion.VERSION_1_8
    }
    ```


## example
example 目录有个 demo

# android 集成alipay步骤
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
    