package tests;
import main.java.Grep;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class GrepTest {

//    PrintStream consoleStream = System.out;
    public final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//динамический массив
//    PrintStream outputStream = new PrintStream(out);
//    System.setOut(outputStream);
//    System.setOut(consoleStream);

    void assertFileContent(String name, String expected) {
        File file = new File(String.valueOf(outputStream));
        String content = file.toString();
        assertEquals(expected, content);
}
/*Тест создан наподобие в тестами KotlinAsFirst2021 в Task 7
  В тестах используется литерал "\r" в связи с тем, что строки в текстовом файле заканчиваются с
  помощью CR(возврат к началу строки)
*/
    @Test
    public void TestIg() throws IOException {
        Grep grep = new Grep(true, false, false);
        grep.grep("клюет", "files/Text.txt");
        assertFileContent("temp.txt","""
                4 Кровавую пищу клюет под окном,\r
                Клюет, и бросает, и смотрит в окно,\r
                """);
        new File("temp.txt").delete();
        //не уверен, что удаление файла после теста работает(((
    }

    @Test
    public void TestIgAndR() throws IOException {
        Grep grep = new Grep(true, true, false);
        grep.grep("[с+]", "files/Text.txt");
        assertFileContent("temp.txt","""
                1 Сижу за решеткой в темнице сырой.\r
                2 Вскормленный в неволе орел молодой,\r
                3 Мой грустный товарищ, махая крылом,\r
                Клюет, и бросает, и смотрит в окно,\r
                Как будто со мною задумал одно;\r
                Зовет меня взглядом и криком своим\r
                Туда, где синеют морские края,\r
                """);
        new File("temp.txt").delete();
    }

    @Test
    public void TestFull() throws IOException {
        Grep grep = new Grep(true, true, true);
        grep.grep("[с+]", "files/Text.txt");
        assertFileContent("temp.txt","""
                4 Кровавую пищу клюет под окном,\r
                \r
                И вымолвить хочет: "Давай улетим!\r
                \r
                Мы вольные птицы; пора, брат, пора!\r
                Туда, где за тучей белеет гора,\r
                Туда, где гуляем лишь ветер... да я!.."\r
                """);
        new File("temp.txt").delete();
    }

    @Test
    public void TestR() throws IOException {
        Grep grep = new Grep(false, true, false);
        grep.grep("[0-9]", "files/Text.txt");
        assertFileContent("temp.txt","""
                1 Сижу за решеткой в темнице сырой.\r
                2 Вскормленный в неволе орел молодой,\r
                3 Мой грустный товарищ, махая крылом,\r
                4 Кровавую пищу клюет под окном,\r
                """);
        new File("temp.txt").delete();
    }

    @Before
    public void Streams() {
        System.setOut(new PrintStream(outputStream));
    }

}