package edu.ce.sharif.twolru;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by mohammad on 1/30/17.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        long num = 0;
        Simulator simulator = new Simulator(10L, 900000000L, 1000L);
        File traceFile = new File("/home/mohammad/simforkhorooji_fixed.o83020");
        Scanner scanner = new Scanner(traceFile);
        String splitPattern=Pattern.quote("#");
        while(scanner.hasNext())
        {
            num++;
            String request = scanner.nextLine();
            String[] splitted = request.split(splitPattern);

            Page p = new Page(new BigInteger(splitted[0]));
            simulator.add(p);
            if(num % 1000000 == 0) {
                double prec = (double) num / 331000000;
                prec = prec * 100;
                System.out.println(prec);
            }

        }
        simulator.printStatistics();
    }


}
