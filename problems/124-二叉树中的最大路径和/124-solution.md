# 124-二叉树中的最大路径和

> 困难  
> 树、深度优先搜索

### 题目描述

给定一个**非空**二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径**至少包含一个**节点，且不一定经过根节点。

#### 示例1:

```
输入：[1,2,3]

       1
      / \
     2   3

输出：6
```

#### 示例2:

```
输入：[-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出：42
```

</br>

### 题目分析

关于树的题，无非要用（前中后序）遍历去解决，还是用到递归遍历（深度优先）树的框架代码。这道题也不例外。

首先要理解题目的意思，把树的节点看成图的话，路径不可以有分叉，即是一条线（**这个是重点**）。路径和就是路径所有的节点的值相加的和，找出这颗树中最大的路径和。

既然用到递归，我们就把大问题分解成小问题来看，给定一个节点，求出其在路径上贡献了多少值，有以下几种情况：

1、该节点是空，则没贡献，贡献值为0

2、该节点是叶子结点且值为整数，则贡献值为该节点的值

3、该节点是叶子节点但值为负数，贡献值为负，设贡献值为0

4、该节点是非叶子节点，则对路径上的贡献值等于节点值+左右子树中取最大得贡献值（当和为负数时，设贡献值为0），因为路径不能分叉，只能取一边的子树来加入到路径中，并返回给**上一层**节点

还有一个要求的值，就是当前节点的最大路径和取决于节点值+左子树贡献值+右子树贡献值。

设函数`maxGain()`，返回的是当前节点的最大贡献值，并且递归更新二叉树中的最大路径和。

例子：

```
   10
   / \
  9  20
    /  \
   -15  7

1、maxGain(9)=9
2、maxGain(-15)=0
3、maxGain(7)=7
4、maxGain(20)=Math.max(maxGain(-15),maxGain(7))+20=27
	此时路径和是20+maxGain(7)+maxGain(-15)=27，因为要考察路径-15->20->7的和是否是最大路径，此时最大路径和是27
5、maxGain(10)=Math.max(maxGain(9),maxGain(20))+10=37
	此时路径和是10+maxGain(9)+maxGain(20)=46，46>27，所以更新最大路径和是46
```



### 代码实现

```java
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return max;
    }

    /**
    * 取得当前节点的最大贡献值，并且更新最大路径和
    */
    public int maxGain(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftGain = Math.max(maxGain(root.left),0);//左子树贡献
        int rightGain = Math.max(maxGain(root.right),0);//右子树贡献
        int pathGain = root.val + leftGain + rightGain;//当前节点路径和
        max = Math.max(pathGain,max);//更新最大路径和
        
        return root.val + Math.max(leftGain,rightGain);//返回贡献值
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/124-二叉树中的最大路径和/124-result.png)
