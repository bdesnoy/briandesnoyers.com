Employee Influence Algorithm
============================

Project Info
------------
This is a program that finds the optimum employees to influence in a
company to spread propaganda within a company, as described in the problem
description. The program is written in Python and was written as part of
coursework for the Algorithms and Data Structures course at Northeastern
University. A greedy algorithm was used to find the solution, as described
in the Readme.pdf file. The list of employees was maintained in a priority
queue implementation to ensure that it could process a list of ~800,000
employees in under one minute.

Problem Description
-------------------

You are given the task of spreading propaganda within a company. You only have the time to talk to a certain amount of people, but you are guaranteed that if you influence somebody, they will influence their boss, that person will influence their boss, and so on, all the way up to the CEO. You have mapped out the entire company hierarchy, and assigned a utility value to each person, indicating how valuable it would be to influence them. Given this setup and a limit on the number of people you can talk to, you want to compute the optimal set of people to influence. An optimal set of people will maximize the total utility of all persons influenced, directly or indirectly, by your selection.

There will be exactly one person, the CEO, with no boss in the hierarchy. All other people will eventually answer to this person on the command chain, but not necessarily directly.

You are guaranteed that each person will have at most one boss, but that boss may have another in turn. For example, person A may have a boss B, whose boss is C, whose boss is D, whose boss is the CEO. Thus influencing person A will automatically influence B, C, D, and the CEO.

Different employes may have bosses in common in the command chain. You DO NOT obtain additional value for influencing a person more than once. For example, if A and B both answer directly to the CEO, and you influence both, you will receive a value of val(A)+val(B)+val(CEO), not val(A)+val(B)+2val(CEO).

The employees will be given to you as numbered positive integers. Each employee will come with its direct boss and the value of influencing them. The first employee will have id 1, and boss id 0, indicating that it is the CEO.

Given a positive integer k, your algorithm must select k employees to influence.

Input Format:
Line 1: n k
n is the number of employees to follow, k is the number of employees you are expected to influence
Line 2 through n+1: i j k
i is the id of the employee
j is the boss id of the employee
k is the value of influencing the employee (always non-negative)

Output Format:
Line 1: VALUE
Where VALUE is the max possible total utility from a selection of k employees.

```
Sample Input:
5 2
1 0 10
2 1 10
3 1 50
4 3 5
5 3 15

Sample Output:
85
```
