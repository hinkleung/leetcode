public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{5,3,1,6,8,4,6,2};
        Main m = new Main();
        m.quickSort(nums,0,nums.length-1);
        for(int num : nums){
            System.out.print(num+" ");
        }
    }


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

}