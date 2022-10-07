package com.cheng.mapper;

import com.cheng.pojo.Book;

import java.util.List;

public interface BookMapper {

    //保存一本图书
    public void save(Book book);

    //通过唯一表述来获取图书信息
    Book selectBookById(String bookId);

    //通过图书和作者名称来获取图书信息
    List<Book> selectBookByNameAndAuthor(Book book);

    //

}
