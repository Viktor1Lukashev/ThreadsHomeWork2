import java.io.*;
import java.nio.Buffer;
import java.util.List;
import java.util.Scanner;

//в этом потоке мы будем записывть в новый файл простые числа из исходного файла
public class FileOutThread implements Runnable {
    private BufferedReader reader;
    private BufferedWriter _writer;
    private String pathToInputFile;
    private int count;
    //конструктор с параметрами путей к 2м файлам
    FileOutThread(String pathIn, String pathOut) throws IOException {
        FileReader readstr = new FileReader(pathIn);
        FileWriter wr = new FileWriter(pathOut);
        reader = new BufferedReader(readstr);
        _writer = new BufferedWriter(wr);
        pathToInputFile = pathIn;
        count = 0;

    }
    private void CountIsNull() throws IOException {
        if (count==0) {
            _writer.write("в файле "+pathToInputFile+" отсутствуют простые числа!");
        }
    }
    @Override
    public void run() {
        synchronized (reader) {
            try {
                String line = "";
                _writer.write("Все простые числа из файла " + pathToInputFile);
                _writer.newLine();
                while ((line = reader.readLine())!=null) {
                    String[] arr = line.split(" ");
                    for(String s:arr) {
                         input(Integer.valueOf(s));
                        }
                    }

                } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    _writer.close();
                     reader.close();
                    System.out.println("Второй поток отработал и закрылся");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //вспомогательный метод который вызывается из метода run
    private void input(Integer i) throws IOException {

        if (i%2==0) {
            String s = Integer.toString(i.intValue());
            _writer.write(s + " ");
            count++;
        }
    }
}
