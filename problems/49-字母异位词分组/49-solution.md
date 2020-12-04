# 49-字母异位词分组

> 中等  
> 哈希表、字符串

### 题目描述

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

#### 示例1:

```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

```

**说明：**

- 所有输入均为小写字母。
- 不考虑答案输出的顺序。

</br>

### 题目分析

很容易想到用Map来分类，异位词只需要把字母排序就能得到相同的字符串，然后用Map来归类就行了。

这题很简单，没什么好说的，就是集合操作了。

### 代码实现

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String str = String.valueOf(chars);
            if(!map.containsKey(str)){
                map.put(str,new ArrayList<>());
            }
            map.get(str).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/49-字母异位词分组/49-result.png)
