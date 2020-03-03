package Lesson_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/list")
    public String showAllBooks(Model model) {
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("booksList", bookList);
        return "books-list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add-bookform";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/books/list";
    }
}
