# 1-两数之和

>简单  
>数组、哈希表

### 题目描述

给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 **两个** 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。



#### 示例

```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```



### 题目分析

题目说了答案只有两个整数，我们可以用一个Map来记录数字和它的数组位置。

只需要遍历一次数组，边遍历，边判断：

1、假设当前遍历到的位置是`i`，判断Map中是否存在`target-nums[i]`，如果存在，则取出其索引，得到答案

2、若不存在，则放进Map继续遍历下一个数组数字




### 代码实现一

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[0] = i;
                res[1] = map.get(temp);
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
```

  



### 复杂度分析

时间复杂度：O(n)，n是数组中元素的个数，花销用在遍历上

空间复杂度：O(n)，n是数组中元素的个数，花销在哈希表上  