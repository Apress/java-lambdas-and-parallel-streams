package de.muellerbruehl.parallelstreams;

public class Money {

  private long _cents;

  Money() {
    _cents = 0;
  }

  Money(String value) {
    setValue(value);
  }

  public long getCents() {
    return _cents;
  }

  public void setCents(long cents) {
    _cents = cents;
  }

  public String getValue() {
    return _cents / 100 + "." + _cents % 100;
  }

  public void setValue(String value) {
    int pos = value.indexOf(".");
    if (pos == -1) {
      _cents = 100 * Long.parseLong(value);
    } else {
      _cents = 100 * Long.parseLong(value.substring(0, pos));
      String decimals = value.substring(pos + 1) + "00";
      _cents += Long.parseLong(decimals.substring(0, 2));
    }
  }
  
  public void add (long cents){
    _cents += cents;
  }
}
