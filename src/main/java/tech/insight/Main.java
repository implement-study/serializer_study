package tech.insight;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("张三");
        user.setAge(18);

        byte[] userBytes = serializeUser(user);
        File file = new File("user.ss");
        Files.write(file.toPath(), userBytes);
        System.out.println(file.length());

        byte[] readAllBytes = Files.readAllBytes(file.toPath());
        User deUser = deserialize(readAllBytes);
        System.out.println(deUser);
    }

    private static byte[] serializeUser(User user) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(user);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static User deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(arrayInputStream)) {
            return (User) objectInputStream.readObject();
        }

    }

}
