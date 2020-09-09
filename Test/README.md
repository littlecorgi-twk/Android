# Test
平时Android的测试代码，具体测试项目可以看每次的Commit

## BroadcastTest
测试广播：
- 静态注册广播接收器，然后发送隐式广播（收不到）  
- 静态注册广播接收器，然后发送显示广播（收到）  
- 动态注册广播接收器，发送广播

## CustomView
自定义View
- FlowLayout：流式布局  
- ZEditText:验证码输入View，[ZEditText：自定义密码输入框](https://blog.csdn.net/u011315960/article/details/107918423)

## EventBusTest
EventBus相关测试：
- EventBus用法
- EventBus粘性事件

## LeakCanaryTest
通过LeakCanary检测内存泄漏测试：
- HandlerLeakMemoryActivity：检测Handler引起的内存泄漏  
- EventBusLeakMemoryActivity：检测EventBus引起的内存泄漏

## LifecycleTest
关于生命周期和Lifecycle的相关测试：
- Activity全部生命周期调用顺序
- Lifecycle简单使用
- 当Activity跳转时两个Activity的生命周期顺序
- 当Activity返回时两个Activity的生命周期顺序

## MVVMTest
Jetpack实现MVVM
- LiveData和ViewModel的基本使用