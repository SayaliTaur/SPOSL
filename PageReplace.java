import java.io.*;
      
public class PageReplace
{
	static void lruinput(int f, int n, int pages[], int frame[])throws IOException
    {
		BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
		int page=0,ch,pgf=0,chn=0;
		boolean flag;
		int pt=0;
    	int pg=0;
    	int hit=0;
    	for(pg=0;pg<n;pg++)
    	{
    		page=pages[pg];				
    	    flag=true;
    	    for(int j=0;j<f;j++)
    	    {
    	    	if(page==frame[j])
    	     	{
    	    		flag=false;
    	    		hit++;
    	      		break;
    	     	}
    	    }
    		int temp,h=3,i;
    	    if(flag)
    	    {
    	    	if( frame[1]!=-1 && frame[2]!=-1 && frame[0]!=-1)
    	        {
    	    		temp=pages[pg-3];
    				if(temp==pages[pg-2] || temp==pages[pg-1]) 
    					temp=pages[pg-4];    
    						     
    				for(i=0;i<f;i++)
    					if(temp==frame[i])
    						break;
    				frame[i]=pages[pg];
    			}
    			else
    			{
    				if(frame[0]==-1)
    					frame[0]=pages[pg];
    				else if(frame[1]==-1)
    					frame[1]=pages[pg];
    				else if(frame[2]==-1)
    					frame[2]=pages[pg];
    			}  	   
    	       
    	      	for(int j=0;j<f;j++)
    	      		System.out.print(frame[j]+"\t");
    			System.out.println();
    			pgf++;
    	    }
    	    else
    		{
    			for(int j=0;j<f;j++)
    				System.out.print(frame[j]+"\t");
    			System.out.println();
    		}
    	    	
    	}//for
    	//System.out.println("Page fault:"+pgf);
    	System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/n));
        System.out.println("The number of Faults: " + pgf);

    }
	
	static void opt(int f, int n, int pages[], int frame[]) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames, pointer = 0, hit = 0, fault = 0,ref_len;
        boolean isFull = false;
        int buffer[];
        int reference[];
        int mem_layout[][];
        
      //  System.out.println("Please enter the number of Frames: ");
        frames = f;
        
      //  System.out.println("Please enter the length of the Reference string: ");
        ref_len = n;
        
      //  reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for(int j = 0; j < frames; j++)
                buffer[j] = -1;
        
      //  System.out.println("Please enter the reference string: ");
       // for(int i = 0; i < ref_len; i++)
      //  {
     //       reference[i] = Integer.parseInt(br.readLine());
     //   }
        System.out.println();
        for(int i = 0; i < ref_len; i++)
        {
         int search = -1;
         for(int j = 0; j < frames; j++)
         {
          if(buffer[j] == pages[i])
          {
           search = j;
           hit++;
           break;
          } 
         }
         if(search == -1)
         {
          if(isFull)
          {
           int index[] = new int[frames];
           boolean index_flag[] = new boolean[frames];
           for(int j = i + 1; j < ref_len; j++)
           {
            for(int k = 0; k < frames; k++)
            {
             if((pages[j] == buffer[k]) && (index_flag[k] == false))
             {
              index[k] = j;
              index_flag[k] = true;
              break;
             }
            }
           }
           int max = index[0];
           pointer = 0;
           if(max == 0)
            max = 200;
           for(int j = 0; j < frames; j++)
           {
            if(index[j] == 0)
             index[j] = 200;
            if(index[j] > max)
            {
             max = index[j];
             pointer = j;
            }
           }
          }
          buffer[pointer] = pages[i];
          fault++;
          if(!isFull)
          {
           pointer++;
              if(pointer == frames)
              {
               pointer = 0;
               isFull = true;
              }
          }
         }
            for(int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        
        for(int i = 0; i < ref_len; i++)
        {
            for(int j = 0; j < frames; j++)
                System.out.print(mem_layout[i][j]+"\t");
            System.out.println();
        }
        
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    }
    
	
	
	/*static void fifo(int f, int n, int pages[], int frame[]) throws IOException
    {
		float rat;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int Hit=0;
		int Fault=0;
		int j=0;
		boolean check;
		for(int i=0;i<n;i++)
		{
			check=false;
			for(int k=0;k<f;k++)
				if(frame[k]==pages[i])
				{
					check=true;
					Hit=Hit+1;
				}
			if(check==false)
			{
				frame[j]=pages[i];
				j++;
				if(j>=f)
					j=0;
				Fault=Fault+1;
			}

		}
		for(int i=0;i<n;i++)
		{
			for(int j1=0;j1<f;j1++)
			{
				System.out.print(frame[j1]+"\t");
			}			
			System.out.println();
		}
		rat = (float)Hit/(float)n;
		//System.out.println("HIT:"+Hit+"  FAULT:"+Fault+"   HIT RATIO:"+rat);
		System.out.println("The number of Hits: " + Hit);
        System.out.println("Hit Ratio: " + rat);
        System.out.println("The number of Faults: " + Fault);
    }*/

	
	static void fifo(int f, int n, int pages[], int frame[]) throws Exception
    {
        int p,num=0, pageHit=0;
       // int pages[];
      //  int frame[];
        boolean flag = true;
      
        //BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
      //  System.out.println("Enter number of frames : ");
        
      //  System.out.println("Enter number of pages : ");
        p = n;
      
   
      /*  System.out.println("Enter page number : ");
        for(int i=0;i<p;i++)
            pages[i] = Integer.parseInt(br.readLine());*/
      
        for(int i=0; i<p; i++)
        {
            flag = true;
            int page = pages[i];
            for(int j=0; j<f; j++)
            {
                if(frame[j] == page)
                {
                    flag = false;
                    pageHit++;
                    break;
                }
            }
            if(num == f)
                num = 0;
          
            if(flag)
            {
                frame[num] = page;
                num++;
            }
          //  System.out.print("frame : ");
            for(int k=0; k<f; k++)
                System.out.print(frame[k]+"\t");
            System.out.println();
          
        }
       // System.out.println("No. of page hit : "+pageHit);
        System.out.println("The number of Hits: " + pageHit);
        System.out.println("Hit Ratio: " + (float)((float)pageHit/p));
        System.out.println("The number of Faults: " + (p-pageHit));
    }
	
	
	public static void main(String args[]) throws Exception
	{
		System.out.println("--------------------------");
		System.out.println("Page Replacement algorithm");
		System.out.println("--------------------------");
		BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
		int f,n;
		int pages[]; 		//pgf-page fault
		System.out.println("Enter no. of frames: ");
    	f=Integer.parseInt(obj.readLine());
    	int frame[]=new int[f];
    	for(int i=0;i<f;i++)
    	{
    		frame[i]=-1;
    	}
    	System.out.println("Enter the no of pages ");
    	n=Integer.parseInt(obj.readLine());
    	pages=new int[n];
    	System.out.println("Enter the page no ");
    	for(int j=0;j<n;j++)
    	{
    	    pages[j]=Integer.parseInt(obj.readLine());
    	}
    	int s=1;
    	while(s==1)
    	{
    		System.out.println("1.LRU");
    		System.out.println("2.Optimal");
    		System.out.println("3.FIFO");
    		System.out.println("Enter your choice");
    		int ch;
    		ch=Integer.parseInt(obj.readLine());
    		switch(ch)
    		{
    			case 1: for(int i=0;i<f;i++)
    	    	{
    	    		frame[i]=-1;
    	    	}
    				lruinput(f,n,pages,frame);
    					break;
    			case 2: for(int i=0;i<f;i++)
    	    	{
    	    		frame[i]=-1;
    	    	}
    				opt(f,n,pages,frame);
    					break;
    			case 3: for(int i=0;i<f;i++)
    	    	{
    	    		frame[i]=-1;
    	    	}
    				fifo(f,n,pages,frame);
    					break;    			
    			default:System.out.println("Invalid choice");
    					break;    				
    		}
    		System.out.println("Do you want to continue (yes = 1 no = 0)");
    		s=Integer.parseInt(obj.readLine());
    	}
    }
	
}

 
      
      