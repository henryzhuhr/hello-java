package com.example.restfulwebapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.restfulwebapp.entity.Book;

@RestController
@RequestMapping("/api")
public class BookController {
    private static final Logger log = Logger.getLogger(BookController.class.getName());
    private List<Book> book_list;

    public List<Book> getBookList() {
        return book_list;
    }

    public void setBookList(List<Book> book_list) {
        this.book_list = book_list;
    }

    public boolean addBook(Book book) {
        if (book.getName() == null || book.getName().equals(""))
            return false;
        else {
            this.book_list.add(book);
            return true;
        }
    }

    public BookController() {
        this.book_list = new ArrayList<Book>();
    }

    /**
     * 添加书籍到书籍列表
     * 
     * @param book 书籍实例
     * @return 书籍列表
     */
    @PostMapping("/book")
    public ResponseEntity<List<Book>> apiAddBook(@RequestBody Book book) {
        log.info("Post 请求添加书籍 name:" + book.getName());
        if (this.addBook(book))
            return ResponseEntity.ok(this.book_list);
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 根据书籍名称查询书籍
     * 
     * @brief @RequestParam("name") 表示请求参数中必须包含 name 参数，否则会报错，并且将 name 参数的值赋值给
     *        book_name
     * @param name
     * @return 书籍列表
     *         请求示例：http://localhost:8080/api/book?name=book1
     * 
     */
    @GetMapping("/book")
    public ResponseEntity<List<Book>> apiGetBookByName(@RequestParam("name") String book_name) {

        List<Book> results = book_list
                .stream() // 将 list 转换为 stream
                .filter(book -> book.getName().equals(book_name)) // 过滤出符合条件的元素
                .collect(Collectors.toList());// 将过滤出的元素收集到list中
        return ResponseEntity.ok(results);
    }

    /**
     * 根据书籍id删除书籍
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/book/{id}")
    public ResponseEntity<List<Book>> apiDeleteBookById(@PathVariable("id") int id) {
        book_list.remove(id);
        return ResponseEntity.ok(book_list);
    }

}
