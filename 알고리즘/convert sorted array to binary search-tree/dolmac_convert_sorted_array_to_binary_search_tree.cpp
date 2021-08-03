/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
#include <iostream>
class Solution {
public:
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        TreeNode* answer = new TreeNode(NULL);
        if(nums.size() == 0){
            return 0;
        }
        else{
            return_val(answer, nums, 0, nums.size()-1);
        }
    
        return answer;
    }
    void return_val(TreeNode* answer, vector<int>& nums, int _begin, int _end){
        int mid = (_begin + _end) / 2;
        answer->val = nums[mid];
        if(_begin <= mid -1 ){
            return_val(answer->left = new TreeNode(NULL), nums, _begin, mid-1); 
        }
        if(mid + 1 <= _end){
            return_val(answer->right = new TreeNode(NULL), nums, mid + 1, _end);
        }
    }
};
