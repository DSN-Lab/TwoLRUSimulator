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
        int num = 0;
        Simulator simulator = new Simulator(10L, 10000L, 1000L);
        File traceFile = new File("/home/mohammad/Desktop/simhead.txt");
        Scanner scanner = new Scanner(traceFile);
        while(scanner.hasNext())
        {
            num++;
            String request = scanner.nextLine();
            String[] splitted = request.split(Pattern.quote("#"));

            Page p = new Page(new BigInteger(splitted[0]).divide(new BigInteger("4096")));
//            if(p.getAddress().equals(new BigInteger("4295111488")))
//                System.out.println("Hi");
            simulator.add(p);
        }
        simulator.printStatistics();
    }


}
