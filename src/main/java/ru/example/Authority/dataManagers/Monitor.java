package ru.example.Authority.dataManagers;

public class Monitor extends Data{
    protected int diagonal;

    public Monitor(int id, int seriesNumber, String maker, int price, int counter, int diagonal){
        super(id, seriesNumber, maker, price, counter);
        this.diagonal = diagonal;
    }

    public Monitor(int seriesNumber, String maker, int price, int counter, int diagonal) {
        super(seriesNumber, maker, price, counter);
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", seriesNumber=" + seriesNumber +
                ", price=" + price +
                ", counter=" + counter +
                ", maker='" + maker + '\'' +
                ", diagonal=" + diagonal +
                '}';
    }

    public int getDiagonal() {
        return diagonal;
    }
}
