import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeMap;

public class deleteThis
{
    public static void main(String[] args)
    {
        int files = 1000;
        int threads = 500;

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
       // System.out.println(getExtension("blah.DS"));
//        File directory = new File("1000_text_files");
//        File[] files = directory.listFiles();
//        System.out.println(files.length);// Array of all files within the directory
//        System.out.println(Arrays.toString(files));
//        File[] newFiles = filter(files);
//        System.out.println(Arrays.toString(newFiles));
//        System.out.println(newFiles.length);
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

    public static String getExtension(String fileName)
    {
        String ans = "";
        if(fileName.length() > 0 && fileName.lastIndexOf('.') >= 0)
        {
            ans = fileName.substring(fileName.lastIndexOf('.'));
        }
        return ans;
    }

    public static File[] filter(File[] files)
    {
        return (File[]) Arrays.stream(files)
                .filter(f -> isTextFile(Paths.get(f.getPath())))
                .toArray(File[]::new);
    }

    public static boolean isTextFile(Path p){ return p.toString().endsWith("txt"); }
}
