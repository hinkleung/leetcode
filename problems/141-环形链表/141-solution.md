# 141-环形链表

> 简单  
> 链表、双指针

### 题目描述

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

**进阶：**

你能用 O(1)（即，常量）内存解决此问题吗？

#### 示例1:

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/141-环形链表/141-pic1.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

#### 示例2:

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/141-环形链表/141-pic2.png)

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

#### 示例3:

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/141-环形链表/141-pic3.png)

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

**提示：**

- 链表中节点的数目范围是 `[0, 104]`
- `-105 <= Node.val <= 105`
- `pos` 为 `-1` 或者链表中的一个 **有效索引** 。

</br>

### 题目分析

题目看起来很难读懂，你忽略掉pos就行了，pos是用来给题目解析答案用的，并不作为输入的参数。

判断链表上有没有环，用快慢指针法（Floyd判圈法、龟兔赛跑算法）。

设快fast慢slow两个指针同时同head出发，若链表无环，则fast指针一直在slow前面，并且最终会指向null。若链表有环，那么fast指针会先进入环，开始转圈，当slow进入环时，此时相当于fast开始追赶slow，由于fast比slow快，所以fast肯定可以追上slow（相遇），并且套了slow若干圈。（还有个思路，fast比slow快，他们的速度差值为相对速度，相当于slow静止不动，fast在套圈跑向slow，肯定会遇到的）

我下面的代码是fast从head.next开始，开始点不在head不影响，因为只要两个快慢指针掉进圈中，他们迟早会相遇。

### 代码实现

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast!=null && fast.next!=null){
            if(fast==slow){
                return true;
            }
            fast=fast.next.next;
            slow=slow.next;
        }
        return false;
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/141-环形链表/141-result.png)
