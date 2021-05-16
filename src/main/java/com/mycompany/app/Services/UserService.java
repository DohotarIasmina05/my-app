package com.mycompany.app.Services;

import com.mycompany.app.Exceptions.*;
import com.mycompany.app.Model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static com.mycompany.app.Services.FileSystemSerivce.getPathToFile;

public class UserService {

    public static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("UserDataBase.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException,EmptyUsernameFieldException, EmptyPasswordFieldException {
        checkEmptyFields(username, password);

        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                throw new UsernameAlreadyExistsException(username);
            }
        }
    }


    private static void checkEmptyFields(String username, String password) throws EmptyUsernameFieldException, EmptyPasswordFieldException {
        if (username == "") {
            throw new EmptyUsernameFieldException();
        } else {
            if (password == "") {
                throw new EmptyPasswordFieldException();

            }
        }
    }



    private static void checkEmptyFieldsLogIn(String username, String password) throws EmptyUsernameFieldException, EmptyPasswordFieldException {
        if (username == "") {
            throw new EmptyUsernameFieldException();
        } else if (password == "") {
            throw new EmptyPasswordFieldException();
        }
    }

    public static void checkUserCredentials(String username, String password, String role) throws EmptyUsernameFieldException, EmptyPasswordFieldException, IncorrectPassoword, IncorrectUsername, WrongRole {
        int sw1 = 1, sw2 = 1, sw3 = 1;
        String encryptedPassword = UserService.encodePassword(username, password);

        checkEmptyFieldsLogIn(username, password);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                sw1 = 0;
                if (Objects.equals(encryptedPassword, user.getPassword())) {
                    sw2 = 0;
                    if (Objects.equals(role, user.getRole())) {
                        sw3 = 0;
                    }
                }
            }
        }

        if (sw1 == 1) {
            throw new IncorrectUsername();
        }
        if (sw2 == 1) {
            throw new IncorrectPassoword();
        }
        if (sw3 == 1) {
            throw new WrongRole();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}

