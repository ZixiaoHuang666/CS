# LC 36 Valid Sudoku

![image-20210421172832588](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421172832588.png)

## Pre-Thinking

给我们一个带有一些数字的数独9*9宫格，问我们目前是否符合规则

Edge case:填入的是否都是1-9的数字，大小是否一定是9*9

对于每一个填入数字的方格，我要检查对应的行，列，小正方形内有没有同样的数字

暴露的算法就是遍历三次，遍历行，遍历列，遍历小正方形，

如何改进我们可以用三个hash表遍历一次，每次标记一下是哪个位置，

