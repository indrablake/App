package indrablake.com.directoryapps.data;

/**
 * Created by indrablake on 17/04/2018.
 */

public class KontakData {
    private String id;
    private String nama;
    private String bagian;
    private String alamat;
    private String notelp;

    public KontakData(){}

    public KontakData(String id, String nama, String bagian, String alamat, String notelp){
        this.id = id;
        this.nama = nama;
        this.bagian = bagian;
        this.alamat = alamat;
        this.notelp = notelp;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getBagian(){
        return bagian;
    }
    public void setBagian(String bagian){
        this.bagian = bagian;
    }
    public String getAlamat(){
        return alamat;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getNotelp(){
        return notelp;
    }
    public void setNotelp(String notelp){
        this.notelp = notelp;
    }
}
