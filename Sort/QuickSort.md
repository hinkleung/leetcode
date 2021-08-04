# 快速排序

### 原理

定一个中心点（pivot），把指定范围内的数组，比pivot小的放在其左边，比pivot大的放在其右边。再递归分别对pivot的左边和pivot的右边进行同样的操作，数组范围剩下一个元素时结束并返回。



### 代码思路

1、取最左数为基准，并用常量pivot存着  

2、定义两个指针，low指向最左，high指向最右  

3、先从最右往左扫描，找出比pivot小的数，把其赋予给low位置。然后从左往右扫描，找出比pivot大的数，赋予给high位置。当low<high时，结束，此时肯定是low==high  

4、把pivot赋予给low位置  

5、递归pivot的左边和右边，不包含pivot本身  



### 代码实现

```java
public class Main {

    public void quickSort(int[] nums, int left, int right) {
        if(nums.length==0 || left>=right) return;
        //取出最左数为pivot
        int pivot = nums[left];
        //partition
        int low = left, high = right;
        while(low < high){
            while(low<high && nums[high]>=pivot){
                high--;
            }
            nums[low]=nums[high];
            while(low<high && nums[low]<=pivot){
                low++;
            }
            nums[high]=nums[low];
        }
        //跳出循环时，low和high相等，此时的low或者high就是pivot的最终位置
        nums[low]=pivot;

        quickSort(nums,left,low-1);
        quickSort(nums,low+1,right);
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{5,3,1,6,8,4,6,2};
        Main m = new Main();
        m.quickSort(nums,0,nums.length-1);
        for(int num : nums){
            System.out.print(num+" ");
        }
    }

}
```

