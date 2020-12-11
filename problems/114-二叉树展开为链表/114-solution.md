# 114-二叉树展开为链表

> 中等  
> 树、深度优先搜索

### 题目描述

给定一个二叉树，**原地**将它展开为一个单链表。

例如，给定二叉树

```
    1
   / \
  2   5
 / \   \
3   4   6
```

将其展开为：

```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

</br>

### 题目分析

这题可以完美地运用递归的思想来解决问题，把大问题化解成小问题，再结合小问题完成大问题。

要把root展开成单链表，如示例，就是要把其左子树接到右子树的位置，把右子树的节点接到刚接过来的左子树的右边，即所有节点通过right连接在一起。

递归思想：若要把root展开成单链表，首先要root.left和root.right都展开成单链表，再拼接到一起。那么问题就变成了将root.left展开成单链表，就要把root.left.left和root.left.right展开成单链表，再拼接……以此类推。那么用树的后序遍历法，遍历完左右子树，再处理当前节点。

当前节点的左子树不为空时，需要找到左子树的最右节点，将当前节点的右子树拼接到此处，再把其左子树移到右子树，随后左子树置空。完成！

### 代码实现

```java
class Solution {
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        if(root.left!=null){
            //找到左子树的最右节点
            TreeNode cur = root.left;
            while(cur.right!=null){
                cur=cur.right;
            }
            //右子树接到这里
            cur.right=root.right;
            root.right=root.left;
            //当前root的左子树置为空
            root.left=null;
        }
    }
}
```

### 执行结果

![pic](https://github.com/hinkleung/leetcode/blob/main/problems/114-二叉树展开为链表/114-result.png)
