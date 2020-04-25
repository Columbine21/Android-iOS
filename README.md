# 2020-Spring 移动互联网技术及应用 课程作业

致谢： **本课程由 段鹏瑞老师 以及字节跳动 安卓/iOS开发人员讲授** 

## 开发环境

**IDE** ：Android Stadio 3.6.1

**PM Emulator** ：Nexus 5X API R （29型） 

## Lesson one 使用 Android Studio 开发 Android 程序

-   创建一个Android app工程，包含一个activity（环境可以参考 demo）

-   使用5种以上的View并实现一些简单交互

-   ImageView, Button, TextView, RadioButton, CheckBox, EditText, ProgressBar, SeekBar, Switch 等等不限

-   将一些交互结果输出log

-   打包生成apk

## Lesson two Android基础UI开发

-   Android `Activity` 生命周期实验，在界面和`logcat` 中同时显示 `Activity` 生命周期活动（`onCreate, onStart, onResume, onPause, onStop, onDestroy, onSaveInstanceState, onRestoreInstanceState, etc`）
-   抖音面试题：求一个 `Activity` 中的所有的组件个数，并用`TextView`显示。
-   实现一个类似抖音消息页面，并且点击每个 `RecycleView` 的 item，能够跳转到一个新的界面，并且在新的页面显示出他是第几个 item。

## Lesson three 界面开发 Animator & Fragment

-   引入 Lottie 库实现简单的图标动画。
-   使用属性动画，练习 `AnimatorSet` 和 `scale`/`fade` 等基本动画样式。
-   使用 `ViewPager` 和 `Fragment` 做一个简单版的好友列表界面。
-   生成的 apk 位置 release 中 v3。

## Lesson four handler多线程&自定义View绘制

-   完成时钟功能，每一秒钟秒针进行跳动（handler方法和postInvalidataDelayed(1000);方法）
-   完成绘制表盘功能。

## Lesson five Network(http:Get/Post) 

-   RESTful (Representational State Transfer) 
-   JSON / GSON 文件格式&解析
-   使用 Retrofit2 构造网络的get / post 请求

