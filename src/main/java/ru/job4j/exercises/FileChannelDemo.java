package ru.job4j.exercises;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class FileChannelDemo {
    public static void main(String[] args) {
        try {
            FileChannel channel = FileChannel.open(Paths.get("src/main/java/ru/job4j/exercises/CurDemo.java"));
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            while (map.hasRemaining()) {
                System.out.print((char) map.get());
            }
            CRC32 crc = new CRC32();
            map.rewind();
            while (map.hasRemaining()) {
                crc.update(map.get());
            }
            long checksum = crc.getValue();
            System.out.println(checksum);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
