package org.itstep.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/users") //подразумевают запрос любого типа (и ГЕТ и ПОСТ), тогда как ГетМаппинг только для запроса ГЕТ
public class UserController {
    @Autowired //делаем инъекцию зависимости, чтобы получить объект репозитори
    private UserRepository repository;

    @GetMapping("/list")
    // отдаем сразу объект привязки модели и вью.
    public ModelAndView list(Map<String, Object> model) {
        Iterable<User> list = repository.findAll();

        model.put("header", "Список пользователей (проверка)"); //просто пример вывода строки в шаблон header, который находится в list.mustache (файл шаблона). Он в application.properties указан с префиксом и суффиксом
        model.put("list", list);

        return new ModelAndView("users/list", model);
    }

    // users/user/123
    @GetMapping("/user/{id}")
    public ModelAndView user(@PathVariable(name = "id") User user, Map<String, Object> model) {
        // вместо аргумента (Спринг понимает, что нужно автоматом создать model), можно самому создать объект: Map<String, Object> model = new HashMap<>();

        if (null == user) {
            // обработка несуществующего пользователя
        }

        model.put("user", user);

        return new ModelAndView("users/user", model);
    }
}
