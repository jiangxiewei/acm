# acm

毕业后吃饱了撑没事干写写题,缅怀以前打ACM的日子.

## 题目分类

### 树

| 题号  |     题名      |   难度   |               简介                |                                            类路径                                             |
|:---:|:-----------:|:------:|:-------------------------------:|:------------------------------------------------------------------------------------------:|
| 103 | 二叉树的锯齿形层序遍历 | 中等(简单) |            基础的层序遍历题             | [leetCode.repository.No103二叉树的锯齿形层序遍历.java](src/leetCode/repository/No103二叉树的锯齿形层序遍历.java) |
| 543 |   二叉树的直径    |   简单   | 分治法,算出每个节点的左最大深度和右最大深度,比较并返回更大的 |        [leetCode.repository.No543二叉树的直径](src/leetCode/repository/No543二叉树的直径.java)         |

### 栈

|      题号       |   题名   |   难度   |       简介        |                                                  类路径                                                   |
|:-------------:|:------:|:------:|:---------------:|:------------------------------------------------------------------------------------------------------:|
|  leetcode 32  | 最长有效括号 | 困难(中等) | 贪心/栈/动态规划 多种解法  |             [leetCode.repository.No32最长有效括号.java](src/leetCode/repository/No32最长有效括号.java)             |
| leetcode 445  | 两数相加II |   中等   |        栈        |              [leetCode.repository.No445两数相加II](src/leetCode/repository/No445两数相加II.java)               |
| leetcode 739  |  每日温度  |   中等   |    单调栈 或 贪心     | [leetCode.repository.No739DailyTemperatures.java](src/leetCode/repository/No739DailyTemperatures.java) |
| leetcode 856  | 括号的分数  |   中等   | 1.暴力 2.栈 3.数学思维 |                            [代码连接](src/leetCode/repository/No856括号的分数.java)                             |
| leetcode 1544 | 整理字符串  |   简单   |  1.模拟 2.栈 3.递归  |    [leetCode.No1544MakeTheStringGreat.java](src/leetCode/repository/No1544MakeTheStringGreat.java)     |

### 搜索

|      题号      |          题名           |   难度    |                      简介                      |                                                                类路径                                                                |
|:------------:|:---------------------:|:-------:|:--------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------:|
| leetcode 10  |        正则表达式匹配        | 困难(中等吧) |                  依旧普通的递归搜索题                  |                                [leetCode.repository.No10.java](src/leetCode/repository/No10.java)                                 |
| leetcode 22  |         括号生成          |   简单    |                      ez                      |                [leetCode.repository.No22GenerateParentheses](src/leetCode/repository/No22GenerateParentheses.java)                |
| leetcode 33  |       搜索旋转排序数组        |   中等    |                     二分搜索                     |                           [leetCode.repository.No33搜索旋转排序数组](src/leetCode/repository/No33搜索旋转排序数组.java)                           |
| leetcode 34  | 在排序数组中查找元素的第一个和最后一个位置 |   中等    |                二分搜索,但要找最左和最右                 |              [leetCode.repository.No34在排序数组中查找元素的第一个和最后一个位置](src/leetCode/repository/No34在排序数组中查找元素的第一个和最后一个位置.java)              |
| leetcode 209 |       长度最小的子数组        |   中等    | 滑动窗口法,可让左右区间滑动搜索实现O(n),也可用二分法针对每个左开区间寻找右闭区间. |                       [leetCode.repository.No209长度最小的子数组.java](src/leetCode/repository/No209长度最小的子数组.java)                        |
| leetcode 297 |     二叉树的序列化与反序列化      | 困难(中等吧) |                   树遍历也是搜索                    | [leetCode.repository.No297SerializeAndDeserializeBinaryTree](src/leetCode/repository/No297SerializeAndDeserializeBinaryTree.java) |

### 动态规划

|      题号       |    题名     |   难度   |                     简介                      |                                                                        类路径                                                                        |
|:-------------:|:---------:|:------:|:-------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------:|
|  leetcode 5   |  最长回文子串   |   简单   |                  找准状态转移逻辑                   |                                      [leetCode.repository.No5最长回文子串](src/leetCode/repository/No5最长回文子串.java)                                      |
|  leetcode 53  |   最大子序和   | 简单(中等) |               dp思路或者可以用数学推导求                |                                     [leetCode.repository.No53最大子数组和](src/leetCode/repository/No53最大子数组和.java)                                     |
|   面试题17.16    |    按摩师    |   简单   |         尝试从DFS->尾递归->DP的一个推断流程,注释里有         |                      [leetCode.repository.Mst_17_16_TheMasseuseLcci](src/leetCode/repository/Mst_17_16_TheMasseuseLcci.java)                      |
|     面试题46     | 把数字翻译成字符串 |   简单   |                   也可以用搜索                    | [leetCode.repository.Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof](src/leetCode/repository/Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof.java) |
| leetcode 416  |  分割等和子集   |   中等   | 01背包题,由于"物品"没有"价值",所以结果值可以只用true false代表可达性 |                   [leetCode.repository.No416PartitionEqualSubsetSum](src/leetCode/repository/No416PartitionEqualSubsetSum.java)                   |
| leetcode 1143 |  最长公共子序列  |   中等   |                   二维动态规划                    |                                  [leetCode.repository.No1143最长公共子序列](src/leetCode/repository/No1143最长公共子序列.java)                                  |

### 并查集

|      题号      |                  题名                  | 难度 |       简介       |                                                             类路径                                                             |
|:------------:|:------------------------------------:|:--:|:--------------:|:---------------------------------------------------------------------------------------------------------------------------:|
| leetcode 990 | satisfiability-of-equality-equations | 简单 | 模板题,可以测试自己写的模板 | [leetCode.No990SatisfiabilityOfEqualityEquations.java](src/leetCode/repository/No990SatisfiabilityOfEqualityEquations.java) |

### 树状数组

|      题号      |      题名       | 难度 |         简介          |                                                          类路径                                                          |
|:------------:|:-------------:|:--:|:-------------------:|:---------------------------------------------------------------------------------------------------------------------:|
| leetcode 315 | 计算右侧小于当前元素的个数 | 困难 | 树状数组 + 离散化,注意下数据有重复 | [leetCode.No315CountOfSmallerNumbersAfterSelf.java](src/leetCode/repository/No315CountOfSmallerNumbersAfterSelf.java) |

### 图论

#### 拓扑排序

| 题号  |  题名   | 难度 |        简介        |                                      类路径                                       |
|:---:|:-----:|:--:|:----------------:|:------------------------------------------------------------------------------:|
| 210 | 课程表II | 中等 | 拓扑排序 + 深度/广度优先遍历 | [leetCode.repository.No210课程表II.java](src/leetCode/repository/No210课程表II.java) |

#### Dijkstra

|      题号       |   题名    | 难度 |       简介       |                                                       类路径                                                       |
|:-------------:|:-------:|:--:|:--------------:|:---------------------------------------------------------------------------------------------------------------:|
|  leetcode 45  | 跳跃游戏II  | 中等 | 可将图画出来,发现就是最短路 |                    [leetCode.repository.No45跳跃游戏II](src/leetCode/repository/No45跳跃游戏II.java)                    |
| leetcode 1514 | 概率最大的路径 | 中等 |    Dijkstra    | [leetCode.No1514PathWithMaximumProbability.java](src/leetCode/repository/No1514PathWithMaximumProbability.java) |

#### 网络流

|    题号    |      题名      | 难度 |             简介             |                             类路径                              |
|:--------:|:------------:|:--:|:--------------------------:|:------------------------------------------------------------:|
| hdu 3549 | Flow Problem | 简单 | 完全模板题,可以测试自己写的网络流模板,但是输入有坑 | [hdu.No3549FlowProblem.java](src/hdu/No3549FlowProblem.java) |

## LeetCode刷题

> 括号里是个人认为的难度

|    题号    |          题名           |   难度    |                                               简介                                                |                                                                           类路径                                                                           |
|:--------:|:---------------------:|:-------:|:-----------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------:|
|    5     |        最长回文子串         |   简单    |                                            找准状态转移逻辑                                             |                                         [leetCode.repository.No5最长回文子串](src/leetCode/repository/No5最长回文子串.java)                                         |
|    10    |        正则表达式匹配        | 困难(中等)  |                                           依旧普通的递归搜索题                                            |                                           [leetCode.repository.No10.java](src/leetCode/repository/No10.java)                                            |
|    11    |         盛最多水的容器        |   中等    |                                           需要灵机一现的双指针                                            |                                           [leetCode.repository.No11盛最多水的容器](src/leetCode/repository/No11盛最多水的容器)                                            |
|    15    |         三数之和          |   中等    |                                          两数之和进阶版(本质一样)                                          |                                      [leetCode.repository.No15_3sum.java](src/leetCode/repository/No15_3sum.java)                                       |
|    22    |         括号生成          | 中等(简单)  |                                             一般的搜索题                                              |                        [leetCode.repository.No22GenerateParentheses.java](src/leetCode/repository/No22GenerateParentheses.java)                         |
|    25    |       K 个一组翻转链表       | 困难(中等)  |                                               模拟题                                               |                       [leetCode.repository.No25ReverseNodesInKGroup.java](src/leetCode/repository/No25ReverseNodesInKGroup.java)                        |
|    32    |        最长有效括号         | 困难(中等)  |                                         贪心/栈/动态规划 多种解法                                          |                                     [leetCode.repository.No32最长有效括号.java](src/leetCode/repository/No32最长有效括号.java)                                      |
|    33    |       搜索旋转排序数组        |   中等    |                                              二分搜索                                               |                                      [leetCode.repository.No33搜索旋转排序数组](src/leetCode/repository/No33搜索旋转排序数组.java)                                      |
|    34    | 在排序数组中查找元素的第一个和最后一个位置 |   中等    |                                          二分搜索,但要找最左和最右                                          |                         [leetCode.repository.No34在排序数组中查找元素的第一个和最后一个位置](src/leetCode/repository/No34在排序数组中查找元素的第一个和最后一个位置.java)                         |
|    42    |          接雨水          |   困难    |                                            太久了,忘了内容了                                            |                          [leetCode.repository.No42TrappingRainWater.java](src/leetCode/repository/No42TrappingRainWater.java)                           |
|    45    |        跳跃游戏II         |   中等    |                                         可将图画出来,发现就是最短路                                          |                                     [leetCode.repository.No45跳跃游戏II.java](src/leetCode/repository/No45跳跃游戏II.java)                                      |
|    53    |        最大子数组和         | 简单(中等)  |                                         dp思路或者可以用数学推导求                                          |                                        [leetCode.repository.No53最大子数组和](src/leetCode/repository/No53最大子数组和.java)                                        |
|    54    |         螺旋矩阵          | 中等(简单)  |                                 就是模拟,不过可用四个变量标记已遍历过的行和列来优化空间复杂度                                 |                                          [leetCode.repository.No54螺旋矩阵](src/leetCode/repository/No54螺旋矩阵.java)                                          |
|    67    |         二进制求和         |   简单    |                                       高精度数API可解,题解也有位运算法解                                       |                                  [leetCode.repository.No67AddBinary.java](src/leetCode/repository/No67AddBinary.java)                                   |
|    84    |       柱状图中最大的矩形       |   困难    |                                           运用栈可以进一步优化.                                           |                                           [leetCode.repository.No84.java](src/leetCode/repository/No84.java)                                            |
|    88    |       合并两个有序数组        |   简单    |                                     简单题,优化小点在从后往前遍历指针来节省空间.                                     |                                   [leetCode.repository.No88合并两个有序数组.java](src/leetCode/repository/No88合并两个有序数组.java)                                    |
|   103    |      二叉树的锯齿形层序遍历      | 中等(简单)  |                                            基础的层序遍历题.                                            |                               [leetCode.repository.No103二叉树的锯齿形层序遍历.java](src/leetCode/repository/No103二叉树的锯齿形层序遍历.java)                                |
|   137    |      只出现一次的数字 II      | 中等(困难)  | 普通做法很简单,[不使用额外空间实现](https://blog.csdn.net/jiangxiewei/article/details/82227451) 就略麻烦,可能需要逻辑代数知识 |                                          [leetCode.repository.No137.java](src/leetCode/repository/No137.java)                                           |
|   146    |       LRU 缓存机制        |   中等    |                                             模拟/数据结构                                             |                                  [leetCode.repository.No146LRUCache.java](src/leetCode/repository/No146LRUCache.java)                                   |
|   160    |       No160相交链表       |   中等    |                                 我用求合法做到空间O(1),然而官方题解二的双指针也十分巧妙                                  |                                      [leetCode.repository.No160相交链表.java](src/leetCode/repository/No160相交链表.java)                                       |
|   206    |         反转链表          |   简单    |                                            基础数据结构操作                                             |                                      [leetCode.repository.No206反转链表.java](src/leetCode/repository/No206反转链表.java)                                       |
|   209    |       长度最小的子数组        |   中等    |                          滑动窗口法,可让左右区间滑动搜索实现O(n),也可用二分法针对每个左开区间寻找右闭区间.                           |                                  [leetCode.repository.No209长度最小的子数组.java](src/leetCode/repository/No209长度最小的子数组.java)                                   |
|   210    |         课程表II         |   中等    |                                        拓扑排序 + 深度/广度优先遍历                                         |                                     [leetCode.repository.No210课程表II.java](src/leetCode/repository/No210课程表II.java)                                      |
|   240    |       搜索二维矩阵II        |   中等    |                                               找规律                                               |                                  [leetCode.repository.No240搜索二维矩阵II.java](src/leetCode/repository/No240搜索二维矩阵II.java)                                   |
|   297    |     二叉树的序列化与反序列化      |   困难    |                                            树的遍历的运用.                                             |         [leetCode.repository.No297SerializeAndDeserializeBinaryTree.java](src/leetCode/repository/No297SerializeAndDeserializeBinaryTree.java)          |
|   315    |     计算右侧小于当前元素的个数     |   困难    |                                       树状数组 + 离散化,注意下数据有重复                                       |                  [leetCode.No315CountOfSmallerNumbersAfterSelf.java](src/leetCode/repository/No315CountOfSmallerNumbersAfterSelf.java)                  |
|   416    |        分割等和子集         |   中等    |                           01背包题,由于"物品"没有"价值",所以结果值可以只用true false代表可达性                           |                   [leetCode.repository.No416PartitionEqualSubsetSum.java](src/leetCode/repository/No416PartitionEqualSubsetSum.java)                    |
|   445    |        两数相加II         |   中等    |                                                栈                                                |                                       [leetCode.repository.No445两数相加II](src/leetCode/repository/No445两数相加II.java)                                       |
|   528    |        按权重随机选择        |   中等    |                                             随机算法方案?                                             |                                      [leetCode.repository.No528按权重随机选择](src/leetCode/repository/No528按权重随机选择.java)                                      |
|   543    |        二叉树的直径         |   中等    |                                 分治法,算出每个节点的左最大深度和右最大深度,比较并返回更大的                                 |                                       [leetCode.repository.No543二叉树的直径](src/leetCode/repository/No543二叉树的直径.java)                                       |
|   739    |         每日温度          |   中等    |                                            单调栈 或 贪心                                             |                         [leetCode.repository.No739DailyTemperatures.java](src/leetCode/repository/No739DailyTemperatures.java)                          |
|   856    |         括号的分数         |   中等    |                                         1.暴力 2.栈 3.数学思维                                         |                                                     [代码连接](src/leetCode/repository/No856括号的分数.java)                                                     |
|   862    |     和至少为 K 的最短子数组     |   困难    |                                    找定义,找优化点,比较过程中更新比较集来实现优化                                     |           [leetCode.repository.No862shortestSubarrayWithSumAtLeastK.java](src/leetCode/repository/No862shortestSubarrayWithSumAtLeastK.java)            |
|   912    |         排序数组          |   中等    |                                         基础排序实现,这里我用了归并.                                         |                               [leetCode.repository.No912SortAnArrya.java](src/leetCode/repository/No912SortAnArrya.java)                                |
|   945    |      使数组唯一的最小增量       | 中等(简单)  |                                            每日推荐里的水题                                             |         [leetCode.repository.No945MinimumIncrementToMakeArrayUnique.java](src/leetCode/repository/No945MinimumIncrementToMakeArrayUnique.java)          |
|   990    |       等式方程的可满足性       |   简单    |                                         模板题,可以测试自己写的模板                                          |               [leetCode.No990SatisfiabilityOfEqualityEquations.java](src/leetCode/repository/No990SatisfiabilityOfEqualityEquations.java)               |
|   1014   |        最佳观光组合         |   简单    |                                               贪心                                                |                            [leetCode.No1014BestSightseeingPair.java](src/leetCode/repository/No1014BestSightseeingPair.java)                            |
|   1143   |        最长公共子序列        |   中等    |                                             二维动态规划                                              |                                  [leetCode.repository.No1143最长公共子序列.java](src/leetCode/repository/No1143最长公共子序列.java)                                   |
|   1496   |       判断路径是否相交        |   简单    |                                               签到题                                               |                                   [leetCode.No1496PathCrossing.java](src/leetCode/repository/No1496PathCrossing.java)                                   |
|   1497   |       仅含 1 的子串数       |   中等    |                                        找性质,找到两个重要性质就很好借.                                        |               [leetCode.No1497CheckIfArrayPairsAreDivisibleByK.java](src/leetCode/repository/No1497CheckIfArrayPairsAreDivisibleByK.java)               |
|   1512   |        好数对的数目         |   简单    |                                               签到题                                               |                              [leetCode.No1512NumberOfGoodPairs.java](src/leetCode/repository/No1512NumberOfGoodPairs.java)                              |
|   1513   |       仅含 1 的子串数       |   中等    |                                             等差数列求和                                              |                   [leetCode.No1513NumberOfSubstringsWithOnly1s.java](src/leetCode/repository/No1513NumberOfSubstringsWithOnly1s.java)                   |
|   1514   |        概率最大的路径        |   中等    |                                            Dijkstra                                             |                     [leetCode.No1514PathWithMaximumProbability.java](src/leetCode/repository/No1514PathWithMaximumProbability.java)                     |
|   1515   |       服务中心的最佳位置       |   困难    |                                           证明凸函数,三分夹逼                                            |                  [leetCode.No1515BestPositionForAServiceCentre.java](src/leetCode/repository/No1515BestPositionForAServiceCentre.java)                  |
|   1518   |         换酒问题          |   简单    |                                               签到题                                               |                                   [leetCode.No1518WaterBottles.java](src/leetCode/repository/No1518WaterBottles.java)                                   |
|   1519   |      子树中标签相同的节点数      |   中等    |                                             邻接表,DFS                                             |      [leetCode.No1519NumberOfNodesInTheSubTreeWithTheSameLabel.java](src/leetCode/repository/No1519NumberOfNodesInTheSubTreeWithTheSameLabel.java)      |
|   1521   |     找到最接近目标值的函数值      |   困难    |                                        利用与运算性质以及结果集数据量限制                                        | [leetCode.No1521FindAValueOfAMysteriousFunctionClosestToTarget.java](src/leetCode/repository/No1521FindAValueOfAMysteriousFunctionClosestToTarget.java) |
|   1544   |         整理字符串         |   简单    |                                          1.模拟 2.栈 3.递归                                          |                             [leetCode.No1544MakeTheStringGreat.java](src/leetCode/repository/No1544MakeTheStringGreat.java)                             |
|   1545   | 找出第 N 个二进制字符串中的第 K 位  |   中等    |                                             溯源,可递归.                                             |                    [leetCode.No1545FindKthBitInNthBinaryString.java](src/leetCode/repository/No1545FindKthBitInNthBinaryString.java)                    |
|   1606   |     找到处理最多请求的服务器      |   困难    |                           以模拟的前提下通过busy优先队列和available红黑树减少全服务器的遍历操作.                            |                                   [leetCode.No1606找到处理最多请求的服务器.java](src/leetCode/repository/No1606找到处理最多请求的服务器.java)                                   |
| 面试题16.18 |         模式匹配          |   中等    |                                           递归,枚举,剪枝,边界                                           |                        [leetCode.Mst_16_18_PatternMatchingLcci.java](src/leetCode/repository/Mst_16_18_PatternMatchingLcci.java)                        |
| 面试题17.16 |          按摩师          |   简单    |                                           基本DP题,可当入门题                                           |                      [leetCode.repository.Mst_17_16_TheMasseuseLcci.java](src/leetCode/repository/Mst_17_16_TheMasseuseLcci.java)                       |
|  面试题51   |        数组中的逆序对        |  困难(?)  |                                           树状数组 或 归并法                                            |          [leetCode.repository.Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof.java](src/leetCode/repository/Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof.java)           |
|  LCP 33  |          蓄水           | 简单(?中等) |                                          1.暴力枚举 2.优先队列                                          |                                      [leetCode.repository.LCP_33_蓄水.java](src/leetCode/repository/LCP_33_蓄水.java)                                       |
