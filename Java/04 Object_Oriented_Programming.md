# 面向对象编程

### 待完成内容

1. 类修饰符继承关系√
2. 接口内部类抽象类√
3. 集合及底层实现原理
4. Object类String类常见类函数√
5. 异常
6. 反射
7. IO机制
8. Comparator Comparable



### 面向过程&面向对象

- 面向过程：
  - 步骤清晰简单，第一步做什么，第二步做什么
  - 适合处理简单问题

- 面向对象：
  - 物以类聚，分类的方式，思考问题首先会解决问题如何分类，然后对分类进行独立思考，最后才会对某个分类下的细节进行面向过程的思考
  - 适合处理复杂多人协作的问题

- 面向对象编程的本质就是以类的方式组织代码，以对象的组织封装数据
- 提供了抽象
- 三大特性：
  - 封装
  - 继承
  - 多态

### 静态方法/非静态

- 静态方法类名可以直接调用
- 非静态方法必须先实例化再调用
- 如果a是静态方法，b不是静态方法，a无法调用b，因为b类实例化后才存在依赖于对象

### 类与对象

- 是一种抽象的数据类型，它是对某一类事物的整体描述/定义，但不代表某一个具体的事物
- 对象是抽象概念的具体事例
- 构造函数默认生成，没有返回值，且名字必须和类名相同，可以重载
- alt+insert 自动生成
- 类代码等先加载在方法区，main()执行的时候在栈底，当main执行完，整个程序结束
- 当发现调用了另一个类的时候，把它加载到方法区，生成具体对象，地址在栈里，实际的内存在堆中
- 方法区中有一块是静态方法区，所有对象都可以直接调用

### 封装

- 高内聚，低耦合，对内部数据细节自己完成，不允许外部干涉，低耦合，仅暴露少数方法给外部使用
- private属性，main函数里的对象无法直接访问，可以增加一些可以访问的方法属性私有，get，set
- 重载的合理使用可以使封装的比较完美

### 继承

- 本质是对某一批类的进一步抽象，从而对世界更好的建模，细化
- extends关键字
- JAVA中只支持单继承，一个儿子只能有一个爸爸
- 类和类的关系还有依赖，组合，聚合，继承是is a关系
- **子类继承继承规则**
  - public：公开的任何位置都可以使用
  - protected：受保护元素对象也无法直接访问，只能在本类，同包，子类中访问 
  - default：什么都不写空白，只能在本类以及同包下访问
  - private：私有的只能在本类中访问
  - 可以修饰属性，方法，类只能（public 和默认）接口同类87w

- Ctrl+H：可以看继承树
- java中所有类默认继承Object，java.lang包下
- super. 可以调用父类的属性函数，前提是非私有，this是自己类
- 构造对象先调用父类无参构造函数，在调用子类构造函数，是默认存在在子类构造器第一行的super()，所以一旦父类不存在无参构造函数，子类构造函数就会报错，不知道如何创建，除非显示调用父类有参构造函数且super()必须出现在第一行。super和this不能同时调用构造方法。使用this可以直接调用对象自己的构造方法。

### 重写Override

1. 重写都是针对函数的，与属性无关
2. A继承B类   B b = new A(); b.test()使用的会是父类的方法如果父类子类中都有一个静态test方法，父类的引用指向子类是不会编译错误的，因为有血缘关系，静态方法的调用只和声明即左边的B类型有关。
3. 但是如果方法不是静态的则该方法将被重写调用的将是实际子类中的test方法，最好加上@Override会有检查
4. 静态方法和非静态方法区别很大，静态方法方法调用只看左边的申明，非静态方法：可以重写，且重写的方法修饰符需要是public
5. 子类重写父类的方法，需要有继承关系，方法名和参数列表必须相同，修饰符方法可以扩大但不能缩小，public>Protected>Default>private 即private可以变成public
6. 抛出的异常范围可以被缩小但是不能扩大
7. 为什么需要重写：父类的功能，子类不一定需要或者满足；

### 多态

1. 实现了动态编译：使得可扩展性更强

2. 实际类型是确定的，但是可以指向的引用类型就不确定了，可以指向父类，换句话说父类的引用可以指向子类的对象

3. 子类可以使用父类的公有方法是因为继承，比如 Person s2 = new Student(); s2能够调用person类中的公有方法，如果子类中有重写该方法，非静态的话，会调用重写后的方法。但是如果s2去调用Student类中的方法则需要强制向下转型，否则编译器会报错，表面类型是Person，能执行的方法主要看对象左边的引用类型，父类不能调子类的方法，除非是父类某方法被实际类型重写。

4. 多态是方法的多态，必须是有联系的两个类，父类和子类

5. static方法不能重写，final也不能，private也不能，因此更不能实现多态。多态使得程序更灵活

6. **instanceof (类型转换) 引用类型**

   可以判断是否具有父子关系，Object object = new Student();

   object instanceof Student  object instanceof Person object instanceof Object

   这三个语句都是true先判断是否是，再强制转换调用。((Student) student).go();子类转换为父类直接转换但是可能会丢失本来属于自己的一些方法。

   如果x,y之前完全没关系会直接编译报错

7. 可以方便方法的调用，使代码更加灵活
8. 被final修饰的类不能被继承

### 代码块

```java
我希望调用Math.random方法
//我可以import static java.lang.Math.random;//静态导入包 这样就可以省略Math，直接调用random（）
{
    //程序中直接出现这样的代码块（匿名代码块）每次对象都会使用一般赋初值
}
static{
    静态代码块//第二个对象创建就不会出现了
}
//首先执行静态代码块，只执行一次，跟类一起加载，然后是匿名代码块，然后是构造函数
```

### 抽象类

1. abstract修饰符如果修饰类，则它是抽象类，修饰方法就是抽象方法
2. 抽象类中可以没有抽象方法，但是有抽象方法的类一定要声明为抽象类
3. 抽象类不能new对象，它的作用是被子类继承
4. 抽象方法只有声明没有实现，是用来让子类实现的
5. 子类继承抽象类，就必须要实现抽象类没有实现的抽象方法，否则子类也要声明为抽象类

```java
public abstract void doSomething(int a, int b);
```

6. 但是抽象类逃脱不了终究是类，需要extends去继承，由于JAVA只支持单继承，所以有弊端

7. 抽象类存在构造器，用于子类super()

### 接口

1. 接口就是规范，定义一组规则，体现了现实世界中，如果你是什么东西应该有能力完成哪些事情
2. 本质是契约，制定好后每个对象必须遵守，约束和实现分离
3. 声明接口的关键字是interface

```java
public interface UserService{
    void run();
    //默认public abstract
} 
```

4. implements关键字，实现了接口的类需要重写接口中的所有方法
5. 接口里面定义的属性默认public static final
6. 借口不具有构造方法，无法实例化

### 包装类

1. java为八种基本类型对应准备了八种包装类型，属于引用类型，父类是Object
2. 好处：针对Object对象的方法无法处理基本数据类型，因此我们提供自动装箱自动拆箱
   1. int - Integer char - Character byte - Byte long - Long 
   2. short - Short    float - Float  double - Double boolean - Boolean 

3. 数字相关的六个父类是Number，抽象类无法实例化，其他两个是Object

4. 提供了一些帮助方法，负责拆箱等

   ```java
   Integer常用方法
   static int parseInt(Sting s);//传String返回int Double.parseDouble(类似)
   static String toBinaryString();//toHexString
   static Integer valueOf(int i);
   ```

5. int 到 String: 直接加双引号，2+""; String.valueOf(int);
6. String 到 int：Integer.parseInt("123")
7. int Integer自动装箱拆箱，Integer.valueOf(100);  Integer.intValue();
8. String到Integer：Integer.valueOf("123"),Integer 到String， String.valueOf(Integer);

## 内部类

1. 内部类就是在一个类内部再定义一个类，如在A中定义B类，对A来说，B是内部类，对B来说，A是外部类
   1. 成员内部类：类似于实例变量
   2. 静态内部类：类似于静态变量
   3. 局部内部类：类似于局部变量
   4. 匿名内部类

### 基本内部类(用的不多了)

~~~java
class Test01{
    //静态内部类
    static class Inner1{
        
    }
    //实例内部类
    class Inner2{
        
    }
    public void doSome(){
        //局部内部类
        class Inner3{
            
        }
    }
    public void doOther(){
        new Test01().new Inner2();
    }
}
~~~

### 匿名内部类

1. 是局部内部类的一种，因为这个类没有名字得名

   ```java
   interface Compute{
       int sum(int a, int b);
   }
   class MyMath{
       public void sum(Compute c,int x, int y`cx){
           c.sum(x,y);
       }
       public static void main(String[] args){
           MyMath mm = new MyMath();
           mm.mySum(?,10,100);//第一个参数怎么传，接口是不能直接new的，你得编写一个接口的实现类，实例化，要不就得实现匿名内部类
           mm.mySum(new Compute(){public int sum(int a,int b){
               return a+b;
           }},100,200);//没有办法重复使用，可读性差，看起来高端，阅读代码能够理解
           
       }
   }
   ```

   

## 常用类

### Object类

1. protected Object clone(); // 负责对象克隆

   深克隆浅克隆，调用的也是C++

2. int hashCode() //获取对象哈希值

   ```java
   public native int hashCode();
   //native关键字，底层调用C++，跟存储地址有关
   ```

   boolean equals(Object obj) //判断两个对象是否相等

   ```java
   public boolean equals(Object ob){
       return (this == obj);
   }//默认实现，通过equals方法判断两个对象是否相等，我们需要判断磨人的equals是否够用，基本类型直接双等号，引用类型默认比较的是内存地址，String类已经重写了toString和equals
   ```

3. String toString() //将对象转换成字符串形式

   ```java
   public String toString(){
       return this.getClass().getName()+"@"+Integer.toHexString(hashCode());//内存地址16进制，建议所有子类都重写这个方法
   }
   ```

4. protected void finalize() //垃圾回收器负责调用的方法

   ```java
   protected void finalize() throws Throwable {}
   //JVM自动调用，如果希望在垃圾销毁时执行一段代码可以写到里面
   ```

### String类

1. 双引号括起来的表示字符串，引用数据类型，长度不能改变

2. 在JDK当中双引号括起来的字符串都是直接存储在方法区的字符串常量池中的，所以直接双引号括起来了如果重复使用地址相同，不用重新生成。但new是在堆中开辟空间，如果方法区中已经有这个字符串，堆中空间就直接从方法区中取相应地址，栈中指向堆中地址，垃圾回收器不回收常量池
3. 建议书写格式用确定不为null的元素调用equals方法
4. 输出引用自动调用toString方法
5. 实现CharSequence接口

   #### 构造方法

1. String s1 = "aaa";

2. String(byte[] bytes)      String(byte[], int offset, int length)//字节数组，数组下标元素的起始位置，长度

   char数组，char[]的部分也是可以的

#### 常用方法

~~~java
1. char charAt(int index) 返回索引字符
2. int compareTo(String anotherString)//相等是0，大于是1，小于是-1
3. boolean contains(CharSequence s)//判断是否包含s
4. boolean endsWith(String suffix)//是否以某个字符串结尾
5. equals()//并没有调用CompareTo
6. boolean equalsIgnoreCase(String anotherString)//判断两个字符串是否相等并且忽略大小写
7. byte[] getByte();//返回byte数组
8. int indexOF(String str)//判断某个子字符串在当前字符串中第一次出现的索引
9. boolean isEmpty()  int length()
10. int lastIndexOf(String str)//最后一次出现的索引
11. boolean matches(String regex)//是否匹配正则表达式
12. String replace(newChar,oldChar)//替换所有old-new
13. String[] split(String regex)//比如时间按照-拆开
14. boolean startsWith(String)
15. String substring(int begin)(int begin, int end
16. char[] toCharArray();
17. String toLowerCase(String),toUpperCase();
18. String trim();//去除字符串前后空白                                
19. static String valueOf(各种类型)不是字符串转换为字符串，对象的话会调用对象.toString()                             
~~~

### StringBuffer

1. 构造一个不带字符的字符串缓冲区，初始容量16，实际底层是一个byte数组（jdk1.9以后）,会自动扩容
2. 拼接使用append()
3. String 的byte数组带final
4. 在创建StringBuffer的时候尽可能给定一个初始化容量，减少扩容次数。

### StringBuilder

1. StringBuffer是线程安全的synchronized关键字修饰，而StringBuilder是没有的，所有更快，但多线程环境下运行时不安全的。
2. 也要尽可能减少扩容次数

### java.util.Date

```java
//构造函数
//无参构造函数会获取精确到毫秒的系统当前时间
Date nowTime = new Date();
//日期格式化主要使用java.text.SimpleDateFormat这个类
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"//这里指定日期格式);要用相应的模式
//yyyy 年 MM月 dd日 HH时 mm分 ss秒 SSS毫秒最高999,除了这些单词，剩下的符号格式随意组织
String str = sdf.format(nowTime);   //就可以更改时间格式了
//string怎么转成Date类型
String time = "2008-08-08 08:08:08 888";
SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
Date dateTime sdf2.parse(time); //这里会出现异常，方法要上抛错误                               //
//System有一个函数，获取自1970年到当前系统时间的总毫秒数
long nowTimemm = System.currentTimeMillis();
//可以用来统计方法执行所耗费的时间                                            
```

### 枚举类型

1. Java 枚举是一个特殊的类，一般表示一组常量，比如一年的 4 个季节，一个年的 12 个月份，一个星期的 7 天，方向有东南西北等。

2. Java 枚举类使用 enum 关键字来定义，各个常量使用逗号 **,** 来分割。

   ```java
   public class Test
   {
       enum Color
       {
           RED, GREEN, BLUE;
       } 
       // 执行输出结果
       public static void main(String[] args)
       {
           Color c1 = Color.RED;
           System.out.println(c1);
       }
   }
   //每个枚举都是通过 Class 在内部实现的，且所有的枚举值都是 public static final 的。
   //以上的枚举类 Color 转化在内部类实现：
   class Color
   {
        public static final Color RED = new Color();
        public static final Color BLUE = new Color();
        public static final Color GREEN = new Color();
   }
   ```

   [枚举知识](https://blog.csdn.net/u013939884/article/details/53445621#:~:text=%E6%9E%9A%E4%B8%BE%E4%BC%98%E7%82%B91%20%E5%A2%9E%E5%BC%BA,%E5%AD%98%E6%94%BE%E7%9A%84%E6%98%AF%E6%97%A7%E5%80%BC%E3%80%82)

