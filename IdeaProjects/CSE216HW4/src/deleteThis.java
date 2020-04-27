public class deleteThis
{
    public static void main(String[] args)
    {
        int files = 3;
        int threads = 2;

        System.out.println(numThreads(files, threads));
    }

    public static int numThreads(int files, int threads)
    {
        int returnValue = 0;

        if(files / threads >= threads)
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
