package A;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {
    static String[][] files = new String[][]{
        new String[]{"in1", "out1"},
        new String[]{"in2", "out2"},
        new String[]{"in3", "out3"},
//        new String[]{"in4", "out4"},
    };
    static Test t = new Test();
    static String dir = "src/" + t.getClass().getPackageName() + "/";


    public static void main(String[] args) throws IOException {
        for (var testcase: files){
            var stdin = System.in;
            var stdout = System.out;
            String tempFile = dir + "tmp.txt";
            String inFile = dir + testcase[0];
            String outFile = dir + testcase[1];
            try {
                System.setIn(new FileInputStream(inFile));
                System.setOut(new PrintStream(tempFile));
                Main.main(args);
            } finally {
                System.setIn(stdin);
                System.setOut(stdout);
            }

            if (isEqualFiles(tempFile, outFile)) {
                System.out.println(testcase[0] + " OK");
            }
            else {
                System.out.println(testcase[0] + " FAIL");
                System.out.println("ожидалось:");
                System.out.println(Files.readString(Path.of(outFile)));
                System.out.println("программа вывела:");
                System.out.println(Files.readString(Path.of(tempFile)));
            }

        }
    }
    public static boolean isEqualFiles(String f1, String f2) throws IOException {
        String content1 = Files.readString(Path.of(f1));
        String content2 = Files.readString(Path.of(f2));
        return content1.equals(content2);
    }
}