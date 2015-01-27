#!/usr/bin/python

import sys

__author__ = 'briandesnoyers'

# ################################################################

class PriorityQueue:
    def __init__(self):
        self.__inner_list = [Employee(0, None, 0)]
        """:type : list of [Node]"""
        self.__size = 0
        """:type : int"""

    def get_size(self):
        """
        Accessor for the size of this priority queue
        :return: the size of this priority queue
        :rtype: int
        """
        return self.__size

    def heapify_up(self, i):
        """
        Performs the heapify up operation on this Priority Queue
        :param i: the index within the inner list to heapify up from
        :type i: int
        """
        while i // 2 > 0:
            if self.__inner_list[i].total_value > self.__inner_list[i // 2].total_value:
                old = self.__inner_list[i]
                self.__inner_list[i] = self.__inner_list[i // 2]
                self.__inner_list[i].heap_position = i
                self.__inner_list[i // 2] = old
                self.__inner_list[i // 2].heap_position = i // 2
            i //= 2

    def insert(self, node):
        """
        Inserts the Employee node into this priority queue
        :param node: the Employee to insert
        :type node: Employee
        """
        self.__inner_list.append(node)
        self.__size = self.get_size() + 1
        node.heap_position = self.get_size()
        self.heapify_up(self.get_size())

    def heapify_down(self, i):
        """
        Performs the heapify down operation on this Priority Queue
        :param i: the index within the inner list to heapify down from
        :type i: int
        """
        while (2 * i) <= self.get_size():
            max_child = self.max_child(i)
            if self.__inner_list[i].total_value < self.__inner_list[max_child].total_value:
                old = self.__inner_list[i]
                self.__inner_list[i] = self.__inner_list[max_child]
                self.__inner_list[i].heap_position = i
                self.__inner_list[max_child] = old
                self.__inner_list[max_child].heap_position = max_child
            i = max_child

    def max_child(self, i):
        """
        Returns the index of the max child in the heap
        :param i: the index to start from to find the max child
        :type i: int
        :return: the index of the max child in the heap
        :rtype: int
        """
        if i * 2 + 1 > self.get_size() or \
                        self.__inner_list[i * 2].total_value > self.__inner_list[i * 2 + 1].total_value:
            return 2 * i
        else:
            return 2 * i + 1

    def pop_max(self):
        """
        Pops the maximum valued employee from the priority queue
        EFFECT: removes the maximum valued employee
        :return: the maximum valued employee from the priority queue
        :rtype: Employee
        """
        max = self.__inner_list[1]
        self.__inner_list[1] = self.__inner_list[self.get_size()]
        self.__size -= 1
        self.__inner_list.pop()
        self.heapify_down(1)
        return max

# ################################################################

class Employee:
    def __init__(self, id, boss, own_value):
        self.id = id
        """:type: int"""
        self.own_value = own_value
        """:type: int"""
        self.boss = boss
        """:type: Employee"""
        if self.boss is None:
            self.total_value = 0
            """:type: int"""
        else:
            self.total_value = own_value + boss.total_value
            """:type: int"""
        self.is_leaf = True
        """:type: bool"""
        self.in_solution = False
        """:type: bool"""
        self.leaves_under = []
        """:type: list of [Employee]"""
        self.heap_position = -1
        """:type: int"""
        self.to_subtract = 0
        """:type: int"""

    def add_leaf(self, leaf):
        """
        Adds a leaf to this Employee's list of leaves under it and to the lists of any bosses of this Employee
        :param leaf: the leaf under this employee
        :type leaf: Employee
        """
        self.leaves_under.append(leaf)
        if not self.boss is None:
            self.boss.add_leaf(leaf)

    def fix_parents(self):
        """
        Handles adding this leaf to the solution and fixing the parents after the addition
        """
        self.in_solution = True
        if not (self.boss is None or self.boss.in_solution):
            for employee in self.leaves_under:
                employee.to_subtract += self.own_value
            self.boss.fix_parents()
        else:
            for employee in self.leaves_under:
                employee.to_subtract += self.own_value
                employee.total_value -= employee.to_subtract
                employee.to_subtract = 0
                leaves.heapify_down(employee.heap_position)


# ################################################################

# For Final Product: Get input from stdin

n_and_k = (map(int, sys.stdin.readline().split(" ")))
n = n_and_k[0]
k = n_and_k[1]

employees = [Employee(0, None, 0)]
for i in range(1, n + 1):
    emp = (map(int, sys.stdin.readline()[:-1].split(" ")))
    employees.append(Employee(emp[0], employees[emp[1]], emp[2]))
    employees[emp[1]].is_leaf = False

leaves = PriorityQueue()

# ################################################################

# For Testing: Get input from sample input

# n = 18
# k = 3
# bosses = [0, 0, 1, 2, 2, 1, 5, 5, 7, 8, 8, 7, 7, 12, 12, 1, 15, 15, 15]
# values = [0, 0, 97, 77, 51, 49, 33, 8, 91, 53, 52, 78, 8, 37, 49, 25, 96, 75, 53]

# n = 5
# k = 2
# bosses = [0, 0, 1, 1, 3, 3]
# values = [0, 10, 10, 50, 5, 15]

# n = 24
# k = 5
# bosses = [0, 0, 1, 2, 3, 3, 3, 6, 6, 6, 2, 2, 1, 12, 13, 14, 14, 13, 17, 17, 17, 12, 21, 21, 1]
# values = [0, 0, 41329, 89523, 70574, 12757, 66296, 59289, 66473, 24881, 41202, 92894, 29814, 49296, 9775, 66389, 50845,
# 65602, 43847, 22809, 70603, 62784, 31285, 49596, 59687]

# ###############################################################

# For Testing: Get input from example files in this directory

# f = open("18.in", "r")
# n_and_k = (map(int, f.readline().split(" ")))
# n = n_and_k[0]
# k = n_and_k[1]
#
# employees = [Employee(0, None, 0)]
# for i in range(1, n + 1):
#     emp = (map(int, f.readline().split(" ")))
#     employees.append(Employee(emp[0], employees[emp[1]], emp[2]))
#     employees[emp[1]].is_leaf = False
#
# leaves = PriorityQueue()
#
# f.close()

# ################################################################

def algorithm():
    value_of_solution = 0

    for employee in employees:
        if employee.is_leaf:
            leaves.insert(employee)
            employee.boss.add_leaf(employee)

    i = k
    while (i > 0):  # and leaves.get_size() > 0
        optimal_emp = leaves.pop_max()
        value_of_solution += optimal_emp.total_value
        if i == 1:
            break
        optimal_emp.in_solution = True
        optimal_emp.fix_parents()
        i -= 1

    return value_of_solution

if __name__ == "__main__":
    print(algorithm())

################################################################
