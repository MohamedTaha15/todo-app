package controllers;

import jakarta.validation.Valid;
import models.Todoitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import repositories.TodoitemRepository;
import services.TodoitemService;

@Controller
public class TodoFormController {
    @Autowired
    private TodoitemService todoitemService;

    @GetMapping("/create-todo")
    public String showCreateForm(Todoitem todoitem){
        return "redirect:/new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoitem(@Valid Todoitem todoitem, BindingResult result , Model model){
        Todoitem item = new Todoitem();
        item.setDescription(todoitem.getDescription());
        item.setIsComplete(todoitem.getIsComplete());

        todoitemService.save(todoitem);
        return "redirect:/";

    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        Todoitem todoItem = todoitemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        todoitemService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Todoitem todoitem = todoitemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("todo", todoitem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid Todoitem todoitem, BindingResult result, Model model) {

        Todoitem item = todoitemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(todoitem.getIsComplete());
        item.setDescription(todoitem.getDescription());

        todoitemService.save(item);

        return "redirect:/";
    }
}
