package week10.saviero.id.ac.umn;
import week10.saviero.id.ac.umn.model.*;
import java.util.Scanner;

public class Main {
    static Barang[] listBarang = new Barang[100];
    static Order[] listOrder = new Order[100];
    static int jumlahBarang = 0;
    static int jumlahOrder = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        listBarang[jumlahBarang++] = new Handphone("H01", "Samsung S9+", 14499000, 10, "Hitam");
        listBarang[jumlahBarang++] = new Handphone("H02", "iPhone X", 17999000, 10, "Putih");
        listBarang[jumlahBarang++] = new Voucher("V01", "Google Play", 20000, 100, 0.1);

        int pilih;
        do {
            System.out.println("------------ Menu Toko Voucher & HP ------------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1 -> pesanBarang();
                case 2 -> lihatPesanan();
                case 3 -> barangBaru();
                case 0 -> System.out.println("Terima kasih!");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 0);
    }

    static void pesanBarang() {
        System.out.println("Daftar Barang:");
        for (int i = 0; i < jumlahBarang; i++) {
            Barang b = listBarang[i];
            System.out.println("ID : " + b.getId());
            System.out.println("Nama : " + b.getNama());
            System.out.println("Stok : " + b.getStok());
            System.out.printf("Harga : %,.0f\n", b.getHarga());

            if (b instanceof Handphone h) {
                System.out.println("Warna : " + h.getWarna());
            } else if (b instanceof Voucher v) {
                System.out.println("Harga + Pajak : " + v.getHargaJual());
            }
            System.out.println("-----------------------------------");
        }

        System.out.print("Masukkan ID Barang: ");
        String id = sc.nextLine();

        Barang b = null;
        for (int i = 0; i < jumlahBarang; i++) {
            if (listBarang[i].getId().equalsIgnoreCase(id)) {
                b = listBarang[i];
                break;
            }
        }

        if (b == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int jumlah = sc.nextInt();
        sc.nextLine();

        if (jumlah > b.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        double totalHarga;
        if (b instanceof Voucher v) {
            totalHarga = v.getHargaJual() * jumlah;
        } else {
            totalHarga = b.getHarga() * jumlah;
        }

        System.out.printf("Total harga: %,.0f\n", totalHarga);
        System.out.print("Masukkan jumlah uang: ");
        double uang = sc.nextDouble();
        sc.nextLine();

        if (uang < totalHarga) {
            System.out.println("Uang tidak mencukupi!");
            return;
        }

        b.minusStok(jumlah);

        if (b instanceof Handphone h) {
            listOrder[jumlahOrder++] = new Order("OH" + jumlahOrder, h, jumlah);
        } else if (b instanceof Voucher v) {
            listOrder[jumlahOrder++] = new Order("OV" + jumlahOrder, v, jumlah);
        }

        System.out.println("Pesanan berhasil!");
    }

    static void lihatPesanan() {
        System.out.println("Daftar Pesanan:");
        for (int i = 0; i < jumlahOrder; i++) {
            Order o = listOrder[i];
            System.out.println("ID : " + o.getId());
            if (o.getHandphone() != null) {
                System.out.println("Nama : " + o.getHandphone().getNama());
                System.out.println("Jumlah : " + o.getJumlah());
                System.out.printf("Total : %,.0f\n", o.getJumlah() * o.getHandphone().getHarga());
            } else {
                System.out.println("Nama : " + o.getVoucher().getNama());
                System.out.println("Jumlah : " + o.getJumlah());
                System.out.printf("Total : %,.0f\n", o.getJumlah() * o.getVoucher().getHargaJual());
            }
            System.out.println("-----------------------------------");
        }
    }

    static void barangBaru() {
        System.out.print("Tambah Voucher / Handphone (V/H): ");
        String jenis = sc.nextLine();

        if (jenis.equalsIgnoreCase("H")) {
            System.out.print("ID : ");
            String id = sc.nextLine();
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            System.out.print("Harga : ");
            double harga = sc.nextDouble();
            System.out.print("Stok : ");
            int stok = sc.nextInt();
            sc.nextLine();
            System.out.print("Warna : ");
            String warna = sc.nextLine();

            listBarang[jumlahBarang++] = new Handphone(id, nama, harga, stok, warna);
            System.out.println("Handphone berhasil ditambahkan!");

        } else if (jenis.equalsIgnoreCase("V")) {
            System.out.print("ID : ");
            String id = sc.nextLine();
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            System.out.print("Harga : ");
            double harga = sc.nextDouble();
            System.out.print("Stok : ");
            int stok = sc.nextInt();
            System.out.print("PPN : ");
            double pajak = sc.nextDouble();
            sc.nextLine();

            listBarang[jumlahBarang++] = new Voucher(id, nama, harga, stok, pajak);
            System.out.println("Voucher berhasil ditambahkan!");
        }
    }
}