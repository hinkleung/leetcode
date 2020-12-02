# 922-按奇偶排序数组 II

>简单  
>数组

### 题目描述

给定一个非负整数数组` A`， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 `A[i] `为奇数时，`i `也是奇数；当` A[i] `为偶数时，` i `也是偶数。

你可以返回任何满足上述条件的数组作为答案。


#### 示例

```
输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
```

#### 提示

1. `2 <= A.length <= 20000`
2. `A.length % 2 == 0`
3. `0 <= A[i] <= 1000`

</br>

### 题目分析

**提取题目关键点：**

1、数组至少有两个元素，并且数组长度是偶数

2、数组元素非负整数、一半是奇数、一半是偶数

3、要求对数组进行排序`A[i] `为奇数时，`i `也是奇数；当` A[i] `为偶数时，` i `也是偶数。**根据条件1、2可知，若有`i`是奇数，`A[i]`是偶数，则必存在`j`是偶数且`A[j]`是奇数。使其调换位置即可。**

</br>

**解题思路：**

双指针法

指针`i`从数组开头位置0，步长为2，搜索第一个奇数的位置

找到之后，指针`j`从1开始出发，步长也为2，搜索第一个偶数的位置，然后交换它们。循环遍历到`i`>=数组长度即可。



### 代码实现一

```java
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for(int i=0;i<A.length;i+=2){
            if(A[i]%2==1){
                while(A[j]%2==1){
                    j+=2;
                }
                swap(A,i,j);
            }
        }
        return A;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
```

  </br>



还有一种思路是，奇数索引指针和偶数索引指针先后找到**错位**的位置，调换它们的值即可。


### 代码实现二

```java
class Solution {
    public int[] sortArrayByParityII(int[] A) {
    
        //奇数指针
        int odd = 1;
        //偶数指针
        int even = 0;

        while(odd<A.length || even<A.length){
            while(odd<A.length && A[odd]%2==1){
                odd += 2;
            }
            while(even<A.length && A[even]%2==0){
                even += 2;
            }
            if(odd<A.length || even<A.length){
                swap(A,odd,even);
            }
        }
        return A;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

}
```



