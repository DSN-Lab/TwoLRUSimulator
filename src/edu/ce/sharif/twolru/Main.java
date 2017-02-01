package edu.ce.sharif.twolru;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by mohammad on 1/30/17.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        long num = 0;
        Simulator simulator = new Simulator(10L, 900000L, 100L);
        File traceFile = new File("C:\\Users\\Farzin\\Desktop\\trace.txt");
        Scanner scanner = new Scanner(traceFile);
        String splitPattern=Pattern.quote("#");
        Date start = new Date();
        while(scanner.hasNext())
        {
            num++;
            String request = scanner.nextLine();
            String[] splitted = request.split(splitPattern);

            Page p = new Page(new BigInteger(splitted[0]));
            simulator.add(p);
            if(num % 50000 == 0) {
                double prec = (double) num / 500000;
                prec = prec * 100;
                System.out.println(prec);
            }

        }
        Date end = new Date();
        System.out.println((end.getTime()-start.getTime())/1000);
        simulator.printStatistics();
    }


}
