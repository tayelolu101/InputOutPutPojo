package com.taiwo;

import com.taiwo.process.Processes;
import com.taiwo.process.customException;

public class Main {

    public static void main(String[] args) throws customException {
	// write your code here
        Processes processes = new Processes();
        String inbox = "inputTest.txt";
        String outbox = "outputTest.txt";
        String sequence = "1 2 3 4";
        System.out.println("File process starting ...........................");
        processes.start_process(inbox, outbox, sequence);
        System.out.println("File process ongoing ...........................");
        System.out.println("File process Ended ...........................");
    }
}
