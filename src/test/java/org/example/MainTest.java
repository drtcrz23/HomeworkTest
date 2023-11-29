package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  @Test
  public void testWrite() throws IOException {
    String text = "Hello, World!";
    File myFile = new File("testOutputFile.txt");
    FileOutputStream outputStream = new FileOutputStream(myFile);
    byte[] buffer = text.getBytes();
    outputStream.write(buffer);
    outputStream.close();
//    System.out.println(text);
    String content = new String(Files.readAllBytes(Paths.get("testOutputFile.txt")));
    assertEquals(text, content);
//    System.out.println(content);
  }

  @Test
  public void testRead() {
    try {
      String path = "testInputFile.txt";
      String expectedContent = "Test message";
      String actualContent = Main.readFile(path);
      assertEquals(expectedContent, actualContent);
    } catch (IOException e) {
      fail("Exception should not have been thrown", e);
    }
  }

  @Test
  public void testEncryptMessage() {
    String message = "Test message";
    int encryptionKey = 3;
    String expectedEncryptedMessage = "Whvw phvvdjh";
    String actualEncryptedMessage = Main.encryptMessage(message, encryptionKey);
    assertEquals(expectedEncryptedMessage, actualEncryptedMessage);
  }

  @Test
  void testReadFileThrowsIOException() {
    String expectedMessage = "File with path \"nonExistentFile.txt\" not found";
    String path = "nonExistentFile.txt";
    Exception e = assertThrows(IOException.class, () -> {
      Main.readFile(path);
    });
    assertEquals(expectedMessage, e.getMessage());
  }

  @Test
  public void mainTest() {
    try {
      String file = "inputFile.txt";
      String message = Main.readFile(file);
      String actualMessage = Main.encryptMessage(message, 3);
      String path = "outputFile.txt";
      String answer = Main.readFile(path);
      assertEquals(actualMessage, answer);
    } catch (IOException e) {
      fail("Exception should not have been thrown", e);
    }
  }
}