package qbert.model.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class RankingBuilder {
 
  private Integer score;
  private String name;
  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  Date date;
  
  public RankingBuilder(){}
  
  public RankingBuilder(Builder builder) {
    score = builder._score;
    name = builder._name;
    date = builder._date;
  }
 
  public String toString() {
    return this.name+'#'+this.dateFormat.format(this.date)+'?'+this.score;
  }
  
  public String getName() {
      return this.name;
  }
  
  public static class Builder {
      
      private Integer _score;
      private String _name;  
      private Date _date = new Date();
      private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
      public Builder() {
      }
      
      public Builder addScore(Integer i) {
          _score = i;
          return this;
      }
      
      public Builder addChar(Integer i) {
          _name += alphabet.charAt(i);
          return this;
      }
      
      public Builder resetName() {
          _name=" ";
          return this;
      }
   
      public RankingBuilder build() {
          return new RankingBuilder(this);
      }
   
    }
 
}