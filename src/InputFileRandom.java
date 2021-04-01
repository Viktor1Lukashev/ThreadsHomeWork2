import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

//в этом потоке мы работаем с файлом куда будем зыписывать случайные числа
public class InputFileRandom implements  Runnable{
    private BufferedWriter wr;
    private Random rand;

    //конструктор в который передаем путь до нашего файла(или создаем его)
    public InputFileRandom(String path) throws IOException {
        FileWriter writestr = new FileWriter(path);
        wr = new BufferedWriter(writestr);
        rand = new Random();

    }
    @Override
    //в методе run мы генерируем случаейное число и записываем в файл через пробел
    public void run() {
        synchronized (wr) {
                try {
                    for (int i = 0; i < 10; i++) {
                        int num = rand.nextInt(13);
                        wr.write(num+" ");
                     }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        wr.close();
                        System.out.println("первый поток отработал и закрылся");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

