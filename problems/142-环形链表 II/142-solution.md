# 141-环形链表 II

> 中等  
> 数学、链表、双指针

### 题目描述

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。**注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。**

说明：不允许修改给定的链表。

**进阶：**

- 你是否可以使用 `O(1)` 空间解决此题？

#### 示例1:

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/142-环形链表%20II/142-pic.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
```

#### 示例2:

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/142-环形链表%20II/142-pic2.png)

```
输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。
```

#### 示例3:

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/142-环形链表%20II/142-pic3.png)

```
输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。
```

**提示：**

- 链表中节点的数目范围在范围 `[0, 104]` 内
- `-105 <= Node.val <= 105`
- `pos` 的值为 `-1` 或者链表中的一个有效索引
- </br>

### 题目分析

本题是[141-环形链表](https://github.com/hinkleung/leetcode/blob/main/problems/141-环形链表/141-solution.md)的进阶，除了要求出是否是环形链表外，还需要求出环的入口点，本题主要运用数学推导+双指针来解决。

推导过程：

1、设从head到环入口共有a个节点（不包含入口点），环里共有b个节点，那么链表里共有a+b个节点。设慢指针slow每次走1步，快指针fast每次走2步。

2、当fast与slow相遇时，此时slow走了s步，fast走了f步，那么①f=2s；

3、fast比slow在环中多走了n圈，那么②f=s+nb；（fast是套圈追上slow的，所以fast比slow多走了环长度的整数倍）

4、由②-①得：③f=2nb，④s=nb，所以f走了2n倍环长，而s走了n倍环长。

5、一个节点从head出发，那么**每次经过入环点的步数为** k=a+nb（经过a步之后，周期为nb经过入环点）

6、由④得此时slow指针走了nb步，那么它再走a步就可以到达入环点，此时另设一个指针point从head出发，其只需要走a步，就可以到达入环点，与slow指针相遇。所以**它们相遇的地方就是入环点**

**提炼要点：**

- 走a+nb步一定是在环入口
- 第一次相遇时慢指针已经走了nb步



### 代码实现

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(true){
            if(fast==null || fast.next==null){
                return null;//不成环
            }
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow){
                break;//找到第一个相遇点
            }
        }
        //此时slow停在第一个相遇点，再往前走a步就是入口点
        ListNode point = head;
        while(point!=slow){
            point=point.next;
            slow=slow.next;
        }
        return point;
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/142-环形链表%20II/142-result.png)
