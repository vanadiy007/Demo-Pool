package org.itstep.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class IndexController {
    @Autowired //Г будет понимать, что здесь надо делать иньекцию.
    private UserRepository repository;

    @GetMapping("/") // обязательно указываем корень для Г, чтобы он мог дальше выполнять экшн, а не просто показывать index.html из ресурсов-статик.
    public String index(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setFirstname("Administrator");

        repository.save(user); //собственно сама инъекция (не надо создавать объект repository - можно сразу пользоваться его методами

        return "index.html";
    }

    // /user/123 для "/user/123" (@PathVariable)
    // /user?id=123 для "/user" (@RequestParam)
    @GetMapping("/user")
    public @ResponseBody User user(@RequestParam Integer id){ // @ResponseBody - объект Юзер передается из метода юзер уже в текстовом виде json'a
        if(id == null){
            return null;
        }

        Optional<User> user = repository.findById(id); // Optional - это такая обертка над юзером, которая возвращает либо юзера, либо нулл - т.е. не надо заморачиваться с обработкой объектов, которые пустые.

//        if (user.isPresent()){
//            return user.get();
//        }
//        return null;
// но можно намного короче:
        return user.orElse(null);
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<User> list(){
        return repository.findAll();
    }

    //метод для выдачи коллекции
    @GetMapping("/list2")
    public @ResponseBody Iterable<User> list2(){
        return repository.findByUsername("admin");
    }

    // метод для выдачи одной сущности
//    @GetMapping("/user2")
//    public @ResponseBody Optional<User> user2(){
//        return repository.findByUsername("admin");
//    }


}
