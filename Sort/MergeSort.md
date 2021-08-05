# 归并排序

### 原理

把一个数组分开两半，分别递归这两半，递归完成以后，借助额外空间合并两个有序数组，得到更长的有序数组。

时间复杂度：*O*(*N*log*N*)

空间复杂度：*O*(*N*)



### 代码思路

1、将数组对半分，分别递归 

2、递归完后，这两部分都是有序数组（递归到底层是只有一个元素，直接返回）

3、合并这两个有序的数组，得到一个完整的有序数组，返回上一层



### 代码实现

```java
public class Main {

    
    public int[] sortArray(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length-1, temp);
        return nums;
    }
	
    // 归并排序
    public void mergeSort(int[] nums, int left, int right, int[] temp){
        if(left>=right){
            return;
        }
        //对半递归
        int mid = (right + left) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid+1, right, temp);
        //得到有序的两个数组，合并起来
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
    }

    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        int i=left;
        int p1=left;
        int p2=mid+1;
        while(p1<=mid && p2<=right){
            temp[i++]=nums[p1]<nums[p2]?nums[p1++]:nums[p2++];
        }
        //剩余的按顺序添加到后面的位置
        while(p1<=mid){
            temp[i++]=nums[p1++];
        }
        //剩余的按顺序添加到后面的位置
        while(p2<=right){
            temp[i++]=nums[p2++];
        }
        //从临时数组复制到原数组
        for(int j=left;j<=right;j++){
            nums[j]=temp[j];
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{5,3,1,6,8,4,6,2};
        Main m = new Main();
        m.sortArray(nums);
        for(int num : nums){
            System.out.print(num+" ");
        }
    }

}
```

