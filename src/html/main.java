package html;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter filename or path:");
        String filenameIn = scanner.nextLine();
        
        try {
            convertToHTML(filenameIn);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void convertToHTML(String filenameIn) throws IOException {
        try (Scanner fileIn = new Scanner(new FileReader(filenameIn));
             PrintWriter fileOut = createOutputFile(filenameIn)) {
            writeHTMLHeader(fileOut);
            writeHTMLBody(filenameIn, fileIn, fileOut);
            writeHTMLFooter(fileOut);
            System.out.println("HTML file is processed: " + getOutputFilename(filenameIn));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filenameIn);
        }
    }

    private static PrintWriter createOutputFile(String filenameIn) throws IOException {
        String filenameOut = getOutputFilename(filenameIn);
        return new PrintWriter(filenameOut);
    }

    private static String getOutputFilename(String filenameIn) {
        int dotIndex = filenameIn.lastIndexOf(".");
        if (dotIndex == -1) {
            return filenameIn + ".html";
        } else {
            return filenameIn.substring(0, dotIndex) + ".html";
        }
    }

    private static void writeHTMLHeader(PrintWriter fileOut) {
        fileOut.println("<!DOCTYPE html>");
        fileOut.println("<html>");
        fileOut.println("<head>");
        fileOut.println("<meta charset=\"UTF-8\">");
        fileOut.println("<title>HTML Conversion</title>");
        fileOut.println("<style>");
        fileOut.println("body { background-color: #f0f0f0; }");
        fileOut.println("h1 { color: #333333; }");
        fileOut.println("</style>");
        fileOut.println("</head>");
        fileOut.println("<body>");
    }

    private static void writeHTMLBody(String filenameIn, Scanner fileIn, PrintWriter fileOut) {
        fileOut.println("<h1>HTML Conversion for File: " + filenameIn + "</h1>");
        if (!fileIn.hasNextLine()) {
            fileOut.println("<p>This file is empty</p>");
            return;
        }

        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            if (!line.isEmpty()) {
                fileOut.println("<p>" + line + "</p>");
            } else {
                fileOut.println("<br>");
            }
        }
    }

    private static void writeHTMLFooter(PrintWriter fileOut) {
        fileOut.println("</body>");
        fileOut.println("</html>");
    }
}
