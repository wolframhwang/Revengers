class Solution {
public:
    int trap(vector<int>& height) {
        vector<int> dp1;
        vector<int> dp2;
        int answer=0;
        dp1.push_back(height[0]);
        dp2.push_back(height[height.size()-1]);
        for(int i=1;i<height.size();i++){
            if(dp1[i-1]>height[i]){
                dp1.push_back(dp1[i-1]);
            }
            else{
                dp1.push_back(height[i]);
            }
            if(dp2[i-1]>height[height.size()-1-i]){
                dp2.push_back(dp2[i-1]);
            }
            else{
                dp2.push_back(height[height.size()-1-i]);
            }
        }
        
        for(int i=0;i<dp1.size();i++){
            if(dp1[i]>dp2[dp2.size()-1-i]){
                dp1[i]=dp2[dp2.size()-1-i];
            }
            answer = answer+dp1[i]-height[i];
        }
        return answer;
    }
};
