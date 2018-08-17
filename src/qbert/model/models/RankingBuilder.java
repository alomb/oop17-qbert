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
    score = builder.scoreB;
    name = builder.nameB;
    date = builder.dateB;
  }
 
  public String toString() {
    return this.name+'#'+this.dateFormat.format(this.date)+'?'+this.score;
  }
  
  public String getName() {
      return this.name;
  }
  
  public static class Builder {
      
      private Integer scoreB;
      private String nameB ="";  
      private Date dateB = new Date();
      private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
      public Builder() {
      }
      
      public Builder addScore(Integer i) {
          scoreB = i;
          return this;
      }
      
      public Builder addChar(Integer i) {
          nameB += alphabet.charAt(i);
          return this;
      }
      
      public Builder reset() {
          nameB=" ";
          dateB = new Date();
          return this;
      }
   
      public RankingBuilder build() {
          return new RankingBuilder(this);
      }
   
    }
 
}