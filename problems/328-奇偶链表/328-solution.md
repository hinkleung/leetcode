# 328-奇偶链表

>中等  
>链表

### 题目描述

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

#### 示例1

```
输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
```

#### 示例2

```
输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
```

**说明**

- 应当保持奇数节点和偶数节点的相对顺序。
- 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

</br>

### 题目分析

题目关键点提取一下：

1、奇偶节点指的是位置的奇偶

2、原地算法完成，即要在原链表上操作

3、空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。不开辟新空间来存储操作链表，用一次遍历就要完成。

</br>

这个题目就是考验大家对链表的操作，还有边界值的控制。

我的思路是：用到三个指针，一个指向奇数节点的指针`odd`，其始终指着排列好的**最后**一个奇数节点。另一个指针指向偶数指针`even`，其表示遍历到的**偶数**节点（注意：它的下一个节点一定是**奇数**节点）。最后一个指针一直指向`第一个`偶数节点。

初始化`odd`指向`head`，`evenHead`指向`head.next`，`even`指向`head.next`，开始遍历指针，`odd.next`指向`even`的下一个节点（奇数节点），`odd`往后移到`odd.next`，即指向**最新排好**的奇数节点，维护`even.next`指向`odd.next`，即下一个偶数节点，再把其移到这上面`even=even.next`。遍历的结束条件是even是链表最后一个节点或者已经移动到空。

宏观来看，就是不断把奇数节点单独连起来，偶数节点单独连起来，等待遍历结束，最后一个奇数节点的`next`指向第一个偶数节点`evenHead`，就完成任务了。

</br>

### 代码实现

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode odd = head;//奇数节点指针
        ListNode evenHead = head.next, even = evenHead;//偶数节点指针
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }
        odd.next=evenHead;
        return head;
    }
}
```

![结果图1](https://github.com/hinkleung/leetcode/blob/main/problems/328-奇偶链表/328-result.png)


