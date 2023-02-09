package pl.coderslab;

import pl.coderslab.entity.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //TWORZENIE NOWEGO USERA W BAZIE
//        User userObj = new User();
//        UserDao userDaoObj = new UserDao();
//
//        for (int i = 12; i < 21; i++) {
//            userObj.setUserName("test1");
//            userObj.setEmail("test@test" + i + ".com");
//            userObj.setPassword("haslo1");
//            userDaoObj.create(userObj);
//            System.out.println("Utworzylem usera o id = " + userObj.getId());
//        }

 //       CZYTANIE USERA

//        UserDao readDaoUser = new UserDao();
//        User readUser = readDaoUser.read(58);
//        System.out.println("ID: " + readUser.getId() + "\nUsername: " + readUser.getUserName() + "\nPassword: " +readUser.getPassword() + "\nemail: " + readUser.getEmail());

// UPDATE USERA / rekordu

//        UserDao readDaoUser = new UserDao();
//        User readUser = readDaoUser.read(58);
//        System.out.println("ID: " + readUser.getId() + "\nUsername: " + readUser.getUserName() + "\nPassword: " +readUser.getPassword() + "\nemail: " + readUser.getEmail());
//
//        readUser.setUserName("testupdate");
//        readUser.setEmail("update@update.com");
//        readUser.setPassword("updatedHaslo");
//
//        readDaoUser.update(readUser);

        UserDao findAllUsers = new UserDao();
//        User[] users = new User[0];
//        users = findAllUsers.findAll();
//        System.out.println("users.length = " + users.length);
//        System.out.println("users[15].getId() = " + users[15].getId());

//        User secondUser = new User();
//        secondUser.setUserName("marek");
//        secondUser.setEmail("marek@coderslab.pl");
//        secondUser.setPassword("pass");
//        userDao.create(secondUser);
        User[] all = findAllUsers.findAll();
//        for (User u : all) {
//            System.out.println(u.getEmail());
//        }

    }






}