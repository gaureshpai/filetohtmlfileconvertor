package html;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner fileIn; // input file connection
        PrintWriter fileOut; // output file connection
        String filenameIn; // original file name
        String filenameOut; // new HTML file's name
        int dotIndex; // hold position of . in filename
        String line = null; // line from the input file
        
        System.out.println("Enter filename or path");
        filenameIn = scanner.nextLine();
        
        try {
            fileIn = new Scanner(new FileReader(filenameIn));
            
            dotIndex = filenameIn.lastIndexOf(".");
            
            if(dotIndex == -1) {
            	filenameOut = filenameIn + ".html";
            } else {
            	filenameOut = filenameIn.substring(0, dotIndex) + ".html";
            }
            fileOut = new PrintWriter(filenameOut);
            
            // Start writing HTML content
            fileOut.println("<html>");
            fileOut.println("<head>");
            // Add background color style
            fileOut.println("<style>");
            fileOut.println("body { background-color: #f0f0f0; }");
            fileOut.println("h1 { color: #333333; }");
            fileOut.println("</style>");
            fileOut.println("</head>");
            fileOut.println("<body>");
            
            // Write heading related to the filename
            fileOut.println("<h1>HTML Conversion for File: " + filenameIn + "</h1>");
            
            // Read the first line from the input file
            try {
                line = fileIn.nextLine();
            } catch(NoSuchElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            // Check if the file is empty
            if(line == null) {
                fileOut.println("<p>This file is empty</p>");
            } else {
                // Write the content into the HTML file within the <body> tags
                fileOut.println("<p>" + line + "</p>");
                while(fileIn.hasNextLine()) {
                    // Add line breaks
                    fileOut.println("<br>");
                    line = fileIn.nextLine();
                    
                    // Check for empty lines
                    if(line.isEmpty()) {
                        fileOut.println("<br>");
                    } else {
                        fileOut.println("<p>" + line + "</p>");
                    }
                }
                // End of content
                fileOut.println("<br>");
            }
            // End of HTML
            fileOut.println("</body>");
            fileOut.println("</html>");
            
            System.out.println("HTML file is processed: " + filenameOut);
            
            // Close input and output resources
            fileIn.close();
            fileOut.close();
            
        } catch(FileNotFoundException e) {
            System.out.println("File not found: " + filenameIn);    
        }
    }
}
