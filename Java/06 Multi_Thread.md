# Multi_Thread

1. 单线程与多线程

   比如我们的main函数主线程，只有通过调用方法，但是在调用的过程中主函数也不能向下运行，我们能不能在调用函数的同时，还能继续向下运行呢？

2. 进程和线程

   可以打个比方，一个视频浏览器是一个进程，里面的声音图像字幕都是由不同的线程去负责的

   真正的多线程是多核，很多多线程是模拟出来的，因为切换很快

   对同一份资源进行抢夺的时候需要加入并发控制

   默认线程，main函数和gc线程

   ![继承](https://img-blog.csdn.net/20141104001043649?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2hvc2tlbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

![](https://img-blog.csdn.net/20141104001057393?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2hvc2tlbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

Thread 类实现了Runable接口，继承Object类

所以线程的第一种创建方法时，继承Thread类重写Runable接口中带有的run方法，编写线程执行体，将对象new出来，调用start()方法启用线程

第二种是写实现runuable接口的类，并且传入Thread对象

第三种方法是实现callable接口

1. 实现callable接口需要返回值类型
2. 重写call方法需要抛出异常
3. 创建目标对象
4. 创建执行服务ExecutorService ser  = Executors.newFixedThreadPool(1);
5. 提交执行：Future<Boolean> result1 = ser.submit(t1);
6. 获取结果：boolean r1 = result1.get();
7. 关闭服务：ser.shutDownNow();

可以定义返回值，可以抛出异常

### 静态代理

两个类同时实现一个接口，代理类为实际使用的类添加一些辅助方法，见代码TestCreatThread04

### Lamda表达式

一个接口里面只有一个方法叫做函数式接口，是对匿名内部类的进一步简化，

like = ()->{接口内方法实现};

如果只有一行可以去掉花括号

### 线程5大状态

1. 创建
2. 就绪
3. 阻塞
4. 运行
5. 死亡

### 常用方法

1. setPriority(int newPriority) 更改线程的优先级
2. static void sleep(long millis) 让当前执行的线程指定时间内休眠
3. void join() 等待该线程终止
4. static void yield()暂停当前正在执行的线程，并执行其他县城
5. void interrupt() 中断线程，不推荐
6. boolean isAlive()测试线程是否处于活动状态

#### 停止线程

- 不推荐使用JDK提供的stop destroy方法
- 推荐线程自己停止
- 建议使用一个标志位进行终止变量，当flag = false，则终止线程运行

 #### 线程休眠

- sleep时间制定当前线程阻塞的毫秒数
- sleep存在异常InterruptedException
- sleep时间达到后线程进入就绪状态
- sleep可以模拟网络延时，倒计时等
- 每一个对象都有一个锁，sleep不会释放锁
- 可以模拟倒计时等

#### 线程礼让

- 只是重新竞争，让当前正在执行的线程退回就绪状态
- CPU重新调度，礼让不一定成功，看CPU心情

#### Join

- Join合并线程，待此线程执行完后才能执行其他线程，其他线程阻塞
- 可以想象成插队

#### 线程的优先级

- 按照优先级调度
- 优先级从1到10，10最高
- get setPriority获取设置优先级

#### 守护线程（daemon）

- 线程分为用户线程和守护线程
- 虚拟机必须确保用户线程执行完毕
- 虚拟机不会等待守护线程执行完毕
- 如后台记录操作日志，监控内存，垃圾回收等待

#### 线程同步

1. 并发：同一个对象被多个线程共同操作
2. 最天然的解决办法就是排队，多个线程访问同一个对象，我们就需要线程同步，线程同步其实就是一种等待机制，对各需要同时访问此对象的线程进入这个对象的等待池形成队列，等待前面线程使用完毕，下一个线程在使用

3. 每个对象都有一把锁
4. 保证安全损失性能

- 方法一，synchronize关键字修饰方法，默认锁自己这个对象
- 方法二，synchronize块

JUC并发包：java.util.concurrent包下

CopyOnWriteArrayList

#### 死锁

- 多个对象同时抱着对方需要的资源，形成僵持

#### 显式锁Lock

ReentrantLock lock unlock

#### 线程通信

Object类中有wait notify方法

#### 线程池

