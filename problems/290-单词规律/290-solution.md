# 290-单词规律

> 简单  
> 哈希表

### 题目描述

给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

#### 示例1:

```
输入: pattern = "abba", str = "dog cat cat dog"
输出: true
```

#### 示例2:

```
输入:pattern = "abba", str = "dog cat cat fish"
输出: false
```

#### 示例3:

```
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
```

#### 示例4:

```
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
```

**说明:**
你可以假设 `pattern` 只包含小写字母， `str` 包含了由单个空格分隔的小写字母。  

</br>

### 题目分析

提炼一下题目的要点：

1、pattern和str的对应关系，跟小时候学的aabb、abb词语同一个意思，即aabb->开开心心

2、str中的单词数量要跟pattern的字符数量一致

3、pattern中的字符只对应一个单词，换一个不行（这不就是哈希表吗？）

解题思路：

1、把s以空格分隔成单词数组，新建一个map存pattern字符与单词之间的关系

2、遍历pattern中的字符，判断其是否在map中

3、在map中：比较当前单词跟map中的value是否一致，不一致则返回false，因为对应关系不一致

4、不在map中：查询当前单词是否在map中作为value存在，是的话就返回false，因为对应关系不一致

### 代码实现

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] array = s.split(" ");
        if(pattern.length()!=array.length){
            return false;
        }
        Map<Character,String> map = new HashMap<>();
        for(int i=0;i<pattern.length();i++){
            char temp = pattern.charAt(i);
            if(map.containsKey(temp)){
                if(!map.get(temp).equals(array[i])){
                    return false;
                }
            }else{
                //不存在这个pattern字符的key但却存在当前单词在map中作为value
                if(map.containsValue(array[i])){
                    return false;
                }
                map.put(temp,array[i]);
            }
        }
        return true;
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/290-单词规律/290-result.png)
