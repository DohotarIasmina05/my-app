package com.mycompany.app.Services;
import com.mycompany.app.Exceptions.InvalidLoginException;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import com.mycompany.app.Exceptions.*;
import com.mycompany.app.User;



public class UserService {
    private static ObjectRepository<User> userRepository;
    public static void initDatabase() {
        Nitrite db = Nitrite.builder()
                .filePath("VirtualLibrary.db")
                .openOrCreate("user1", "password1");
        userRepository = db.getRepository(User.class);

    }
    public static User login(String username, String password) throws InvalidLoginException{
        User crt;

        crt = attemptLogin(username, password);

        if(crt == null){
            throw new InvalidLoginException();
        }

        return crt;
    }
    public static void addUser(String username, String password, String role) {
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }
    public static User attemptLogin(String username, String password){
        for (User user : userRepository.find()) {
            if(Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword())){
                return user;
            }
        }

        return null;
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