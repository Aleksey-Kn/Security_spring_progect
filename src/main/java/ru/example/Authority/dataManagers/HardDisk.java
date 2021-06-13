package ru.example.Authority.dataManagers;

public class HardDisk extends Data {
    protected int volume;

    public HardDisk(int id, int seriesNumber, String maker, int price, int counter, int volume){
        super(id, seriesNumber, maker, price, counter);
        this.volume = volume;
    }

    public HardDisk(int seriesNumber, String maker, int price, int counter, int volume) {
        super(seriesNumber, maker, price, counter);
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "HardDisk{" +
                "id=" + id +
                ", seriesNumber=" + seriesNumber +
                ", price=" + price +
                ", counter=" + counter +
                ", maker='" + maker + '\'' +
                ", volume=" + volume +
                '}';
    }

    public int getVolume() {
        return volume;
    }
}
