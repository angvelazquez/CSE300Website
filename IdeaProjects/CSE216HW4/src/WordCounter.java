import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter {

    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.
    public static final Path FOLDER_OF_TEXT_FILES  = Paths.get("filesPlace"); // path to the folder where input text files are located
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get("table"); // path to the output plain-text (.txt) file
    public static final int  NUMBER_OF_THREADS     = 3;                // max. number of threads to spawn

    // NEED TO IMPLEMENT
    static class ReadThread extends Thread implements Runnable {
        private File textFile;
        private TreeMap<String, Integer> map;

        public TreeMap<String, Integer> getMap()
        {
            return map;
        }

        public ReadThread(File textFile, TreeMap<String, Integer> map)
        {
            this.textFile = textFile;
            this.map = map;
        }

        public String fileName()
        {
            return textFile.getName();
        }

        public void run() {
            try
            {
                Scanner readText = new Scanner(textFile);
                while(readText.hasNextLine())
                {
                    String currentLine = readText.nextLine();
                    String[] split = currentLine.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                    for(int i = 0; i < split.length; i++)
                    {
                        if(map.get(split[i]) == null)
                        {
                            map.put(split[i], 1);
                        }
                        else
                        {
                            map.put(split[i], map.get(split[i])+1);
                        }
                    }
                }
                readText.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void main(String... args)
    {
        // your implementation of how to run the WordCounter as a stand-alone multi-threaded program
        long start = System.currentTimeMillis();
        ArrayList<ReadThread> runningThreads = new ArrayList<>();
        File directory = new File(String.valueOf(FOLDER_OF_TEXT_FILES));
        File[] files = directory.listFiles(); // Array of all files within the directory
        assert files != null; // idk what this does yet lol
        if(NUMBER_OF_THREADS >= files.length)
        {
            for(int i = 0; i < files.length; i++)
            {
                TreeMap<String, Integer> map = new TreeMap<>();
                ReadThread t = new ReadThread(files[i], map);
                runningThreads.add(t);
                t.start();
            }
        }
        else
        {

        }

        ArrayList<String> mergedKeyset = new ArrayList<>();
        TreeMap<String, TreeMap<String, Integer>> fileMaps = new TreeMap<>();
        for(int i = 0; i < runningThreads.size(); i++)
        {
            try
            {
                runningThreads.get(i).join();
                fileMaps.put(runningThreads.get(i).fileName(), runningThreads.get(i).getMap());
                mergedKeyset.addAll(runningThreads.get(i).getMap().keySet());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Set<String> noRepeats = new HashSet<>(mergedKeyset);
        mergedKeyset.clear();
        mergedKeyset.addAll(noRepeats);

        Collections.sort(mergedKeyset);

        String longest = WordCounter.longest(mergedKeyset, true);
        int firstColSpace = longest.length() + 1;

        // Write to file
        try
        {
            FileWriter writer = new FileWriter(String.valueOf(WORD_COUNT_TABLE_FILE), true);
            for(int i = 0; i < firstColSpace; i++)
            {
                writer.write(" ");
            }

            for(String fileName : fileMaps.keySet())
            {
                writer.write(fileName + "\t");
            }

            writer.write("total\n");

            for(int i = 0; i < mergedKeyset.size(); i++)
            {
                String empty = "";
                int padDiff = firstColSpace - mergedKeyset.get(i).length();
                String padding = String.format("%-" + padDiff + "s", empty);
                String values = "";
                int lineTotal = 0;
                for(String keyFile : fileMaps.keySet())
                {
                    if(fileMaps.get(keyFile).get(mergedKeyset.get(i)) == null)
                    {
                        String pad = String.format("%-" + (keyFile.length() - 1) + "s", empty);
                        values += "0" + pad + "\t";
                    }
                    else
                    {
                        String val = fileMaps.get(keyFile).get(mergedKeyset.get(i)) + "";
                        String pad = String.format("%-" + (keyFile.length() - val.length()) + "s", empty);
                        values += val + pad + "\t";
                        lineTotal += fileMaps.get(keyFile).get(mergedKeyset.get(i));
                    }
                }
                String line = mergedKeyset.get(i) + padding + values + lineTotal + "\n";
                writer.write(line);
            }
            writer.close();
            long end = System.currentTimeMillis();
            System.out.println(end - start + "ms");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String longest(final Collection<String> strings, final boolean from_start) {
        return strings
                .stream()
                .reduce((s1, s2) -> (s1.length() > s2.length()) ? s1 : ((s1.length() < s2.length()) ? s2 : (from_start ? s1 : s2))).orElse("");
    }
}