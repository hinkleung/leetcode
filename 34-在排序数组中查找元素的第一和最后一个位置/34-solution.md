# 34-在排序数组中查找元素的第一和最后一个位置

> 中等  
> 二分法

### 题目描述

给定一个按照升序排列的整数数组`nums`，和一个目标值 `target`。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 `target`，返回 `[-1, -1]`。

**进阶：**

- 你可以设计并实现时间复杂度为 `O(log n)` 的算法解决此问题吗？

#### 示例1:

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

#### 示例2：

```
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```

#### 示例3：

```
输入：nums = [], target = 0
输出：[-1,-1]
```

**提示：**

- `0 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`
- `nums` 是一个非递减数组
- `-109 <= target <= 109`

</br>

### 题目分析

我自己一开始是用了递归分治法，我的思想是：通过对每个小区间计算出其等于target的坐标范围，最终通过合并更新坐标范围（若存在等于target的情况，肯定是范围越来越大）。定义一个递归函数，得出当前`[start,end]`区间内，等于target的范围。最小地，区间长度为1时，就可以直接得到范围，若此位置等于target且当前坐标为`i`时，则返回范围`[i,i]`；若此位置不等target，则返回范围`[-1,-1]`。合并每层时，更新左边界和右边界，下边为代码，我会增加注释来解释代码。



### 代码实现一

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0) return new int[]{-1,-1};
        return searchRange(nums,0,nums.length-1,target);
    }

    private int[] searchRange(int[] nums,int start, int end, int target){
        int[] res = new int[]{-1,-1};
        //由于nums是非单调递减的，所以当左边界大于target或者右边界小于target时，该区间肯定不存在target，直接返回[-1,-1]
        if(nums[start]>target || nums[end]<target){
            return res;
        }
        //递归结束条件：最小区间，判断是否等于target，是则返回当前位置，否则返回[-1,-1]
        if(start==end){
            if(nums[start]==target){
                return new int[]{start,start};
            }else{
                return res;
            }
        }
        int mid = start + (end-start)/2;
        int[] leftRes = searchRange(nums,start,mid,target);//分治左侧
        int[] rightRes = searchRange(nums,mid+1,end,target);//分治右侧

        //对递归分治结果进行合并
        //更新左边界，当左侧左边界和右侧左边界都为-1时，则新范围左边界为-1
        //当左侧左边界或右侧左边界其中一个为-1时，则取不是-1那个
        //当左侧左边界和右侧左边界，则取左侧左边界（因为要取最左范围）
        if(leftRes[0]==-1 || rightRes[0]==-1){
            res[0]=leftRes[0]!=-1?leftRes[0]:rightRes[0];
        } else{
            res[0]=leftRes[0];
        }

        //更新右边界，取最右边范围（若其中某个不存在target时会返回—1，所以取最大是安全的）
        res[1]=Math.max(leftRes[1], rightRes[1]);
        return res;
    }
}
```

### 执行结果一

![pic](https://github.com/hinkleung/leetcode/blob/main/34-在排序数组中查找元素的第一和最后一个位置/34-result.png)

</br>

提交完代码之后，我去看了评论区和题解区，大家都用二分法来完成，原来进阶那里要求`O(log n)`，模仿官答写了下二分法的题解，由于`nums`是排好序的，所以可以通过二分查找法查找想要的元素的位置，分别寻找左边界和右边界。左边界是第一个大于等于target的位置，右边界是最后一个等于target的位置。



### 代码实现二

```java
class Solution {
	public int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0) return new int[]{-1,-1};
        int left = findLeft(nums,target);
        //寻找左边界时返回-1，说明nums中没有等于target的值
        if(left==-1){
            return new int[]{-1,-1};
        }
        int right = findRight(nums,target);
        return new int[]{left,right};
    }

    //寻找左边界，第一个大于等于target的位置
    private int findLeft(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            int mid = left + (right-left)/2;
            if(nums[mid]<target){
                //开始位置一定不在mid和mid的左边，下一次搜索区间[mid+1,right]
                left = mid+1;
            }else if (nums[mid]==target){
                //开始位置可能在mid和mid的左边，下一次搜索区间[left,mid]
                right=mid;
            }else{
                //nums[mid]>target，开始位置一定不在mid和mid的右边，下一次搜索区间[left,mid-1]
                right=mid-1;
            }
        }
        if(nums[left]==target){
            return left;
        }
        return -1;
    }

    //寻找右边界，最后一个等于target的位置
    private int findRight(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            //向上取整，因为不取整的话，当right-left=1时，mid一直等于left，就会陷入死循环
            int mid = left+(right-left+1)/2;
            if(nums[mid]>target){
                //结束位置一定不在mid和mid的右边，所以下一次搜索区间[left,mid-1]
                right=mid-1;
            }else if(nums[mid]==target){
                //结束位置可能在mid和mid的右边，所以下一次搜索区间[mid,right]
                left=mid;
            }else{
                //结束为止一定不在mid的和mid的左边，所以下一次搜索区间[mid+1,right]
                left = mid+1;
            }
        }
        return left;
    }
}
```

### 执行结果二

![pic](https://github.com/hinkleung/leetcode/blob/main/34-在排序数组中查找元素的第一和最后一个位置/34-result1.png)
