package com.cheng.pojo;

import lombok.Data;

import java.util.Date;


@Data
public class Book {

   private  String id ;
   private  String name;
   private  Double price ;
   private  String author;
   private  String publisher;
   private  String publishDate;
   private Date bookDesc;

}
