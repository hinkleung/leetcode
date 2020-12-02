# 767-重构字符串

> 中等  
> 贪心算法、数组

### 题目描述

给定一个字符串`S`，检查是否能重新排布其中的字母，使得两相邻的字符不同。

若可行，输出任意可行的结果。若不可行，返回空字符串。

#### 示例1:

```
输入: S = "aab"
输出: "aba"
```

#### 示例2：

```
输入: S = "aaab"
输出: ""
```

**提示：**

- `S` 只包含小写字母并且长度在`[1, 500]`区间内。

</br>

### 题目分析

这题用贪心算法，先找出最多出现次数的字符，如果要使两相邻的字符不同，那么最大出现次数不能大于`(len+1)/2`，该临界值为偶数位置的个数，超过临界值则必然会出现相邻重复的情况，其中字符串`S`的长度为`len`。我们可以先把偶数位置填满，先用最大出现次数的字符填充，随后填其他字符，偶数填完，再填奇数字符，由于隔着填，就算某个元素出现次数大于2次，也不会有相邻重复的情况。（若有，则说明最大出现次数的字符没找对）

</br>

图解：

当`len`为偶数时，如`len=8`，最大出现字符`a`出现次数为4时，如下图：

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/767-重构字符串/767-pic2.jpg)



当`len`为奇数时，如`len=9`，最大出现字符`a`出现次数为5次时，如下图：

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/767-重构字符串/767-pic1.jpg)



当`len`为偶数时，如`len=8`，最大出现字符`a`出现次数为3次时，如下图：

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/767-重构字符串/767-pic3.jpg)



*问：为什么不能从奇数位置开始填充？*

*答：因为当`len`为奇数时，奇数位置个数少于临界值`(len+1)/2`，不够位置填。*



### 代码实现

```java
class Solution {
    public String reorganizeString(String S) {
        char[] sToArray = S.toCharArray();
        //字符出现字数
        int[] times = new int[26];
        for (char c : sToArray) {
            times[c-'a']++;
        }
        int len = S.length();
        int max = 0;
        int maxCharAt = 0;
        //临界值
        int threshold = (len+1)/2;
        for(int i=0;i<26;i++){
            if(times[i]>max){
                max = times[i];
                maxCharAt = i;
                //最大出现次数大于临界值，则不能使得两个相邻字符不相同
                if(max > threshold){
                    return "";
                }
            }
        }
        char[] res = new char[len];
        int cur = 0;
        //从偶数位置放置最多出现次数的字符。
        //不能在奇数位置放，当len=3时，且最多出现次数的字符有2个时，奇数位置不够放
        while(times[maxCharAt]-->0){
            res[cur]=(char)(maxCharAt+'a');
            cur+=2;
        }
        //然后把剩余的字符放到其余位置
        for(int i=0;i<26;i++){
            while(times[i]-->0){
                if(cur>=len){
                    cur=1;
                }
                res[cur]=(char)(i+'a');
                cur+=2;
            }
        }
        return new String(res);
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/767-重构字符串/767-result.png)

