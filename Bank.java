import java.io.*;
import java.util.*;

public class Bank
{
	int need[][];
	int allo[][];
	int max[][];
	int ava[][];
	int p,r;
	Scanner sc=new Scanner(System.in);
     
	public boolean check(int i)
	{
        	for(int j=0;j<r;j++) 
		{
			if(ava[0][j]<need[i][j])
			{
        			return false;
    			}
		}
    		return true;
    	}
 	
	public void safe()
	{
        	System.out.println("Enter number of processes ");
	     	p=sc.nextInt();
	     	System.out.println("Enter number of resources  ");
	     	r=sc.nextInt(); 
	     	need=new int[p][r];
	     	max=new int[p][r];
	     	allo=new int[p][r];
	     	ava=new int[1][r];
	      
	     	System.out.println("Enter allocation matrix");
	     	for(int i=0;i<p;i++)
		{
	          	for(int j=0;j<r;j++)
			{
		         	allo[i][j]=sc.nextInt(); 
			}
		}
		       
	     	System.out.println("Enter max matrix");
	     	for(int i=0;i<p;i++)
		{
	        	for(int j=0;j<r;j++)
			{
		         	max[i][j]=sc.nextInt();
			}
		}
	       
		System.out.println("Enter available matrix");
        	for(int j=0;j<r;j++)
		{
         		ava[0][j]=sc.nextInt();
        	} 
		
       		for(int i=0;i<p;i++)
		{
        		for(int j=0;j<r;j++)
			{
          			need[i][j]=max[i][j]-allo[i][j];
			}
		}
       

        	boolean done[]=new boolean[p];
       		int j=0;
 		System.out.println();
       		while(j<p)
      		{  
		       	boolean allocated=false;
                	for(int i=0;i<p;i++)
			{
				if(!done[i] && check(i))
				{ 
	            			for(int k=0;k<r;k++)
					{
        	    				ava[0][k]=ava[0][k]-need[i][k]+max[i][k];
					}
         				System.out.print(i+"  ");
         				allocated=done[i]=true;
               				j++;
	             		}
			}	
          		if(!allocated)
				break;
       		}

       		if(j==p)
		{ 
        		System.out.println("\nAll processes are safely allocated");
		}
       		else
		{
        		System.out.println("proceess can't be allocated safely");
		}
   	}
     
	public static void main(String[] args) 
    	{
       		Bank b=new Bank();
		b.safe();
    	}
}
