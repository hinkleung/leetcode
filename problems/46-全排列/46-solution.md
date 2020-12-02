# 46-全排列
>中等  
>回溯算法

#### 题目描述

给定一个 没有重复 数字的序列，返回其所有可能的全排列。

**示例**

```
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

#### 题目分析

假设输入n个数字，就要填充一个长度为n的数组或者List，当填充到某个位置上时，每次取数是取未用到的数，这里很容易想到用一个visited[]数组来标记取过的数，该填充位置上应当把未取过的数字都枚举一遍，回溯到本位置的时候要撤销掉visited和当前位置上的数字。

比如说[1,*]，现在取到第2个位置，现在visited中2和3是未取的，先取2，即当前排列是[1,2]，后面剩下3未取，即最终排列是[1,2,3]。

回溯到第2个位置，撤销刚刚的2，排列重回到[1]，轮到取3了，即当前排列是[1,3]，后面剩下2未取，即最终排列是[1,3,2]加入结果集。

我们可以想到用递归来解决这个问题，因为这很容易用一棵树来表示，不过给出树示意图之前，还有一个问题未解决，既然用到递归就得有结束条件，我们可以用一个值来代表当前填充到的位置，当填充到n的时候，就可以结束了加入到结果集。（代码中用first来表示当前填充的位置，由于first从0开始，每次递归都first+1，当first=n时，就表示current已填到n个数了）。

下面给出树的示意图。



![42-树示意图](https://github.com/hinkleung/leetcode/blob/main/problems/46-全排列/46-analysis.jpg)

#### 代码实现

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //current 当前排列结果
        List<Integer> current = new ArrayList<>(length);
        //visited中，1为已取，0为未取
        int[] visited = new int[length];
        backtrack(nums,visited,res,current,0);
        return res;
    }

    private void backtrack(int[] nums, int[] visited, List<List<Integer>> res,List<Integer> current, int first){
        if(first==nums.length){
            res.add(new ArrayList<Integer>(current));
        }
        for(int i=0;i<nums.length;i++){
            if(visited[i]!=1){
                current.add(nums[i]);
                visited[i]=1;
                backtrack(nums,visited,res,current,first+1);
                visited[i]=0;
                current.remove(first);
            }
        }
    }
}
```

#### 提交结果

![42提交结果图](https://github.com/hinkleung/leetcode/blob/main/problems/46-全排列/46-result.png)
