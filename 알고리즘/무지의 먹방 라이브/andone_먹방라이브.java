import java.io.*;
import java.util.*;

class FoodPlate implements Comparable<FoodPlate>{
    int index;
    int time;

    FoodPlate(int index, int time){
        this.index=index;
        this.time=time;
    }

    public int getTime() {
        return time;
    }
    public int getIndex(){
        return index;
    }

    @Override
    public int compareTo(FoodPlate foodPlate) {
        int time = foodPlate.getTime();
        if(this.time>time) return 1;
        else if(this.time==time) return 0;
        else return -1;
    }
}

class IndexComparator implements Comparator<FoodPlate>{
    @Override
    public int compare(FoodPlate f1, FoodPlate f2){
        return f1.getIndex() - f2.getIndex();
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        List<FoodPlate> foodList = new ArrayList<FoodPlate>();

        long totalFoodTime=0;

        int n = food_times.length;

        for(int i=0;i<n;i++){
            totalFoodTime+=food_times[i];
        }

        if(totalFoodTime<=k){
            return -1;
        } //남은 음식 없음

        for(int i=0;i<n;i++){
            foodList.add(new FoodPlate(i+1,food_times[i]));
        }


        foodList.sort(Comparator.naturalOrder());



        long litleTime=0;
        long finish=0;
        long spend;
        int i=0;

        for(FoodPlate f : foodList){
            long diff = f.time - litleTime;
            if(diff!=0){
                spend = diff * n;
                if(k>=spend){
                    k-=spend;
                    litleTime=f.time;
                }else{
                    k %= n;
                    Collections.sort(foodList.subList(i,food_times.length),new IndexComparator());
                    return foodList.get(i+(int)k).index;
                }
            }
                n--;
                i++;


        }

        return -1;
    }
}