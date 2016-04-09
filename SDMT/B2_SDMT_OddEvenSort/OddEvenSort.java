

public class OddEvenSort {

	public static int[] sort(int[] arr)
	{
		// TODO Auto-generated method stub
		int i,j,k;
		for(k = 0 ; k < arr.length ; k++)
		{
			if(k%2==0)
			{
				for(i = 0 ; i < arr.length ; i += 2)
				{
					if(i!=arr.length-1)
					if(arr[i]>arr[i+1])
					{
						int t1;
						t1 = arr[i];
						arr[i]=arr[i+1];
						arr[i+1]=t1;	
					}
				}
			}
			else
			{
				for(j = 1 ; j < arr.length ; j += 2)
				{
					if(j!=arr.length-1)
					if(arr[j]>arr[j+1])
					{
						int t2;
						t2 = arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=t2;	
					}
				}				
			}
			
		}
		
		return arr;
		
	}

}
