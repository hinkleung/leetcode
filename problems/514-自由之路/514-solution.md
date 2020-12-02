# 514-自由之路

>困难  
>递归、深度优先搜索、动态规划

### 题目描述

电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。

给定一个字符串 **ring**，表示刻在外环上的编码；给定另一个字符串**key**，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。

最初，**ring**的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 **ring**以使 **key**的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 **key**中的所有字符。

旋转 **ring**拼出 **key**字符 `key[i] `的阶段中：

您可以将 **ring**顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 **ring**的一个字符与 12:00 方向对齐，并且这个字符必须等于字符` key[i]` 。
如果字符 `key[i]` 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 **key**的下一个字符（下一阶段）, 直至完成所有拼写。  



#### 示例

![514ring](https://github.com/hinkleung/leetcode/blob/main/problems/514-自由之路/ring.jpg)

```
输入: ring = "godding", key = "gd"
输出: 4
解释:
 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 当然, 我们还需要1步进行拼写。
 因此最终的输出是 4。
```

  



#### 提示

* ring 和 key 的字符串长度取值范围均为 1 至 100；

* 两个字符串中都只有小写字符，并且均可能存在重复字符；

* 字符串 key 一定可以由字符串 ring 旋转拼出。  

  

### 题目分析一

今天（2020-11-11）的每日一题514，是一道**困难**题。~~看完题目，为了更好地寻找思路，我打开了steam。~~

看完题目，想到应该需要用深度优先搜索，因为前面旋转的选择会影响到后面的选择，但不太能写下代码，于是打开评论区和题解区寻找思路。我试了题解区里的两种方式，一种是用深度优先搜索+记忆法，另一种是动态规划。

先来讲第一种 **深度优先搜索+记忆法**

#### 思路：

1、通过旋转+按按钮拼出key中所有字符需要的最少步数。

按按钮的步数固定是1，所以关键看旋转的步数。

对于key中的每一个字符，在ring可能存在重复的字符，所以当要从某个位置旋转到key中的指定字符时，可能存在多个可选位置。

我们需要一个dfs去尝试所有的位置：选定一个位置，基于当前的选择，再作出下一步的选择（往下递归）

每次选择都有两种方式把key字符旋转到12:00方向：顺时针、逆时针  

2、如何定义dfs递归函数

根据当前12:00的方向对齐的位置`startIndex`，key中的字符索引`keyIndex`，返回从`startIndex`旋转到key中`keyIndex`的字符一直到key末尾字符，所需的最少步数。

所以结果是从底往上叠加的，从最末尾一个字符的旋转次数一直返回到最初的字符。

递归结束条件：当遍历完key时，返回0  


3、需要用一个map去记录ring中每种字符的位置。递归到每一层，都需要遍历当前字符在ring中的位置，每个都需要选一遍，最终选出当前层最少的步数。  


4、距离`dis`怎么算，因为有两种旋转方式：

`Math.abs(当前12:00索引 - 字符目标位置索引)`

`len(ring) - Math.abs(当前12:00索引 - 字符目标位置索引)`

取最小值，当前层步数就是`dis+1`  

  

5、因为旋转过程中会出现重复的计算，从某个位置旋转到key某个字符的最短路径，即入参`startIndex`，`keyIndex`会有重复的，可以用一个数组记忆起来。（试过了，不用记忆法会超时）。  


### 代码实现一

```java
class Solution {

    //深度优先搜索+记忆法

    //记录ring中每个字符的位置
    HashMap<Character, List<Integer>> map;
    //int[i][j]表示当前圆盘位置12:00指着位置i，转到key中位置为j的字符，最短的距离
    int[][] memo;

    public int findRotateSteps(String ring, String key) {
        map = new HashMap<>(ring.length());
        memo = new int[ring.length()][key.length()];
        //记录ring的每种字符的位置
        for(int i=0;i<ring.length();i++){
            if(map.containsKey(ring.charAt(i))){
                map.get(ring.charAt(i)).add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(ring.charAt(i),list);
            }
        }
        return dfs(ring,key,0,0);
    }

    /**
     * startIndex表示当前圆盘12:00方向指着的位置(其实就是上一步的字符的位置)，keyIndex表示当前key中要寻找字符在key中的位置
     * 函数返回的结果是startIndex转到key当前字符最短的步数
     */
    private int dfs(String ring, String key, int startIndex, int keyIndex){
        if(keyIndex==key.length()) return 0;
        if(memo[startIndex][keyIndex]!=0){
            //有缓存结果
            return memo[startIndex][keyIndex];
        }
        char keyChar = key.charAt(keyIndex);
        //获取key在ring中的位置list
        List<Integer> list = map.get(keyChar);
        int res = Integer.MAX_VALUE;
        for(int index : list){
            //递归下一步返回的结果
            int nextDfsRes = dfs(ring,key,index,keyIndex+1);
            //顺、逆时针中最短的距离
            int dis = Math.min(Math.abs(startIndex-index), ring.length()-Math.abs(startIndex-index));
            res = Math.min(res, dis+1+nextDfsRes);
        }
        //缓存结果
        memo[startIndex][keyIndex] = res;
        return res;
    }
}
```

  



### 提交结果一

![514提交结果图2](https://github.com/hinkleung/leetcode/blob/main/problems/514-自由之路/514-result2.png)

  


### 题目分析二

第二种 **动态规划**

#### 思路：

1、第一一个数组`dp[i][j]`，表示从开始到拼接完成`key[0:i]`, 在`ring[j]==key[i]`的时候最小步数。

`dp[i][j]`需通过`dp[i-1][jn] + m + 1`算出来，`jn`表示上一个匹配的12:00方向的字符在ring中的位置，`m`表示`jn`旋转到当前字符的最短距离。

当`jn`有多个时`j1`、`j2` 、`j3`...，取最小的距离。  


2、同样需要用一个map去记录ring中每种字符的位置。  

  

3、初始化`dp[0]`数组，即匹配key第一个字符需要的步数数组。  

  

4、距离怎么算，因为有两种旋转方式：

`Math.abs(当前12:00索引 - 字符目标位置索引)`

`len(ring) - Math.abs(当前12:00索引 - 字符目标位置索引)`

所以`dp[0][j] = Math.min(j,ringLen-j) + 1;`  

  

5、最终结果返回`min(dp[keyLen-1])`  





### 代码实现二

```java
class Solution {
    public int findRotateSteps(String ring, String key) {
        int ringLen = ring.length();
        int keyLen = key.length();
        //用来记录ring中每个字符出现的位置,key是ring的字符，value是位置其位置list
        //比如g->[0]、d->[2,3]
        Map<Character,List<Integer>> map = new HashMap<>(ringLen);
        for(int i=0;i<ringLen;i++){
            if(map.containsKey(ring.charAt(i))){
                map.get(ring.charAt(i)).add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(ring.charAt(i),list);
            }
        }

        //动态规划dp数组,dp[i][j]表示从游戏开始到拼接完成key[0:i], 在ring[j]==key[i]的时候最小步数
        int[][] dp = new int[keyLen][ringLen];
        for(int i=0;i<keyLen;i++){
            for(int j=0;j<ringLen;j++){
                //用int最大值来填充，等下用打擂台法，存储下最步数
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //初始化拼接key的第一个字符的步数dp[0][j]
        for(int j : map.get(key.charAt(0))){
            //因为最初，ring的第一个字符与12:00方向对齐，要旋转到位置j，就要走Math.min(j,ringLen-j)步，且按下按钮+1
            dp[0][j] = Math.min(j,ringLen-j) + 1;
        }

        //遍历key剩余的字符，从1开始，从前往后推，算出dp[i][j]
        //如 dp[1][1]=min(dp[0][0]+m+1,dp[0][2]+m+1,dp[0][4]+m+1)
        //其中m为上一个旋转点到当前字符的最短距离（因为要当前字符旋转到12:00方向）
        for(int i=1;i<keyLen;i++){
            //获取key当前字符在ring中的位置
            List<Integer> ringPosList = map.get(key.charAt(i));
            for(int j : ringPosList){
                //上一个字符的位置的list
                List<Integer> lastPosList = map.get(key.charAt(i-1));
                for(int last : lastPosList){
                    //m为上一个旋转点到当前字符的最短距离
                    //dp[i][j] = dp[i-1][last]+m+1
                    //dp[i][j]的初始值为Integer.MAX_VALUE(打擂台法)
                    int m = Math.min(Math.abs(j-last), ringLen-Math.abs(j-last));
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][last]+m+1);
                }
            }
        }
        //最后结果是dp[keyLen-1]的最小值
        return Arrays.stream(dp[keyLen-1]).min().getAsInt();
    }
}
```

  

### 提交结果二

![514提交结果图](https://github.com/hinkleung/leetcode/blob/main/problems/514-自由之路/514-result.png)
