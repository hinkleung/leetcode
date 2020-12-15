# 121-买卖股票的最佳时机

> 简单  
> 数组、动态规划

### 题目描述

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。

#### 示例1:

```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
```

#### 示例2:

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

</br>

### 题目分析

使用动态规划：

1、从第二天开始，记录今天之前的最小价格minPrice

2、计算当天的最大获利（prices[i]-minPrice）

3、遍历数组，比较得出当前最大获利

### 代码实现

```java
class Solution {
    public int maxProfit(int[] prices) {
        //今天之前的最小价格
        int minPrice = Integer.MAX_VALUE;
        //最大的获利
        int maxGain = 0;
        for(int i=1;i<prices.length;i++){
            minPrice = Math.min(minPrice,prices[i-1]);
            maxGain = Math.max(maxGain,prices[i]-minPrice);
        }
        return maxGain;
    }
}
```
