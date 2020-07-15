# acm
毕业后吃饱了撑没事干写写题,缅怀以前打ACM的日子.

## 题目分类

### 单调栈
| 题号 |    题名    | 难度 | 简介 |                类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| leetcode 739 | 每日温度 | 中等 | 单调栈 或 贪心 |[leetCode.repository.No739DailyTemperatures.java](acm/src/leetCode/repository/No739DailyTemperatures.java)|

### 搜索
| 题号 |    题名    | 难度 | 简介 |                类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| leetcode 10 | 正则表达式匹配 | 困难(中等吧) | 依旧普通的递归搜索题 |[leetCode.repository.No10.java](acm/src/leetCode/repository/No10.java)|
| leetcode 22  | 括号生成 | 简单 | ez | [leetCode.repository.No22GenerateParentheses](acm/src/leetCode/repository/No22GenerateParentheses.java) |
| leetcode 297| 二叉树的序列化与反序列化 | 困难(中等吧) | 树遍历也是搜索 | [leetCode.repository.No297SerializeAndDeserializeBinaryTree](acm/src/leetCode/repository/No297SerializeAndDeserializeBinaryTree.java) |

### 动态规划
| 题号 |    题名    | 难度 | 简介 |               类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| leetcode 53  | 最大子序和 | 简单 | 还好 | [leetCode.repository.No53MaximumSubarray](acm/src/leetCode/repository/No53MaximumSubarray.java) |
| 面试题17.16 | 按摩师 | 简单 | 尝试从DFS->尾递归->DP的一个推断流程,注释里有 | [leetCode.repository.Mst_17_16_TheMasseuseLcci.java](acm/src/leetCode/repository/Mst_17_16_TheMasseuseLcci.java) |
| 面试题46 | 把数字翻译成字符串 | 简单 | 也可以用搜索 | [leetCode.repository.Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof.java](acm/src/leetCode/repository/Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof.java) |

### 网络流
| 题号 |    题名    | 难度 | 简介 |                类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| hdu 3549 | Flow Problem | 简单 | 完全模板题,可以测试自己写的网络流模板,但是输入有坑 | [hdu.No3549FlowProblem.java](acm/src/hdu/No3549FlowProblem.java)|

### 并查集
| 题号 |    题名    | 难度 | 简介 |                类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| leetcode 990 | satisfiability-of-equality-equations | 简单 | 模板题,可以测试自己写的模板 | [leetCode.No990SatisfiabilityOfEqualityEquations.java](acm/src/leetCode/repository/No990SatisfiabilityOfEqualityEquations.java)|

### 树状数组
| 题号 |    题名    | 难度 | 简介 |                类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| leetcode 315 | 计算右侧小于当前元素的个数 | 困难 | 树状数组 + 离散化,注意下数据有重复 | [leetCode.No315CountOfSmallerNumbersAfterSelf.java](acm/src/leetCode/repository/No315CountOfSmallerNumbersAfterSelf.java)|

### 图论

#### Dijkstra
| 题号 |    题名    | 难度 | 简介 |                类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| 5211 | 概率最大的路径 | 中等 | Dijkstra | [leetCode.No5211PathWithMaximumProbability.java](acm/src/leetCode/repository/No5211PathWithMaximumProbability.java)|

### LeetCode刷题
| 题号 |    题名    | 难度 | 简介 |                 类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| 10 | 正则表达式匹配 | 困难(中等) | 依旧普通的递归搜索题 |[leetCode.repository.No10.java](acm/src/leetCode/repository/No10.java)|
| 15 | 三数之和 | 中等 | 两数之和进阶版(本质一样) |[leetCode.repository.No15_3sum.java](acm/src/leetCode/repository/No15_3sum.java)|
| 22 | 括号生成 | 中等(简单) | 一般的搜索题 |[leetCode.repository.No22GenerateParentheses.java](acm/src/leetCode/repository/No22GenerateParentheses.java)|
| 42 | 接雨水 | 困难 | 太久了,忘了内容了 |[leetCode.repository.No42TrappingRainWater.java](acm/src/leetCode/repository/No42TrappingRainWater.java)|
| 53 | 只出现一次的数字 II | 中等(困难) | 普通做法很简单,[不使用额外空间实现](https://blog.csdn.net/jiangxiewei/article/details/82227451) 就略麻烦,可能需要逻辑代数知识 | [leetCode.repository.No137.java](acm/src/leetCode/repository/No137.java)|
| 67 | 二进制求和 | 简单 | 高精度数API可解,题解也有位运算法解 | [leetCode.repository.No67AddBinary.java](acm/src/leetCode/repository/No67AddBinary.java)|
| 84 | 柱状图中最大的矩形 | 困难 | 运用栈可以进一步优化. |[leetCode.repository.No84.java](acm/src/leetCode/repository/No84.java)|
| 297 | 二叉树的序列化与反序列化 | 困难 | 树的遍历的运用. |[leetCode.repository.No297SerializeAndDeserializeBinaryTree.java](acm/src/leetCode/repository/No297SerializeAndDeserializeBinaryTree.java)|
| 315 | 计算右侧小于当前元素的个数 | 困难 | 树状数组 + 离散化,注意下数据有重复 | [leetCode.No315CountOfSmallerNumbersAfterSelf.java](acm/src/leetCode/repository/No315CountOfSmallerNumbersAfterSelf.java)|
| 739 | 每日温度 | 中等 | 单调栈 或 贪心 |[leetCode.repository.No739DailyTemperatures.java](acm/src/leetCode/repository/No739DailyTemperatures.java)|
| 862 | 和至少为 K 的最短子数组 | 困难 | 找定义,找优化点,比较过程中更新比较集来实现优化 |[leetCode.repository.No862shortestSubarrayWithSumAtLeastK.java](acm/src/leetCode/repository/No862shortestSubarrayWithSumAtLeastK.java)|
| 945 | 使数组唯一的最小增量 | 中等(简单) | 每日推荐里的水题 | [leetCode.repository.No945MinimumIncrementToMakeArrayUnique.java](acm/src/leetCode/repository/No945MinimumIncrementToMakeArrayUnique.java) |
| 990 | 等式方程的可满足性 | 简单 | 模板题,可以测试自己写的模板 | [leetCode.No990SatisfiabilityOfEqualityEquations.java](acm/src/leetCode/repository/No990SatisfiabilityOfEqualityEquations.java)|
| 1014 | 最佳观光组合 | 简单 | 贪心 | [leetCode.No1014BestSightseeingPair.java](acm/src/leetCode/repository/No1014BestSightseeingPair.java)|
| 1512 | 好数对的数目 | 简单 | 签到题 | [leetCode.No1512NumberOfGoodPairs.java](acm/src/leetCode/repository/No1512NumberOfGoodPairs.java)|
| 1513 | 仅含 1 的子串数 | 中等 | 等差数列求和 | [leetCode.No1513NumberOfSubstringsWithOnly1s.java](acm/src/leetCode/repository/No1513NumberOfSubstringsWithOnly1s.java)|
| 1514 | 概率最大的路径 | 中等 | Dijkstra | [leetCode.No1514PathWithMaximumProbability.java](acm/src/leetCode/repository/No1514PathWithMaximumProbability.java)|
| 1515 | 服务中心的最佳位置 | 困难 | 证明凸函数,三分夹逼 | [leetCode.No1515BestPositionForAServiceCentre.java](acm/src/leetCode/repository/No1515BestPositionForAServiceCentre.java) |
| 面试题16.18 | 模式匹配 | 中等 | 递归,枚举,剪枝,边界 | [leetCode.Mst_16_18_PatternMatchingLcci.java](acm/src/leetCode/repository/Mst_16_18_PatternMatchingLcci.java)|
| 面试题17.16 | 按摩师 | 简单 | 基本DP题,可当入门题 | [leetCode.repository.Mst_17_16_TheMasseuseLcci.java](acm/src/leetCode/repository/Mst_17_16_TheMasseuseLcci.java) |
| 面试题51 | 数组中的逆序对 | 困难(?) | 树状数组 或 归并法 | [leetCode.repository.Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof.java](acm/src/leetCode/repository/Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof.java) |

## leetcode周赛
打的不多,不过还是挺有意思的.
### 第197场周赛
| 题号 |    题名    | 难度 | 简介 |                 类路径                  |
| :---: | :--------: | :---: | :---: | :-------------------------------------: |
| 1512 | 好数对的数目 | 简单 | 签到题 | [leetCode.No1512NumberOfGoodPairs.java](acm/src/leetCode/repository/No1512NumberOfGoodPairs.java)|
| 1513 | 仅含 1 的子串数 | 中等 | 等差数列求和 | [leetCode.No1513NumberOfSubstringsWithOnly1s.java](acm/src/leetCode/repository/No1513NumberOfSubstringsWithOnly1s.java)|
| 1514 | 概率最大的路径 | 中等 | Dijkstra | [leetCode.No1514PathWithMaximumProbability.java](acm/src/leetCode/repository/No1514PathWithMaximumProbability.java)|
| 1515 | 服务中心的最佳位置 | 困难 | 证明凸函数,三分夹逼 | [leetCode.No1515BestPositionForAServiceCentre.java](acm/src/leetCode/repository/No1515BestPositionForAServiceCentre.java) |
