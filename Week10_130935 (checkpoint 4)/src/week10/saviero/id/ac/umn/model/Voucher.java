package week10.saviero.id.ac.umn.model;

public class Voucher extends Barang {
    private double pajak;
    public static int total = 0;

    public Voucher(String id, String nama, double harga, int stok, double pajak) {
        super(id, nama, harga, stok);
        this.pajak = pajak;
        total++;
    }

    public double getPajak() {
        return pajak;
    }

    public double getHargaJual() {
        return harga + (harga * pajak);
    }
}