package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

  public static String readFile(String path) throws IOException {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded);
    } catch (IOException e) {
      throw new FileNotFoundException(String.format("File with path \"%s\" not found", path));
    }
  }

  static void write(String message) throws IOException {
    File myFile = new File("outputFile.txt");
    FileOutputStream outputStream = new FileOutputStream(myFile);
    byte[] buffer = message.getBytes();
    outputStream.write(buffer);
    outputStream.close();
  }

  public static String encryptMessage(String message, int encryptionKey) {
    int alphabetSize = 26;
    char symbol;
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      symbol = (message.charAt(i));
      if (Character.isLetter(symbol)) {
        if ((Character.isLowerCase(symbol) && symbol <= 'z')
                || (Character.isUpperCase(symbol) && symbol <= 'Z')) {
          symbol = (char) (symbol + encryptionKey);
        } else {
          symbol = (char) (symbol - (alphabetSize - encryptionKey));
        }
      }
      stringBuilder.append(symbol);
    }

    return stringBuilder.toString();
  }

  public static void main(String[] args) throws IOException {

    String file = "inputFile.txt";
    String message;
    message = readFile(file);
    System.out.println(message);

    write(encryptMessage(message, 3));
    System.out.println(encryptMessage(message, 3));
  }
}
