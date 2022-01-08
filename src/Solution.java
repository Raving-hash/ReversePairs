class Solution {
    public int reversePairs(int[] nums) {
        int length = nums.length;
        if (length < 2){
            return  0;
        }

        int[] copy = new int[length];
        for (int i = 0; i < length; i++) {
            copy[i] = nums[i];
        }
        int[] temp = new int[length];
        return reversePairs(copy,0,length-1,temp);
    }

    /**
     * 计算处从l到r的nums数据的逆序对个数
     * @param nums
     * @param l
     * @param r
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int l, int r, int[] temp){
        if (l == r){
            return 0;
        }
        int mid = l + (r - l)/2;
        int leftPairs = reversePairs(nums,l,mid,temp);
        int rightPairs = reversePairs(nums,mid+1,r,temp);
        int crossPairs = mergeAndCount(nums,l,r,temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private int mergeAndCount(int[] nums, int l, int r, int[] temp){
        for (int i = l; i <= r; i++){
            temp[i] = nums[i];
        }
        int i = l;
        int mid = l + (r-l)/2;
        int j = mid + 1;
        int count = 0;
        for (int k = l ; k <= r ; k++){
            if (i == mid + 1){
                nums[k] = temp[j];
                j++;
            }else if(j == r+1){
                nums[k] = temp[i];
                i++;
            }else if(temp[i] <= temp[j]){
                nums[k] = temp[i];
                i++;
            }else if(temp[i] > temp[j]){
                nums[k] = temp[j];
                j++;
                count += mid - i + 1;
            }
        }
        return count;
    }
}