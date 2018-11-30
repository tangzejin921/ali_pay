# AliPayPlugin

阿里支付 flutter_plugin

## Getting Started

For help getting started with Flutter, view our online
[documentation](https://flutter.io/).

For help on editing plugin code, view the [documentation](https://.io/platform-plugins/#edit-code).flutter


##  目录结构被我改动的了

settings.gralde 文件中
```gradle
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

project工程的 build.gradle 中
```gradle
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