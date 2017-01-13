/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.parallelstreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProvider {

  private static final DataProvider _instance = new DataProvider();
  private List<String> _surNames = new ArrayList<>();
  private final Map<String, Gender> _givenNames = new HashMap<>();
  private final Map<Integer, Article> _articles = new HashMap<>();

  public static DataProvider getInstance() {
    return _instance;
  }

  private DataProvider() {
    init();
  }

  public List<String> getSurNames() {
    return _surNames;
  }

  public Map<String, Gender> getGivenNames() {
    return _givenNames;
  }

  public Map<Integer, Article> getArticles() {
    return _articles;
  }

  private void init() {
    try {
      _surNames = readFile("Surnames.csv");
      readFile("GivenNamesFemale.csv").stream().forEach(n -> _givenNames.put(n, Gender.Female));
      readFile("GivenNamesMale.csv").stream().forEach(n -> _givenNames.put(n, Gender.Male));
      int articleNo = 0;
      for (String line : readFile("Articles.csv")) {
        if (!line.trim().isEmpty()) {
          articleNo++;
          Article article = new Article(line, articleNo);
          _articles.put(articleNo, article);
        }
      };
    } catch (IOException ex) {
      Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private List<String> readFile(String fileName) throws IOException {
    List<String> lines = new ArrayList<>();
    try (InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    }
    return lines;
  }
}
