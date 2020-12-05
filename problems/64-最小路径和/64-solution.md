# 64-最小路径和

> 中等  
> 数组

### 题目描述

给定一个包含非负整数的 `m x n` 网格 `grid` ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**每次只能向下或者向右移动一步。

#### 示例1:

![pic1](https://github.com/hinkleung/leetcode/blob/main/problems/64-最小路径和/64-pic1.jpg)

```
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
```

#### 示例2：

```
输入：grid = [[1,2,3],[4,5,6]]
输出：12
```

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 200`
- `0 <= grid[i][j] <= 100`

</br>

### 题目分析

要求到最右下角的最小路径和，那么就要知道到其**上方元素**的最小路径和还有**左方元素**的最小路径和，取这两个的最小值再加上最右下角元素的值就行了。下一步要求的就是**上方元素**的**上方元素**和**左方元素**……这不就是动态规划的经典使用，把大问题分解出小问题，小问题解决完，大问题就得出结果，此处小问题就是到每个方格的最小路径和，于是可以创建一个`dp[m][n]`数组，动态求出数组中的所有元素，初始条件是`dp[0][0]=grid[0][0]`

可以抽象出推导公式`dp[i][j]=min(dp[i-1][j],dp[i][j-1])+grid[i][j]`，但需要注意数组越界的情况。

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0]=grid[0][0];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    continue;
                }
                //获取正上方元素的dp值
                int up = i-1 >= 0 ? dp[i-1][j] : Integer.MAX_VALUE;
                //获取正左方元素的dp值
                int left = j-1 >= 0 ? dp[i][j-1] : Integer.MAX_VALUE;
                dp[i][j]=Math.min(up,left)+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
```

这个代码由于太多判断，提交时间只超过了20%，再优化一下，边界值可以直接得出，不需要多余的判断了。

附上解题步骤图，红色数组为`dp[i][j]`的值

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/64-最小路径和/64-pic.jpg)

### 代码实现

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0]=grid[0][0];

        //左边界填充
        for(int i=1;i<m;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }

        //上边界填充
        for(int j=1;j<n;j++){
            dp[0][j]=dp[0][j-1]+grid[0][j];
        }
	//从i=1,j=1开始
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/64-最小路径和/64-result.png)
