# 139-单词拆分

> 中等  
> 动态规划

### 题目描述

给定一个**非空**字符串 *s* 和一个包含**非空**单词的列表 *wordDict*，判定 *s* 是否可以被空格拆分为一个或多个在字典中出现的单词。

**说明：**

- 拆分时可以重复使用字典中的单词。
- 你可以假设字典中没有重复的单词。

#### 示例1:

```
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
```

#### 示例2:

```
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
```

#### 示例3:

```
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
```



</br>

### 题目分析

使用动态规划解决问题，定义dp[i]数组，表示字符串中s[0...i-1]能否拆分成wordDict中的单词，则题目要求是求出dp[s.length()]，设初始值dp[0]=true（这个值没实际意义，是为了计算递推出dp[1]和后面的值）。

于是从i=1开始遍历，求出s[0...i-1]能否拆分成wordDict中的单词，嵌套一层循环，声明变量j从0开始，j是s[0...i-1]的拆分点，拆分成两部分d[j]和s[j...i-1]，因为dp[j]是前面已求的结果，只需要和contains(s.substring(j,i))作与运算，若dp[i]=true，即可以跳出该嵌套循环。

### 代码实现

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //利用哈希表来匹配
        Set<String> wordDictSet = new HashSet<>(wordDict);
        //设dp[i]表示字符串s中[0...i-1]能否拆分成wordDict中的单词
        //动态规划求出dp[s.length()]，所以需要创建数组空间s.length()+1
        boolean[] dp = new boolean[s.length()+1];
        //边界值空串的时候是true
        dp[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                //j是s[0...i-1]的拆分点，dp[j]已经是已知结果
                if(dp[j]&&wordDictSet.contains(s.substring(j,i))){
                    dp[i]=true;
                    //得到true就不用再拆分了
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/139-单词拆分/139-result.png)
