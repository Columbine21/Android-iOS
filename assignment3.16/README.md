# Assignment one 

Date: 2020/3/16

## 作业要求

-   创建一个Android app工程，包含一个activity（环境可以参考 demo）

-   使用5种以上的View并实现一些简单交互

-   ImageView, Button, TextView, RadioButton, CheckBox, EditText, ProgressBar, SeekBar, Switch 等等不限

-   将一些交互结果输出log

-   打包生成apk

## 开发环境 

**IDE** ：Android Stadio 3.6.1

**PM Emulator** ：Nexus 5X API R （29型） 

## 作业功能简介

-   使用控件 ImageView, Button, TextView, EditText, ProgressBar.

-   功能简介：在模拟器中打开我们编写的App。效果如下：

    <img src="./image/3_16_figure1.png" style="zoom:50%;" />

-   交互功能说明：

    1.  首先使用图中“小智”头像旁边的Switch控件，点击可以切换头像（切换成女版头像）

        <img src="./image/3_16_figure2.png" style="zoom:50%;" />

    2.  点击Login button启动环形Processbar，设置多线程完成Processbar的更新功能。同时用户点击登陆按钮后冻结LoginButton，不允许用户多次点击 Login。

        <img src="./image/3_16_figure3.png" style="zoom:50%;" />

    3.  在Processbar达到最大值后（设置为10s）Login接触冻结，并且将processbar和LoadingText隐藏。

        （效果同图一）

-   交互输出：

    -   点击Switch切换头像时使用

        ```java
         Log.e("MainActivate", "onCheckedChanged: change the image")
        ```

    -   点击Login button时使用

        ```java
        Log.e("MainActivate", "onClick: showing processbar");
        ```

    -   etc.

-   打包生成apk

    Generate apk 并添加签名：

    ```shell
    keytool -genkey -v -keystore android.keystore -alias androidtest -keyalg RSA -validity 365
    ```

-   项目地址：https://github.com/Columbine21/Android-iOS/tree/master/assignment3.16

 
