package week10.saviero.id.ac.umn.model;

public class Handphone extends Barang {
    private String warna;
    public static int total = 0;

    public Handphone(String id, String nama, double harga, int stok, String warna) {
        super(id, nama, harga, stok);
        this.warna = warna;
        total++;
    }

    public String getWarna() {
        return warna;
    }
}
