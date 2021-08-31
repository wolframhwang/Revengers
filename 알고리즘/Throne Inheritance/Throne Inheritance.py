"""

안 해 본 유형의 문제였다

이걸 dfs로 푸는 건 아직 엄청 오래 걸리네

"""

class ThroneInheritance(object):
    def __init__(self, kingName: str):
        self.family_tree = collections.defaultdict(list)
        self.king = kingName
        self.dead = set()

    def birth(self, parentName: str, childName: str) -> None:
        self.family_tree[parentName].append(childName)

    def death(self, name: str) -> None:
        self.dead.add(name)

    def getInheritanceOrder(self) -> List[str]:

        def dfs(name: str) -> None:
            if name not in self.dead:
                inheritance_order.append(name)
            for kid in self.family_tree[name]:
                dfs(kid)

        inheritance_order = []
        dfs(self.king)
        return inheritance_order
