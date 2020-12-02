# 1030-距离顺序排列矩阵单元格

>简单  
>排序

### 题目描述

给出` R` 行 `C `列的矩阵，其中的单元格的整数坐标为` (r, c)`，满足 `0 <= r < R` 且` 0 <= c < C`。

另外，我们在该矩阵中给出了一个坐标为` (r0, c0) `的单元格。

返回矩阵中的所有单元格的坐标，并按到` (r0, c0) `的距离从最小到最大的顺序排，其中，两单元格`(r1, c1) `和` (r2, c2)` 之间的距离是曼哈顿距离，`|r1 - r2| + |c1 - c2|`。（你可以按任何满足此条件的顺序返回答案。）


#### 示例1

```
输入：R = 1, C = 2, r0 = 0, c0 = 0
输出：[[0,0],[0,1]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
```

#### 示例2

```
输入：R = 2, C = 2, r0 = 0, c0 = 1
输出：[[0,1],[0,0],[1,1],[1,0]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
```

#### 示例3

```
输入：R = 2, C = 3, r0 = 1, c0 = 2
输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
```

#### 提示

1. `1 <= R <= 100`
2. `1 <= C <= 100`
3. `0 <= r0 < R`
4. `0 <= c0 < C`

</br>

### 题目分析

注意返回的数组是一个`[R×C][2]`的数组，而不是`[R][C]`的数组，但却是用`[R×C][2]`的数组描述`[R][C]`的矩阵。

首先用**暴力解法**，速度不佳

### 代码实现一

```java
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                res[i*C+j][0] = i;
                res[i*C+j][1] = j;
            }
        }

        Arrays.sort(res,((o1, o2) -> {
            int dis1 = Math.abs(o1[0]-r0) + Math.abs(o1[1]-c0);
            int dis2 = Math.abs(o2[0]-r0) + Math.abs(o2[1]-c0);
            return dis1-dis2;
        }));
        return res;
    }
}
```

### 提交结果一

![1030提交结果图1](https://github.com/hinkleung/leetcode/blob/main/1030-距离顺序排列矩阵单元格/1030-result1.png)

</br>

**第二种解法：桶排序**

用一个list维护着多个桶，每个桶表示一个距离且里面装着的是距离`r0,c0`等于这个距离的点，等装完所有点后，再按顺序排列出结果。

速度有提升，但消耗了多余的空间。


### 代码实现二

```java
class Solution {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {

        int[][] res = new int[R*C][2];
        //最大距离
        int maxDist = Math.max(r0, R-1-r0) + Math.max(c0, C-1-c0);
        List<LinkedList<Pos>> bucket = new ArrayList<>(maxDist+1);
        for(int i=0;i<=maxDist;i++){
            bucket.add(new LinkedList<>());
        }

        //把点装进对应的桶
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                int dist = getDis(i,j,r0,c0);
                bucket.get(dist).add(new Pos(i,j));
            }
        }

        //把点取出来到结果集
        int cur = 0;
        for(int i=0;i<=maxDist;i++){
            if(!bucket.get(i).isEmpty()){
                LinkedList<Pos> linkedList = bucket.get(i);
                for(Pos p : linkedList){
                    res[cur][0] = p.i;
                    res[cur][1] = p.j;
                    cur++;
                }
            }
        }


        return res;
    }

    private int getDis(int i, int j, int r0, int c0){
        return Math.abs(i-r0) + Math.abs(j-c0);
    }

    private class Pos{
        int i;
        int j;
        public Pos(int i, int j){
            this.i=i;
            this.j=j;
        }
    }
}
```

### 提交结果二

![1030提交结果图2](https://github.com/hinkleung/leetcode/blob/main/1030-距离顺序排列矩阵单元格/1030-result2.png)

</br>

**第三种解法：BSF**

把`r0,c0`当做图的圆点，然后广度优先搜索，每次距离增加1之后，找出符合当前距离的点，直到填充完结果集为止。

### 代码实现三

```java
class Solution {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        int dist = 0;
        int cur = 0;
        int[] factor = new int[]{-1,1};
        while(cur<R*C){
            for(int rowDist=0;rowDist<=dist;rowDist++){
                int colDist = dist - rowDist;
                //4个方向
                for(int i=0;i<2;i++){
                    int row = r0 + factor[i]*rowDist;
                    for(int j=0;j<2;j++){
                        int col = c0 + factor[j]*colDist;
                        if(row>=0 && row < R && col >=0 && col < C){
                            res[cur][0]=row;
                            res[cur][1]=col;
                            cur++;
                        }
                        if(colDist==0) break;//当colDist==0时，始终int col = c0 + factor[j]*colDist = c0，遍历一次即可
                    }
                    if (rowDist == 0) break;//rowDist==0时，始终int row = r0 + factor[i]*rowDist = r0，遍历一次即可
                }
            }
            dist++;
        }
        return res;
    }   
}
```

### 提交结果三

![1030提交结果图3](https://github.com/hinkleung/leetcode/blob/main/1030-距离顺序排列矩阵单元格/1030-result3.png)