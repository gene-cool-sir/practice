package com.example.practicejavase.computer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 * 数据按照一定格式写出,按照一定格式读入
 */
public class DataInputStreamTest {

    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.setAge(20);
        person.setUsername("gene");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(bos);
        // 写入2个字节,并写入name的字符数据
        stream.writeUTF(person.getUsername());
        // 写入4个字节,并写入name的字符数据
        stream.writeInt(person.getAge());
        byte[] bytes = bos.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        // 按照写入的顺序读取字符数据,它首先从输入流中读取两个字节，这两个字节表示了后续字符串的长度。然后，它根据长度读取相应数量的字节，并将这些字节解码为字符串。在示例代码中，name变量将接收读取到的字符串数据。
        String i1 = dataInputStream.readUTF();
        // 用于读取整数数据。它从输入流中读取4个字节，并将这些字节解码为一个整数值
        int i = dataInputStream.readInt();

        System.out.println(i + i1 );
    }
}
