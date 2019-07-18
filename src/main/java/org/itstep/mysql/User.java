

package org.itstep.mysql;

import javax.persistence.*;
// класс Юзер - модель пользователя
@Entity //Показывает гибернейту, что то нужно связывать
@Table(name = "users") //определяет для гибернейта какую использовать таблицу
public class User {
    @Id //указание для г, что это праймари
    @GeneratedValue(strategy = GenerationType.AUTO) //не надо генерить, т.к. БД сама генерит
    @Column(name = "id") //связь со столбцом в БД
    private Integer id;

    @Column(name = "username", unique = true, nullable = false) // указывает г, что это уникальное поле. и не может быть пустым.
    private  String username;

    @Column(name = "password")
    private  String password;

    @Column(name = "firstname")
    private  String firstname;

    @Column(name = "lastname")
    private  String lastname;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



}
