package ru.example.Authority.dataManagers;

public class Notebook extends Data{
    protected int size;

    public Notebook(int id, int seriesNumber, String maker, int price, int counter, int size){
        super(id, seriesNumber, maker, price, counter);
        this.size = size;
    }

    public Notebook(int seriesNumber, String maker, int price, int counter, int size) {
        super(seriesNumber, maker, price, counter);
        this.size = size;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", seriesNumber=" + seriesNumber +
                ", price=" + price +
                ", counter=" + counter +
                ", maker='" + maker + '\'' +
                ", size=" + size +
                '}';
    }

    public int getSize() {
        return size;
    }
}
