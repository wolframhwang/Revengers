// 21.08.29 Solved By Wolfram Hwang
// 간단하지 않은 해시 문제입니다.
// Node 포인터를 사용해서 원하는 영역으로 갈수있게끔 조절을해줍니다.
// 아마도 getInheritanceOrder일때 전체 N중, 2N을 탐색하게되는데요
// 20만정도고, 10번정도 돈다하여, 200만에 터질일은 없겠다고 확신을 했습니다.
#include<iostream>
#include<string>
#include<vector>
#include<unordered_map>
using namespace std;
struct Node {
    string name;
    int n = 0;
    bool death;
    Node *prev, *next;
    Node *child, *parent;

}buf[100010];

class ThroneInheritance {
public:
    int idx = 0;
    unordered_map<string, Node*> map;
    string king = "";
    ThroneInheritance(string kingName) {
        buf[idx] = {kingName, 0, 0,0, 0, 0, 0};
        map[kingName] = &buf[idx++];
        king = kingName;
    }

    void birth(string parentName, string childName) {
        Node *p = map[parentName];

        buf[idx] = {childName, 0,0, 0,0, map[parentName]};
        map[childName] = &buf[idx];
        if(p->n != 0){
            p->child->prev = &buf[idx];
            buf[idx].next = p->child;
        }
        p->child = &buf[idx];
        p->n = 1;
        idx+= 1;
    }

    void death(string name) {
        Node *p = map[name];
        p->death = true;
    }

    void getID(Node *name, vector<string> &ans) {
        if(!name){ return ;}
        Node *sibling = name;
        for(; sibling->next; sibling = sibling->next){
        }
        for(; sibling; sibling = sibling->prev) {
            if(!sibling->death)
                ans.push_back(sibling->name);
            if(sibling->n)
                getID(sibling->child, ans);
        }
    }

    vector<string> getInheritanceOrder() {
        vector<string> ans;
        if(!map[king]->death)
            ans.push_back(king);
        if(map[king]->n)
            getID(map[king]->child, ans);
        return ans;
    }
};

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance* obj = new ThroneInheritance(kingName);
 * obj->birth(parentName,childName);
 * obj->death(name);
 * vector<string> param_3 = obj->getInheritanceOrder();
 */
