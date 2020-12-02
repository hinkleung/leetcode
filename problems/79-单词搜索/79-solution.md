# 79-单词搜索
>中等  
>数组、回溯算法、深度优先搜索

#### 题目描述

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

**示例**

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false

提示：
board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
```



#### 题目分析

这道题在写代码前，自己先想一下是怎么寻找这个单词的，首先找到word的第一个字母在数组中的哪个位置，比如`word="ABCCED"`，寻找A在哪里。A在`board[0][0]`和`board[2][0]`都有，先来看看`board[0][0]`，寻找它相邻的单元格有没有`B` ，相邻的意思，就是上下左右四个方向的单元格，这里要主意**数组越界**问题，还有一个要注意的，题目说**同一个单元格内的字母不允许被重复使用**，这很容易想到需要维护一个已读标记，可以用`visited[]`数组，也可以像我下面代码那样，变更字母中内容，下次如果遍历到该单元格，不会被匹配。搜索到`B`之后，就遍历`B`的相邻的结点，搜索下一个字母`C`，以此类推，我们可以用深度优先搜索（DFS）和回溯来递归实现，每一次都看当前字母匹配否，匹配就搜索上下左右相邻的单元格，不匹配就回溯到上一层，撤销已读标记。当匹配完单词，就可以返回结果了。



#### 代码实现

```java
class Solution {
        public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if (exist(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * i和j是当前节点的位置标示，index是当前遍历到单词word的位置
     */
    private boolean exist(char[][] board, String word, int i, int j, int index){
        //已找全整个单词
        if(index == word.length()) return true;
        //数组越界
        if(i<0 || i==board.length || j<0 || j==board[i].length){
            return false;
        }
        //字母不匹配
        if(board[i][j]!=word.charAt(index)){
            return false;
        }
        //标记访问：把原字母变更成其他字符
        board[i][j] += 100;
        if(exist(board,word,i-1,j,index+1)) return true;
        if(exist(board,word,i+1,j,index+1)) return true;
        if(exist(board,word,i,j-1,index+1)) return true;
        if(exist(board,word,i,j+1,index+1)) return true;
        //回溯，撤销访问标记，变回原字符
        board[i][j] -= 100;
    
        return false;
    }
}
```

#### 提交结果

![79提交结果图](https://github.com/hinkleung/leetcode/blob/main/problems/79-单词搜索/79-pic.png)