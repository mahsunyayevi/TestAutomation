package tests;

import com.model.User;
import com.pages.*;
import com.runner.TestsRunner;
import com.shared.file.ExcelRead;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.data.tests.TestDescription.*;

public class TrendyolTests extends TestsRunner {


    @Test(enabled = true, description = TS0001Description)
    public void TS0001() throws IOException, InterruptedException {

        IndexPage indexPage = new IndexPage(getDriver(), testNo, getBrowserName());
        LoginPage loginPage = new LoginPage(getDriver(), testNo, getBrowserName());
        Urunler urunler = new Urunler(getDriver(), testNo, getBrowserName());
        UrunDetay urunDetay = new UrunDetay(getDriver(), testNo, getBrowserName());
        Sepet sepet = new Sepet(getDriver(), testNo, getBrowserName());
        ExcelRead excelRead = new ExcelRead();

        User user = excelRead.getUsersExcelRead();
        String userEmail = user.getEmail();
        String userSifre = user.getPassword();
        String urun = "Bilgisayar";
        String bosSepet = "Sepetinizde ürün bulunmamaktadır.";

        indexPage
                .anaSayfaEkranKontrol()
                .girisYapNavKontrol()
                .girisYapBtn();
        loginPage
                .emailDoldur(userEmail)
                .sifreDoldur(userSifre)
                .girisYapBtn();
        indexPage
                .accountNav()
                .kullaniciKontrol(userEmail)
                .aramaYap(urun);
        urunler
                .urunSec();
        urunDetay
                .urunBilgisiDosyaYaz()
                .urunSepetEkle();

        String urunFiyat = urunDetay.urunFiyatAl();
        String urunAdi = urunDetay.urunAdiAl();

        indexPage
                .sepetim();
        sepet
                .UrunVeFiyatKontrol(urunAdi, urunFiyat)
                .urunSayisArttir(urun, 2)
                .urunSil(urun)
                .urunSilOnay(urun)
                .silinenUrunKontrol(bosSepet);
    }


}
