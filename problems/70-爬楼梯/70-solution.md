# 70-爬楼梯

>简单  
>递归、动态规划

### 题目描述

假设你正在爬楼梯。需要 *n* 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

**注意**：给定 *n* 是一个正整数。

#### 示例1

```
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```

#### 示例2

```
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```



### 题目分析

这个题目就是斐波那契数列的变形。

根据题目意思，当需要爬n阶楼梯时，定义`f(n)`为爬n阶楼梯的方法数，当前的情况有两种，①距离n阶还有1阶，②距离n阶还有2阶，那么`f(n)`为`f(n-1)`和`f(n-2)`的和，即`f(n)=f(n-1)+f(n-2)`，看到这里相信大家已经有思路了，`f(n-1)=f(n-2)+f(n-3)`、`f(n-2)=f(n-3)+f(n-4)`……`f(3)=f(2)+f(1)`，初始条件`f(0)=1`，`f(1)=1`，`f(2)=2`。

可以看到，从底向上计算就能累加出`f(n)`，ok，敲下代码吧

</br>

### 代码实现

```java
class Solution {
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }
}
```

oh my god……**超时！**

因为有很多重复的计算，比如`f(n)=f(n-1)+f(n-2)`和`f(n-1)=f(n-2)+f(n-3)`，明显`f(n-2)`重复计算了。

改进代码，用一个Map来记忆算过的数

```java
class Solution {
    Map<Integer,Integer> map = new HashMap<>();
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        if(map.containsKey(n)){
            return map.get(n);
        }
        int res = climbStairs(n-1) + climbStairs(n-2);
        map.put(n,res);
        return res;
    }
}
```

![结果图1](https://github.com/hinkleung/leetcode/blob/main/70-爬楼梯/70-result1.png)

</br>

</br>

还可以用**动态规划**来解题，又搬出了熟悉的`dp`数组。定义`dp[n]`为爬到第n阶的方法数，所以`dp[n]=dp[n-1]+dp[n-2]`，初始值易得`dp[1]=1`，`dp[2]=2`，跟上面递归一样递推。

```java
class Solution {
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
```

![结果图1](https://github.com/hinkleung/leetcode/blob/main/70-爬楼梯/70-result2.png)

</br>

</br>

上面的代码需要用到空间复杂度为O(n)，n是楼梯的阶数，其实可以发现`dp[n]`只是与`dp[n-1]`和`dp[n-2]`有关系，与更靠前的没关系，所以我们可以用滚动数来使空间复杂度减少为O(1)，当前不断滚动更新`f1`和`f2`的值，相当于更新`dp[n]`前面的解的值

当n=1时，f1=0，f2=1，res=1

当n=2时，f1=1，f2=1，res=f1+f2=2  （f1滚动更新为f2的值，f2滚动更新为res的值）

当n=3时，f1=1，f2=2，res=f1+f2=3

当n=4时，f1=2，f2=3，res=f1+f2=5

当n=5时，f1=3，f2=5，res=f1+f2=8

……

![结果图1](https://github.com/hinkleung/leetcode/blob/main/70-爬楼梯/70-pic1.png)



最终优化代码如下：

  ```java
class Solution {
    public int climbStairs(int n) {
        int f1 = 0;
        int f2 = 0;
        int res = 1;
        for(int i=1;i<=n;i++){
            f1=f2;
            f2=res;
            res=f1+f2;
        }
        return res;
    }
}
  ```

