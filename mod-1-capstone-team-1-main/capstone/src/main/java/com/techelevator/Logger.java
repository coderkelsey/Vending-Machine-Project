package com.techelevator;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;

public class Logger {

    public void fileWriter(String logRecord) throws IOException {
        String cwd = System.getProperty("user.dir");
        File inventoryLog = new File("log.txt");
        LocalDateTime currDateTime =  LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy h:m:ss a");
        String currDateTimeStr = currDateTime.format(format);
        if(inventoryLog.isFile()) {
            PrintWriter writer = new PrintWriter(new FileOutputStream(inventoryLog.getAbsoluteFile(), true));
            writer.append(System.getProperty("line.separator"));
            writer.append(currDateTimeStr + logRecord);//append().append();
            writer.flush();
            writer.close();
        }else{
            boolean fileCreated = inventoryLog.createNewFile();
            if(fileCreated) {
                PrintWriter writer = new PrintWriter(inventoryLog.getAbsoluteFile());
                writer.println(currDateTimeStr + logRecord);
                writer.flush();
                writer.close();
            }

        }
    }

}
