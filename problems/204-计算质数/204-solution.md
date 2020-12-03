# 204-计算质数

> 简单  
> 数学

### 题目描述

统计所有小于非负整数 *`n`* 的质数的数量。

#### 示例1:

```
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
```

#### 示例2：

```
输入：n = 0
输出：0
```

#### 示例3：

```
输入：n = 1
输出：0
```

</br>

### 题目分析

质数定义：除了1和自身外，没有其他因数的自然数。

简单题，ok，直接双重循环，遍历n前的每个数，每个数遍历从2到本身，没有整除的就是质数，于是提交超时……

改进一下，对于一个数`num`，其实只需要判断到`根号num`就可以知道是不是质数了，比如12

```
12=2*6
12=3*4
12=根号12 * 根号12
12=4*3
12=6*2
```

可以看到，遍历到根号12之后，因数其实是重复了的。改进代码如下：

```java
class Solution {
    public int countPrimes(int n) {
        int count=0;
        for(int i=2;i<n;i++){
            if(isPrimes(i)) count++;
        }
        return count;
    }

    private boolean isPrimes(int n){
        for(int i=2;i*i<=n;i++){
            if(n % i==0){
                return false;
            }
        }
        return true;
    }
}
```

提交之后只超越了5%



进阶一下，当你知道2是质数时，那么`2*2=4`，`2*3=6`，`2*4=8`……2的倍数都不是质数

同理，3是质数，那么3的倍数也不是质数。

用一个数组来标记当前数是否是质数，当遍历到是质数时，就可以标记它的倍数都不是质数，代码如下：

```java
class Solution {
    public int countPrimes(int n) {
        int count=0;
        boolean flag[] = new boolean[n];
        Arrays.fill(flag,true);
        for(int i=2;i<n;i++){
            if(flag[i]){
                for(int j=i*2;j<n;j+=i){
                    flag[j]=false;
                }
            }
        }
        for(int i=2;i<n;i++){
            if(flag[i]) count++;
        }
        return count;
    }
}
```

再优化一下：

内层循环的初始条件可以优化成`j=i*i`，因为`i*(i-1)、i*(i-2)……`，`i`之前的数已经被当过因数标记过的了。

外层循环的结束条件可以优化成`i<根号n`，因为只要判断到根号n之前的数已经找过他们对应的倍数了，所以后面的都不需要去遍历标记了。

```java
class Solution {
    public int countPrimes(int n) {
        int count=0;
        boolean flag[] = new boolean[n];
        Arrays.fill(flag,true);
        for(int i=2;i*i<n;i++){
            if(flag[i]){
                for(int j=i*i;j<n;j+=i){
                    flag[j]=false;
                }
            }
        }

        for(int i=2;i<n;i++){
            if(flag[i]) count++;
        }
        return count;
    }

}
```

