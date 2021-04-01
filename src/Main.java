import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу в котором будут рандомные числа:");
        String pathIn = sc.nextLine();
        System.out.println("Введите путь к выходному файлу с простыми числами из первого файла: ");
        String pathOut = sc.nextLine();
        System.out.println("Введите путь к выходному файлу с факториалами из первого файла: ");
        String pathOutForFactorials = sc.nextLine();
        //объект для заполнения файла случайными числами
        InputFileRandom in = new InputFileRandom(pathIn);
        //объект для вычисления факториалов чисел и записи их в файл
        NumbersFactorial factorials = new NumbersFactorial(pathIn,pathOutForFactorials);
        //объект для нахождения простых чисел в исходном файле и записи их в новый файл
        FileOutThread out = new FileOutThread(pathIn,pathOut);
        //создаем потоки
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        Thread t3 = new Thread(factorials);
        //запуск первого потока и ожидание его выполнения (заполнение файла)
        t1.start();
        t1.join();
        //запуск параллельных потоков для работы с заполненным файлом
        t2.start();
        t3.start();

    }
}
