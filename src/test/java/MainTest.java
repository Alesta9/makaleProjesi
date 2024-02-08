import org.example.Main;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void testBenzerlikHesapla() {
        String makale1 = "Birinci makale içeriği";
        String makale2 = "İkinci makale içeriği";
        String[] makale3Kelimeler = {"makale", "içeriği"};

        int benzerlik13 = Main.benzerlikHesapla(Main.top10Word(makale1), makale3Kelimeler);
        int benzerlik23 = Main.benzerlikHesapla(Main.top10Word(makale2), makale3Kelimeler);


        assertEquals(2, benzerlik23);
    }

    @Test
    public void testMainMethodOutput() {
        String input = "Makale 1\nMakale 2\nMakale 3\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[0]);

        String output = outputStream.toString().trim();


        String expectedOutput = "1. Yazarın Makalesini Girin: \r\n" +
                "2. Yazarın Makalesini Girin: \r\n" +
                "3. Makaleyi Girin: \r\n" +
                "3. Makale 2 yazara da eşit oranda benzemektedir."; // Burada varsayılan bir çıktı veriyorum

        assertEquals(expectedOutput, output);
    }
}