# 13-罗马数字转整数

> 简单  
> 数学、数组

### 题目描述

罗马数字包含以下七种字符: `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 `II `，即为两个并列的 1。12 写做` XII` ，即为`X` + `II` 。 27 写做  `XXVII`, 即为 `XX` + `V` +`II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在`V` (5) 和 `X` (10) 的左边，来表示 4 和 9。

- `X` 可以放在 `L` (50) 和 `C` (100) 的左边，来表示 40 和 90。 

- `C` 可以放在 `D` (500) 和 `M` (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。


#### 示例1:

```
输入: "III"
输出: 3
```

#### 示例2:

```
输入: "IV"
输出: 4
```

#### **示例 3:**

```
输入: "IX"
输出: 9
```

#### 示例4:

```
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
```

#### 示例5:

```
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

**提示：**

- 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
- IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
- 关于罗马数字的详尽书写规则，可以参考 [罗马数字 - Mathematics](https://b2b.partcommunity.com/community/knowledge/zh_CN/detail/10753/%E7%BD%97%E9%A9%AC%E6%95%B0%E5%AD%97#knowledge_article) 。

</br>

### 题目分析

首先用一个Map去存储字符与数值的关系。把数字从左往右遍历，从Map中获取字符所代表的数字。

通常情况下，罗马数字中小的数字在大的数字的右边。但存在特例，这几种特例，是小数放在大数的左边，数值为（大数 - 小数）。

要注意的是，这种情况只会是一个小数紧挨后面一个大数，由于算总数值只涉及到加减法，如果碰到一个数小于其后面的数，那么将当前数减掉就行了，其余情况就是加到总数里去。

### 代码实现

```java
class Solution {
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int res = 0;
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(i+1!=chars.length && map.get(chars[i])<map.get(chars[i+1])){
                res -= map.get(chars[i]);
            }else{
                res += map.get(chars[i]);
            }
        }
        return res;
    }
}
```
