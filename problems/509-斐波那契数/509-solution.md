# 509-斐波那契数

> 简单  
> 数组、动态规划

### 题目描述

**斐波那契数**，通常用 `F(n)` 表示，形成的序列称为 **斐波那契数列** 。该数列由 `0` 和 `1` 开始，后面的每一项数字都是前面两项数字的和。也就是：

```
F(0) = 0，F(1) = 1
F(n) = F(n - 1) + F(n - 2)，其中 n > 1
```

给你 `n` ，请计算 `F(n)` 。

#### 示例1:

```
输入：2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1
```

#### 示例2:

```
输入：3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2
```

#### 示例3:

```
输入：4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3
```

**提示：**

- `0 <= n <= 30`

</br>

### 题目分析

这个题目太经典了，学过计算机的都知道。

首先用最慢的递归来实现一下，非常好写，要知道fib(n)得先知道fib(n-1)和fib(n-2)，要知道fib(n-1)得先知道fib(n-2)和fib(n-3)……以此类推，初始化条件是知道f(0)和f(1)，这个为什么会慢，从上一句话就知道，其实fib(n-2)求了两次，其实可以用记忆法优化。

递归：

```java
class Solution {
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
```

记忆法：

```java
class Solution {
    Map<Integer,Integer> map = new HashMap<>();
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        if(map.containsKey(n)){
            return map.get(n);
        }else{
            int res = fib(n-1) + fib(n-2);
            map.put(n,res);
            return res;
        }
    }
}
```

不用递归，可以用动态规划，求出整个斐波那契数列，设dp[n]表示fib(n)

动态规划：

```java
class Solution {
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
```

再优化一下空间，不用创建数n+1长度的数组，只需要使用两个变量，滚动更新即可

```java
class Solution {
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        int[] dp = new int[n+1];
        int small=0;
        int big=1;
        int res=0;
        for(int i=2;i<=n;i++){
            res=small+big;
            small=big;
            big=res;
        }
        return res;
    }
}
```

