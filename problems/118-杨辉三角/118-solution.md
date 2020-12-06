# 118-杨辉三角

> 简单  
> 集合、数组

### 题目描述

给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/118-杨辉三角/118-pic1.gif)

在杨辉三角中，每个数是它左上方和右上方的数的和。

#### 示例1:

```
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

</br>

### 题目分析

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/118-杨辉三角/118-pic2.jpg)

如上图，当输入numsRows=6时，则需要返回6行list，横坐标是索引位置，从0开始，纵坐标是list的索引，也是从0开始。

从图标中可以看出规律：

1、每个list的第一个元素和最后一个元素都是1

2、其他元素是**上一个**list的**同索引**位置+**前一个索引**位置的和。例如`(3,1)=(2,0)+(2,1)=1+2=3`

### 代码实现

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            List<Integer> row = new ArrayList<>();
            for(int j=0;j<=i;j++){
                if(j==0||j==i){
                    row.add(1);
                }else{
                    //上一个 List<Integer> lastRow = res.get(i - 1);
                    row.add(res.get(i - 1).get(j-1)+res.get(i - 1).get(j));
                }
            }
            res.add(row);
        }
        return res;
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/118-杨辉三角/118-result.png)
