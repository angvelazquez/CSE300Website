import java.util.TreeMap;

public class deleteThis
{
    public static void main(String[] args)
    {
        int files = 51;
        int threads = 30;
        
        int fileIDK = numThreads(files, threads);
        int loopCount = 1;
        int fCount = 1;
        System.out.println(fileIDK);
        for(int i = 0; i < (threads - 1) * fileIDK; i++)
        {
            //System.out.println(i);
            if(fCount == fileIDK)
            {
                //System.out.println(i);
                System.out.println("new thread");
                fCount = 0;
            }
            fCount++;
        }
    }

    public static int numThreads(int files, int threads)
    {
        int returnValue = 0;

        if(files / threads >= 2)
        {
            returnValue = files / threads;;
        }
        else if(files % threads != 0)
        {
            if (((threads - 1) * 2 ) + 1 == files)
                return 2;
            else
                return 1;
        }
        else if(threads == 1)
        {
            returnValue = files;
        }
        return returnValue;
    }
}
