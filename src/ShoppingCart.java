package src;
import java.util.*;

// urun bilgileri
class Urun {
    String ad;
    double fiyat;
    String kategori;

    public Urun(String ad, double fiyat, String kategori) {
        this.ad = ad;
        this.fiyat = fiyat;
        this.kategori = kategori;
    }
}

public class Sepet {
    private List<Urun> urunler = new ArrayList<>();
    public String kupon;
    public String kargo;
    public String sehir;

    public void urunEkle(Urun u) {
        urunler.add(u);
        System.out.println(u.ad.toLowerCase() + " sepete eklendi");
    }

    public double hesapla() {
        double toplam = 0;

        // vergi hesabi
        for (Urun u : urunler) {
            double vergi = 0;
            if (u.kategori.equals("elektronik")) {
                vergi = u.fiyat * 0.18;
            } else if (u.kategori.equals("gida")) {
                vergi = u.fiyat * 0.01;
            } else {
                vergi = u.fiyat * 0.08;
            }
            toplam += u.fiyat + vergi;
        }

        // indirim kismi
        if (kupon != null) {
            if (kupon.equals("yaz2026")) {
                toplam = toplam * 0.90;
            } else if (kupon.equals("merhaba")) {
                toplam = toplam - 50;
            }
        }

        // kargo ucreti
        double kargoUcreti = 0;
        if (kargo != null) {
            if (kargo.equals("standart")) {
                kargoUcreti = 20;
            } else if (kargo.equals("hizli")) {
                kargoUcreti = 50;
            }
        }

        // sehir kontrolu
        if (sehir != null && sehir.equalsIgnoreCase("istanbul") && toplam > 500) {
            kargoUcreti = 0;
        }

        return toplam + kargoUcreti;
    }

    public void yazdir() {
        System.out.println("fatura detayi");
        for (Urun u : urunler) {
            System.out.println(u.ad.toLowerCase() + " fiyati " + u.fiyat);
        }
        System.out.println("odenecek toplam " + hesapla());
    }

    public static void main(String[] args) {
        Sepet s = new Sepet();
        s.sehir = "istanbul";
        s.kupon = "yaz2026";
        s.kargo = "hizli";

        s.urunEkle(new Urun("laptop", 15000, "elektronik"));
        s.urunEkle(new Urun("tisort", 500, "giyim"));

        s.yazdir();
    }
}