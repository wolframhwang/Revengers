class Solution {
public:
    int findMin(vector<int>& nums) {
        if(nums.size()==1){
            return nums[0];
        }//한개일 때는 그냥 나가자
        if(nums.size()==2){
            return min(nums[0],nums[1]);
        }//두개일 때는 더 작은거
        int begin = 0;
        int end = nums.size()-1;
        while(nums[begin] > nums[end]){//왼쪽이 오른쪽보다 무조건 작아야함
            int mid = (begin+end)/2;
            if(nums[mid] > nums[end]){// 중간이 마지막보다 크면 정배열이 아니라는 것이니까 시작지점을 mid + 1로 옮겨줌
                begin = mid + 1;
            }
            else{//중간이 마지막보다 작으면 그 뒤는 정배열이라는 것이니까 마지막을 mid 로 바꿔줌
                end = mid;
            }
        }
        return nums[begin];
    }
};
