package io.github.zhengyhn.practice.enum_serialization;

import java.io.*;

public class EnumSerialization {
    public enum Color {
        RED, GREEN, BLUE
    }

    public static void main(String[] args) throws Exception {

        System.out.println(deserialize(serialize(Color.GREEN), Color.class));
    }

    private static <T> T deserialize(byte[] data, Class<T> cls) throws IOException, ClassNotFoundException {

        try (final ByteArrayInputStream stream = new ByteArrayInputStream(data);
             final ObjectInputStream reader = new ObjectInputStream(stream)) {

            return cls.cast(reader.readObject());
        }
    }

    private static byte[] serialize(Serializable obj) throws IOException {

        final ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {

            try (final ObjectOutputStream writer = new ObjectOutputStream(stream)) {

                writer.writeObject(obj);
            }

        } finally {

            stream.close();
        }

        return stream.toByteArray();
    }
}
