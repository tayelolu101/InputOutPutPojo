package com.taiwo.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Processes {

    public void start_process(String inboxPath, String outboxPath, String Seq) throws customException {
        String [] seqs = Seq.split(" ");
        System.out.println("Checking sequence length ........................................");
        System.out.println("LEN : " + seqs.length);
        if (seqs.length == 0 || seqs.length > 20){
            throw new customException( "The length of sequence does not meet criteria........." );
        }
        System.out.println("Getting Project root path .........................................");
        String Project_dir = System.getProperty("user.dir");// + "\\src";
        inboxPath = inboxPath.endsWith(".txt") ? Project_dir + "\\inbox\\" + inboxPath : Project_dir + "\\inbox\\" + inboxPath + ".txt";
        System.out.println("INBOX PATH : " + inboxPath);

        outboxPath = outboxPath.endsWith(".txt") ? Project_dir + "\\outbox\\" + outboxPath : Project_dir + "\\outbox\\" + outboxPath + ".txt";
        System.out.println("OUTBOX PATH : " + outboxPath);

        String result_from_inbox_file = read_input_file(inboxPath);
        if (result_from_inbox_file.trim().length() < 1){
            throw new customException( "Input file content is empty" );
        }
        // To use for each
        //result_from_inbox_file = for_each_option(seqs, result_from_inbox_file);
        // To use switch case
        result_from_inbox_file = switch_case_option(seqs, result_from_inbox_file);
        write_to_file(outboxPath, result_from_inbox_file);
    }

    public String read_input_file(String inputFile){
        System.out.println("Start reading from input file...........................");
        File file = new File(inputFile);
        // Declaring a string variable
        String st;
        String input_file_string = "";
        try {
            // Creating an object of BufferedReader class
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder(input_file_string);
            // Condition holds true till there is character in a string
            while ((st = br.readLine()) != null) {
                stringBuilder.append(st + System.lineSeparator());
            }
            input_file_string = stringBuilder.toString();
            System.out.println("End of reading from input file...........................");
        }catch (Exception ex){
            System.err.println("Error Reading input file..................................");
            ex.printStackTrace();
        }
        return input_file_string;
    }

    public void write_to_file(String output, String content){
        System.out.println("Start writing to output file...................................");
        // Creating an instance of file
        Path path = Paths.get(output);
        // Converting string to byte array
        // using getBytes() method
        byte[] arr = content.getBytes();

        // Try block to check for exceptions
        try {
            // Now calling Files.write() method using path
            // and byte array
            Files.write(path, arr);
            System.out.println("End writing to output file...........................");
        }
        // Catch block to handle the exceptions
        catch (Exception ex) {
            // Print message as exception occurred when
            // invalid path of local machine is passed
            System.err.println("Error writing to output file........................");
        }
    }

    public String convert_7_e(String inputString){
        return inputString.replace('7', 'e');
    }

    public String convert_to_upperCase(String inputString){
        return inputString.toUpperCase();
    }

    public String remove_white_spaces(String inputString){
        return inputString.trim().replaceAll("\\s", "");
    }

    public String convert_to_ascii(String inputString){
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(result);
        for(int i = 0; i < inputString.length(); i++){
            int AsciiValue = (int) inputString.charAt(i);
            if ( AsciiValue >= 32 && AsciiValue <= 63){
                int newAscii = AsciiValue + 1;
                stringBuilder.append(newAscii);
            }else {
                stringBuilder.append(inputString.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public String switch_case_option(String [] Seq, String res) throws customException {
        System.out.println("Using the switchCase Method ...........................");
        for(String seqs : Seq){
            switch (seqs){
                case "1" :
                    System.out.println("Convert 7 to e starting ...........................");
                    res = convert_7_e(res);
                    System.out.println("Convert 7 to e ending ...........................");
                    break;
                case "2" :
                    System.out.println("Convert uppercase starting ...........................");
                    res = convert_to_upperCase(res);
                    System.out.println("Convert uppercase ending ...........................");
                    break;
                case "3" :
                    System.out.println("Remove whitespaces starting ...........................");
                    res = remove_white_spaces(res);
                    System.out.println("Remove whitespaces ending ...........................");
                    break;
                case "4" :
                    System.out.println("Covert to Ascii starting ...........................");
                    res = convert_to_ascii(res);
                    System.out.println("Covert to Ascii ending ...........................");
                    break;
                default:
                    throw new customException("INVALID SEQUENCE NUMBER.............. : " + seqs);
            }
        }
        return res;
    }

    public String for_each_option(String [] seqs, String con) throws customException{
        System.out.println("Using the forEach Method ...........................");
        for (int i = 0; i < seqs.length; i++){
            System.out.println("SEQ :" + seqs[i]);
            if (seqs[i].trim().equalsIgnoreCase("1") || seqs[i].trim().equals("1")) {
                System.out.println("Convert 7 to e starting ...........................");
                con = convert_7_e(con);
                System.out.println("Convert 7 to e ending ...........................");
            }else if (seqs[i].trim().equalsIgnoreCase("2") || seqs[i].trim().equals("2")) {
                System.out.println("Convert uppercase starting ...........................");
                con = convert_to_upperCase(con);
                System.out.println("Convert uppercase ending ...........................");
            }else if (seqs[i].trim().equalsIgnoreCase("3") || seqs[i].trim().equals("3")) {
                System.out.println("Remove whitespaces starting ...........................");
                con = remove_white_spaces(con);
                System.out.println("Remove whitespaces ending ...........................");
            }else if (seqs[i].trim().equalsIgnoreCase("4") || seqs[i].trim().equals("4")) {
                System.out.println("Covert to Ascii starting ...........................");
                con = convert_to_ascii(con);
                System.out.println("Covert to Ascii ending ...........................");
            }else {
                throw new customException("INVALID SEQUENCE NUMBER.............. : " + seqs[1]);
            }
        }
        return con;
    }
}
