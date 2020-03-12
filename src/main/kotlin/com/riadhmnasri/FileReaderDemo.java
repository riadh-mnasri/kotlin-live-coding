package com.riadhmnasri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReaderDemo {

    private static String displayFileContent(String fileName)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        InputStream inputStream = FileReaderDemo.class.getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            String fileName = "/data/content.txt";
            String text = displayFileContent(fileName);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
