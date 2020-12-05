# 9-回文数

> 简单  
> 数学、字符串

### 题目描述

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

#### 示例1:

```
输入: 121
输出: true
```

#### 示例2：

```
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```

#### 示例3：

```
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

**进阶:**

你能不将整数转为字符串来解决这个问题吗？

</br>

### 题目分析

一开始我就用字符串来解决，发现时间有点慢。

解法一：

首先可以排除负数，把数字转换成字符串，然后对半比对，头跟尾比对，遇到不一样的就可以返回false了，循环结束条件`i>j`，当`j=i`或者`j<i`时，比对数已经超过一半了，可以结束。

### 代码实现一

```java
public boolean isPalindrome(int x) {
        if(x<0) return false;
        String num = String.valueOf(x);
        char[] array = num.toCharArray();
        int i=0;
        int j=array.length-1;
        while(i<j){
            if(array[i]!=array[j]) return false;
            i++;
            j--;
        }
        return true;
    }
```



解法二：

首先排除掉负数、小于10的数、还有末尾是0的数。

步骤1：声明一个反转数rev

步骤2：x取余10，得到末尾数

步骤3：把当前rev×10再加上步骤2求得的末尾数，赋值到rev

步骤4：x=x/10

步骤5：判断x是不是大于rev，若是，则说明取数已经对半或者过半。若不是，重复步骤2

步骤6：比较结果：如果x==rev（偶数位）或者x==rev/10（奇数，/10把中间数去掉），则返回true

### 代码实现二

```java
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0||(x!=0&&x%10==0)){
            return false;
        }
        int rev = 0;
        while(x>rev){
            rev = rev*10+x%10;
            x/=10;
        }
        return x==rev||x==rev/10;
    }
}
```
