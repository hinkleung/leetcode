# 62-不同路径

> 中等  
> 动态规划

### 题目描述

一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

#### 示例1:

![pic1](https://github.com/hinkleung/leetcode/blob/main/problems/62-不同路径/62-pic1.png)

```
输入：m = 3, n = 7
输出：28
```

#### 示例2：

```
输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```

#### 示例3：

```
输入：m = 7, n = 3
输出：28
```

#### 示例4：

```
输入：m = 3, n = 3
输出：6
```

**提示：**

- `1 <= m, n <= 100`
- 题目数据保证答案小于等于 `2 × 10^9`

</br>

### 题目分析

这题跟64题是类似的，我第一反应想到的就是动态规划，根据题目意思，是要从左上角走到右下角。

创建一个数组`dp[n][m]`，表示走到当前位置有多少条路径，初始化`dp[0][0]=1`，上边界和左边界的值都为1，因为走到这两个边界的任意一个点都只有一个方向，一个走法。一般地，走到`dp[i][j]`的路径就是`dp[i-1][j]`和`dp[i][j-1]`的和，即左边格子和上边格子的路径和。

所以有`dp[i][j] = dp[i-1][j]+dp[i][j-1]`。

最终返回`dp`数组的最后一个元素`dp[n-1][m-1]`

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for(int i=0;i<n;i++){
            dp[i][0] = 1;
        }
        for(int j=1;j<m;j++){
            dp[0][j]=1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }
}
```

上面是标准的动态规划的实现，一般动态规划都可以优化成滚动数组，我们可以看到，其实算`dp[i][j]`的值只需要知道上一层的同列的格子的值和左边的格子的值就可以了，所以创建两个数组，一个记录上一层的值，一个记录当前层的值，滚动更新上一层的值。

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] pre = new int[m];
        int[] cur = new int[m];
        Arrays.fill(pre,1);
        Arrays.fill(cur,1);
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                cur[j]=cur[j-1]+pre[j];
            }
            pre=cur.clone();
        }
        return pre[m-1];
    }
}
```

还可以再优化，这个思路比较新奇，只用一个数组`cur[]`，其实当完成了`i-1`行的值之后，遍历下一行更新`i`的值，可以直接加上把`cur[i-1]`与`cur[i]`的值相加即得到最新的`cur[i]`的值，因为未相加之前的`cur[i]`的值就是上一层的值。

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] cur = new int[m];
        Arrays.fill(cur,1);
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                cur[j]+=cur[j-1];
            }
        }
        return cur[m-1];
    }
}
```
