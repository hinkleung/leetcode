# 7-整数反转

> 简单  
> 数学、数据类型

### 题目描述

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

**说明：** 每次只能向下或者向右移动一步。

#### 示例1:

```
输入: 123
输出: 321
```

#### 示例2：

```
输入: -123
输出: -321
```

#### 示例3：

```
输入: 120
输出: 21
```

**注意：**

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

</br>

### 题目分析

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/7-整数反转/7-pic1.jpg)

数字反转，只需要把数字末尾的数移到前面就行了，反转过程不是难点，需要注意的是反转后的**溢出**问题。

因为题目说的只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]，这不刚好是Java整型int的范围吗？

```
2^31-1=2147483647  =  Integer.MAX_VALUE
-2^31=-2147483648  =  Integer.MIN_VALUE
```

代码中需要判断，当`res>Integer.MAX_VALUE/10`时，res再×10就会溢出，或者`res==Integer.MAX_VALUE/10`时，res再×10并加上一个大于7的数，就会溢出。负数同理。

### 代码实现

```java
class Solution {
    public int reverse(int x) {
        int res=0;
        int temp;
        while(x!=0){
            temp = x%10;
            if(res>Integer.MAX_VALUE/10 || res==Integer.MAX_VALUE/10 && temp>7) return 0;
            if(res<Integer.MIN_VALUE/10 || res==Integer.MIN_VALUE/10 && temp<-8) return 0;
            res = res * 10 + temp;
            x=x/10;
        }
        return res;
    }
}
```
