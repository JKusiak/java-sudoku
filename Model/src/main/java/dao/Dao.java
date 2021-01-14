package dao;

import exceptions.NoDataException;
import exceptions.NoSuchFileException;

import java.io.IOException;

public interface Dao<T> {
    T read() throws IOException, ClassNotFoundException, NoDataException, NoSuchFileException;

    void write(T obj) throws IOException, NoSuchFileException;
}
