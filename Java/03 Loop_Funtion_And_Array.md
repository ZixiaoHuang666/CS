# JAVA 流程控制函数与数组

## 用户交互Scanner

1. java.util.Scanner 是 java5的新特性，我们可以通过这个类来获取用户的输入。
2. 基本语法 Scanner s = new Scanner(System.in);
3. 通过next()与nextLine()方法获取输入的字符串，hasNext(),hasNextLine()判断是否还有数据
4. next() 省略之前的空格，读取到有效字符后才讲后面输入的空白作为分隔符或结束符
5. nextLine()方法返回的是输入回车之前的所有字符，以Enter作为结束符
6. 要记得再使用后关闭，不然这些io类会一直占用资源，scanner.close();
7. hasNextInt(),nextInt(),hasNextFloat(),nextFloat();

## 逻辑结构

### 顺序结构

1. 基本结构就是顺序结构，自动执行
2. 任何一个算法都无法离开的结构

### 选择结构

#### if单选择结构

```java
if(){
    
}
```

#### if双选择结构

```java
if() {
    
}else{
    
}
```

#### if多选择结构

```java
if(){
    
}else if(){
    
}else if(){
    
}else{
    
}
```

#### switch多选择结构

```java
switch(expression){
    case value://变量类型可以是byte,short,int,char 从javaSE7开始支持String
        //case标签必须是字符串常量或者字面量
        break;
    case value2:
        //
        break;//不写break输出所有后面的，击穿
    default:
        //
        
}//String类的实现本质还是数字的识别，使用了hashcode();
```

### 循环结构

#### while循环

```java
while(//布尔表达式){ 循环停止条件
    
}
```

#### do while循环

```java
do{
    
}while();//即使不满足条件至少执行一次，先执行后判断
```

#### for循环

```java
for(//初始化;布尔表达式;更新){
    
}
for(;;)//死循环
```

#### 增强for循环

1. java5引入对于数组与集合的增强型for循环

```java
for(int i:arrayname){
    
}
```

#### break/continue

终止跳出循环/跳过本次循环

## Java 函数详解

1. 函数是语句的集合，在一起执行一个功能
2. 最好一个方法实现一个功能，原子性，方便扩展

```java
修饰符 返回值类型 方法名(参数类型 参数名){
    方法体
    return;
}
static 代表类独有的，可以类名直接调用    
```

3. **值传递和引用传递**

   JAVA都是值传递

4. 方法的重载
   - 在一个类中，有相同函数名称，但形参不同
   - 参数列表必须不同
   - 返回类型可同可不同，只有返回类型不同不可以
   - 编译器会逐一匹配

5. **命令行传参**

   main函数后面的String[]意味着我们可以在命令行传入数据

   javac 包名.类名

   要退到src级目录下去找

   java com.huang.Demo 这里输入想要传入的数据

6. 可变参数

   JDK1.5开始，JAVA支持传递同类型的可变参数，在方法声明中加一个省略号，一个方法中最多一个，且放在最后。

   ```java
   public static void print(double... numbers){
       if(numbers.length == 0){
           return;
       }
       double result = numbers[0];
       //本质使用数组
   }
   ```

## 数组

   1. 数组是相同数据类型的有序集合
   2. 可以通过下标访问

```java
//声明数组变量
dataType[] array = new dataType[arraySize];
//索引从0开始
//直接获取数组长度
array.length
```

3. 数组的三种初始化

   1. 静态初始化

      ```java
      int[] a = {1,2,3};
      Man[] man = {new Man(),new Man()};
      ```
      
   2. 动态初始化

      ```java
      int[] a = new int[2];
      a[0] = 1;
      a[1] = 2;
      ```
      
   3. 数组的默认初始化
   
      - 数组是引用类型，它的元素相当于类的实例变量，因此数组一经分配空间，其中的每个元素也被按照实例变量同样的方法隐私初始化。
### java内存分析

![java内存](https://user-gold-cdn.xitu.io/2019/12/27/16f4750982daa7b6?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

1. 堆
   1. 存放new的对象和数组
   2. 可以被所有的线程共享，不会存放别的对象的引用

2. 栈
   1. 存放基本变量类型，包含具体数值
   2. 引用对象的变量，存放引用对象在堆里面具体地址

3. 方法区
   1. 可以被所有线程共享
   2. 包含了所有的class和static变量

### 数组的基本特点

1. 长度确定，创建大小不能改变
2. 元素必须是相同类型，包括基本类型和引用类型
3. 数组变量属于引用类型，本身也可以看作对象，里面的元素相当于成员变量，数组对象在堆中
4. 0到nums.length-1 不可越界

### Arrays类

1. java.util.Arrays
2. Arrays.toString() 打印出来元素前后加中括号 Arrays.fill(a,0)对数组a赋值0
3. 都是static修饰，可以直接类调用

### 稀疏数组

1. 当一个数组中大部分元素为0，或者同一值，可以使用稀疏数组来保存该数组
2. 处理方式是
   1. 记录数组一共有几行几列，有多少个不同值
   2. 把具有不同值的元素和行列及值记录在一个小规模的数组中，从而缩小程序的规模，从而达到压缩的目的

3. 转换：获取有效值的个数，

   ```java
   int[][] array2 = new int[sum + 1][3];
   //第一行几行几列有效值
   array2[0][0] = //依次行列有效值个数
   //接下来把原数组中的非目标值记录，行列，数值
   //还原也很方便
   ```

   