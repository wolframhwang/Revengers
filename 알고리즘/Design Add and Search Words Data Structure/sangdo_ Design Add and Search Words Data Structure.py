'''
트라이를 이용한다!

'.'이 나오면 해당 자리에 모든 child를 모두 넣어 보면서 다음 노드에서 조건이 되는지 확인해서 어떤 값이라도 dfs로 통과 된다면 True로 return 하면 된다.
최악의 경우는 500*50000 정도 될 것 같다.
'''
class Trie:
    def __init__(self):
        self.child = [None]*26
        self.isEnd = False
    def search(self, word) -> bool:
        if(len(word) == 0): return self.isEnd
        conv = ord(word[0]) - ord('a')
        next = word[1:]
        if(word[0] == '.'):
            for c in range(0,26):
                if self.child[c] and self.child[c].search(next): return True
        else: 
            if(self.child[conv]): 
                return self.child[conv].search(next)
        
        return False
        
        
class WordDictionary:
    def __init__(self):
        self.root = Trie()

    def addWord(self, word: str) -> None:
        now = self.root
        for c in word:
            conv = ord(c) - ord('a')
            if now.child[conv] == None:
                now.child[conv] = Trie()
            now = now.child[conv]
            
        now.isEnd = True
        
    def search(self, word: str) -> bool:
        return self.root.search(word)
