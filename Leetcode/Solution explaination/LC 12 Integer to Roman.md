# LC 12 Integer to Roman

![image-20210421113733542](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421113733542.png)

---

这道题是经典的折磨题，如果细分种类试图找简单的方法会发现情况无尽多，极难判断，应该从有限的组合情况下手

## Try

~~~java
class Solution{
     public String intToRoman(int num) {
         String[] str = new String[]			{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
         int[] point = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
         StringBuilder s = new StringBuilder();
         while(num != 0){
             for(int i = 0; i < point.length;i++){
                 if(num >= point[i]){
                     s.append(str[i]);
                     num -= point[i];
                     break;
                 }
             }
         }
         return s.toString();
         
     }
}
~~~

一遍过，no debugging 