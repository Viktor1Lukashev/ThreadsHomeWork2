import java.io.*;
//в этом потоке мы будем записывать в новый файл факториалы чисел из первого потока
public class NumbersFactorial implements Runnable{
    private BufferedReader reader;
    private BufferedWriter _writer;
    private String pathToInputFile;
    private  int result;
    //конструктор в который мы передаем путь до файла с числами и файла куда будем записывать факториалы
    public NumbersFactorial(String pathIn, String pathOut) throws IOException {
        FileReader readstr = new FileReader(pathIn);
        FileWriter wr = new FileWriter(pathOut);
        reader = new BufferedReader(readstr);
        _writer = new BufferedWriter(wr);
        pathToInputFile = pathIn;
        result = 0;
    }
    @Override
    public void run() {
        synchronized (reader) {
            try {
                String line = "";
                _writer.write("факториалы чисел из файла " + pathToInputFile);
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
                    System.out.println("третий поток отработал и закрылся");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //локальный метод input вызывается из метода run
    private void input(Integer i) throws IOException {
        result = i;
            for(int j=i;j>1;j--){
                result *= j-1;
            }
            String s = Integer.toString(result);
            _writer.write( "факториал числа "+ i +" = " + s);
            _writer.newLine();
        }
    }

