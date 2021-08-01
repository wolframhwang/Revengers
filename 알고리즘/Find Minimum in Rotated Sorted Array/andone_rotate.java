//이분탐색으로 풀었다.
//정렬이 어느정도는 되어있다는 가정이 있으며
//가장 왼쪽을 후보 최소값이라 가정하고 배열의 중간 값과 비교했을때 후보 최소값이 배열의 중간 인덱스 값 보다 크다면 중간 인덱스 값 보다 낮은 인덱스에 최소값이 존재하고
//중간값보다 작다면 그 후보 값이 최소이거나 중간 인덱스 값 뒤부분에 최소값이 존재한다. 배열의 범위를 줄여가며 이전보다 최소였는지를 확인한다.
class Solution {    
    static int answer;
    
    public int findMin(int[] nums) {
        int candidate = nums[0];
        answer = Integer.MAX_VALUE;
        
        search(nums, candidate, 0, nums.length);
        
        return answer;
    }
    
    public void search(int[] nums, int candidate, int low, int high) {
        answer = Math.min(candidate, answer);
        
        if(low>=high) return;
        
        int mid = (low + high) / 2;
        if(nums[mid]>candidate) {
            search(nums, nums[low], mid, high);
        } else {
            search(nums, nums[mid], low, mid);
        }
    }
}
