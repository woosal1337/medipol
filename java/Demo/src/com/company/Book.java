package com.company;

public class Kitap {

    public String KitapISBN;
    public String kitapAdi;
    public int Yazar;
    public int Yayinci;


    public Kitap(String KitapISBN, int Yazar, String kitapAdi, int Yayinci) {
        this.KitapISBN = KitapISBN;
        this.Yazar = Yazar;
        this.Yayinci = Yayinci;
        this.kitapAdi = kitapAdi;
    }

    public String getKitapISBN() {
        return KitapISBN;
    }

    public String getkitapAdi() {
        return kitapAdi;
    }

    public int getYazar() {
        return Yazar;
    }

    public int getYayinci() {
        return Yayinci;
    }

    // Setting the elements start from here:

    public void setKitapISBN(String KitapISBN) {
        this.KitapISBN = KitapISBN;
    }

    public void setkitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public void setYazar(int Yazar) {
        this.Yazar = Yazar;
    }

    public void setYayinci(int Yayinci) {
        this.Yayinci = Yayinci;
    }


    public String toString() {
        return
        "Kitap adı : " + kitapAdi + "\n" +
        "Kitap yazarı : " + Yazar + "\n" +
        "Yayıncı : " + Yayinci + "\n" +
        "ISBN : " + KitapISBN;

    }


}