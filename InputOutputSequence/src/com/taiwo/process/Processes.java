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
        System.out.println("LEN : " + seqs.length);
        if (seqs.length == 0 || seqs.length > 20){
            throw new customException( "The length of sequence does not meet criteria" );
        }
        String Project_dir = System.getProperty("user.dir") + "\\src";//\\com\\taiwo";
        inboxPath = inboxPath.endsWith(".txt") ? Project_dir + "\\inbox\\" + inboxPath : Project_dir + "\\inbox\\" + inboxPath + ".txt";
        System.out.println("INBOX PATH : " + inboxPath);

        outboxPath = outboxPath.endsWith(".txt") ? Project_dir + "\\outbox\\" + outboxPath : Project_dir + "\\outbox\\" + outboxPath + ".txt";
        System.out.println("OUTBOX PATH : " + outboxPath);

        String result_from_inbox_file = read_input_file(inboxPath);
        if (result_from_inbox_file.trim().length() < 1){
            throw new customException( "Input file content is empty" );
        }
        for (int i = 0; i < seqs.length; i++){
            System.out.println("SEQ :" + seqs[i]);
            if (seqs[i].trim().equalsIgnoreCase("1") || seqs[i].trim().equals("1"))
                result_from_inbox_file = convert_7_e(result_from_inbox_file);
            else if (seqs[i].trim().equalsIgnoreCase("2") || seqs[i].trim().equals("2"))
                result_from_inbox_file = convert_to_upperCase(result_from_inbox_file);
            else if (seqs[i].trim().equalsIgnoreCase("3") || seqs[i].trim().equals("3"))
                result_from_inbox_file = remove_white_spaces(result_from_inbox_file);
            else if (seqs[i].trim().equalsIgnoreCase("4") || seqs[i].trim().equals("4"))
                result_from_inbox_file = convert_to_ascii(result_from_inbox_file);
            else {
                throw new customException("INVALID SEQUENCE NUMBER : " + seqs[1]);
            }
        }
        write_to_file(outboxPath, result_from_inbox_file);
    }

    private String read_input_file(String inputFile){

        File file = new File(inputFile);
        // Declaring a string variable
        String st;
        String input_file_string = "";
        try {
            // Creating an object of BufferedReader class
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder(input_file_string);
            // Condition holds true till
            // there is character in a string
            while ((st = br.readLine()) != null) {
                stringBuilder.append(st + System.lineSeparator());
            }
            input_file_string = stringBuilder.toString();
        }catch (Exception ex){
            System.err.println("Error Reading input file");
            ex.printStackTrace();
        }
        return input_file_string;
    }

    private void write_to_file(String output, String content){
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
        }
        // Catch block to handle the exceptions
        catch (Exception ex) {
            // Print message as exception occurred when
            // invalid path of local machine is passed
            System.err.println("Invalid Path");
        }
    }

    private String convert_7_e(String inputString){
        return inputString.replace('7', 'e');
    }

    private String convert_to_upperCase(String inputString){
        return inputString.toUpperCase();
    }

    private String remove_white_spaces(String inputString){
        return inputString.trim().replaceAll("\\s", "");
    }

    private String convert_to_ascii(String inputString){
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
}
