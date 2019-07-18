// в application.properties обязательно прописываем настройки для Г: к какой БД подключаться и с какими параметрами

package org.itstep.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// создаем интерфейс, который позволяет Г взаимодействовать с БД (крудить), унаследуемся от гибернейтовского CrudRepository
// причем создается иньекция - в контроллере (IndexController) достаточно объявить (но не инстанцировать) объект UserRepository и сам Спринг будет создавать этот объект
// если бы спринг этого не делал, то нам пришлось делать анонимный класс на основе этого интерфейса и прописывать все его методы (CrudRepository), которые мы тольком не знаем

@Repository // указание для Г, что это "Репозиторий" - инструмент взаимодействия с БД
public interface UserRepository extends CrudRepository<User, Integer> {

    //Спринг может понимать по findBy - что нужно искать по username или указанному любому другому
    // для возврата коллекции
    Iterable<User> findByUsername(String Username);

    // для возврата одной сущности
//    Optional<User> findByUsername(String Username);

    // псевдо-скл с названиями класса и его свойств (т.е. используем не столбцы таблицы, а уже объекты). Это когда нам надо делать какие-нибудь кастомные запросы
    @Query("SELECT u FROM User u WHERE u.username = 'admin'")
    Iterable<User> findAllAdmins();

    // Нативный скл-запрос из-за опции nativeQuery = true
//    @Query(value = "SELECT * FROM User u WHERE u.username = 'admin'", nativeQuery = true)
//    Iterable<User> findAllAdmins();


}

