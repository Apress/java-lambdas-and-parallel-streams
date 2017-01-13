package de.muellerbruehl.parallelstreams;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Person {

  private String _givenName;
  private String _surname;
  private Gender _gender;
  private int _age;
  private Map<Integer, ArticleInfo> _selling = new ConcurrentHashMap<>();
  private Map<Integer, ArticleInfo> _buying = new ConcurrentHashMap<>();
  private int _discount;

  public String getGivenName() {
    return _givenName;
  }

  public void setGivenName(String givenName) {
    _givenName = givenName;
  }

  public String getSurname() {
    return _surname;
  }

  public void setSurname(String surname) {
    _surname = surname;
  }

  public Gender getGender() {
    return _gender;
  }

  public void setGender(Gender gender) {
    _gender = gender;
  }

  public boolean isFemale(){
    return _gender == Gender.Female;
  }
  
  public int getAge() {
    return _age;
  }

  public void setAge(int age) {
    _age = age;
  }

  public boolean isVendor() {
    return _selling.size() > 0;
  }

  public int getDiscount() {
    return _discount;
  }

  public void setDiscount(int discount) {
    _discount = discount;
  }

  public Map<Integer, ArticleInfo> getSelling() {
    return _selling;
  }

  public void setSelling(Map<Integer, ArticleInfo> selling) {
    _selling = selling;
  }

  public Map<Integer, ArticleInfo> getBuying() {
    return _buying;
  }

  public void setBuying(Map<Integer, ArticleInfo> buying) {
    _buying = buying;
  }

}
