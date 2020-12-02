# 147-对链表进行插入排序

> 中等  
> 双指针

### 题目描述

对链表进行插入排序。

![动态图](https://github.com/hinkleung/leetcode/blob/main/problems/147-对链表进行插入排序/147-problem.gif)


插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

 

**插入排序算法：**

1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
3. 重复直到所有输入数据插入完为止。

#### 示例1:

```
输入: 4->2->1->3
输出: 1->2->3->4
```

#### 示例2：

```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```

</br>

### 题目分析

这是一个链表操作问题。我们可以用双指针来完成，一个指向要排序的元素，一个指向该插入位置的前驱（找到前驱修改前驱指针的next就可以拼接要插入的元素了），还需要创建一个`dummyHead`，因为插入的位置可能是第一个位置，这时候的前驱就是`dummyHead`。

下面图示模拟一下插入排序过程，下面是`[-1,5,3,4,0]`一开始的情况。

![pic1](https://github.com/hinkleung/leetcode/blob/main/problems/147-对链表进行插入排序/147-pic1.jpg)

模拟排完 `-1,5`，`cur`指向3

![pic2](https://github.com/hinkleung/leetcode/blob/main/problems/147-对链表进行插入排序/147-pic2.jpg)

### 代码实现一

```java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = head;
        ListNode pre = dummyHead;
        while(cur!=null){
            while(pre.next!=null && pre.next.val<cur.val){
                pre=pre.next;//找到插入点
            }
            ListNode temp = cur.next;//temp用来暂存cur的next
            cur.next = pre.next;
            pre.next=cur;
            cur=temp;//继续遍历后面未排序的元素
            pre=dummyHead;
        }
        return dummyHead.next;
    }
}
```

以上代码提交后，运行时间只超过22%的人。看了其他人的答案，加了一行代码，就提高到了超过97%的人。其实就是，`pre`指针不用每次排序完某个元素就重新指向`dummyHead`，先判断当前`pre`的点是不是插入点`(pre.val<cur.val)`，就可以在此处直接插入了，当然只有元素大部分是顺序排的时候，这个代码才会起很好的作用。

### 代码实现二

```java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = head;
        ListNode pre = dummyHead;
        while(cur!=null){
            if(pre.val>cur.val){
                pre = dummyHead;//当前pre的位置不符合插入位置
            }
            while(pre.next!=null && pre.next.val<cur.val){
                pre=pre.next;
            }
            ListNode temp = cur.next;
            cur.next = pre.next;
            pre.next=cur;
            cur=temp;
        }
        return dummyHead.next;
    }
}
```





