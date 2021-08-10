"""

"""
import sys

sys.setrecursionlimit(10 ** 6)


class Tree:
    def __init__(self, data_list, org_data_list):
        self.data = max(data_list, key=lambda x: x[1])

        self.idx = org_data_list.index(self.data) + 1

        left = list(filter(lambda x: x[0] < self.data[0], data_list))
        right = list(filter(lambda x: x[0] > self.data[0], data_list))

        if left != []:
            self.left = Tree(left, org_data_list)
        else:
            self.left = None

        if right != []:
            self.right = Tree(right, org_data_list)
        else:
            self.right = None


def go(tree, pre, post):
    pre.append(tree.idx)

    if tree.left != None:
        go(tree.left, pre, post)

    if tree.right != None:
        go(tree.right, pre, post)

    post.append(tree.idx)


def solution(nodeinfo):
    answer = [[]]
    tree = Tree(nodeinfo, nodeinfo)

    pre = []
    post = []

    go(tree, pre, post)

    return [pre, post]