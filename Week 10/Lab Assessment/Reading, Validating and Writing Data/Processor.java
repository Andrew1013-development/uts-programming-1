// Q2-W10-A: Reading, Validating and Writing Data

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.*;
import java.io.*;

public class Processor {

    public static <O> void mapData(String inputFilename, String outputFilename, Function<Integer, O> map) {
        O val;
        BufferedReader br;
        BufferedWriter bw;

        try {
            br = new BufferedReader(new FileReader(inputFilename));
            bw = new BufferedWriter(new FileWriter(outputFilename));
            while (br.ready()) {
                try {
                    val = map.apply(Integer.parseInt(br.readLine()));
                    System.out.println(val);
                    bw.write(val.toString());
                    bw.newLine();
                } catch (NumberFormatException e) {
                    //e.printStackTrace();
                }
            }
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mapData("inputTest.txt", "outputTest.txt", Function.<Integer>identity());
    }
}
