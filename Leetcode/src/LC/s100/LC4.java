package LC.s100;

public class LC4 {
    public double findMedianSortedArrays(int[] arr1, int[] arr2){//返回值是double不是int
        //consider if one array is 0
        int m = arr1.length;
        int n = arr2.length;
        if((m+n)%2 != 0) return findKth(arr1,arr2,0,m-1,0,n-1,(m+n+1)/2);
        return (findKth(arr1,arr2,0,m-1,0,n-1,(m+n)/2)+
                findKth(arr1,arr2,0,m-1,0,n-1,(m+n+2)/2))/2.0;


    }
    private int findKth(int[] arr1,int[] arr2,int s1,int e1,int s2,int e2,int k){
        int element1 = e1 - s1 + 1;
        int element2 = e2 - s2 + 1;
        if(element1 > element2) return findKth(arr2,arr1,s2,e2,s1,e1,k);//first array small
        if(element1 == 0) return arr2[s2+k-1];//这里忘了加s2
        if(k == 1) return Math.min(arr1[s1],arr2[s2]);
        int use1 = Math.min(element1,k/2);//A中取的元素
        int use2 = Math.min(element2,k/2);
        int index1 = s1+ use1 -1, index2 = s2 + use2 -1;
        if(arr1[index1]  < arr2[index2]){//drop arr1
            return findKth(arr1,arr2,index1+1,e1,s2,e2,k-use1);
        }
        else{
            return findKth(arr1,arr2,s1,e1,index2+1,e2,k-use2);
        }
    }
}
