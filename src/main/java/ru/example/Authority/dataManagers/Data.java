package ru.example.Authority.dataManagers;

abstract public class Data {
    protected int id, seriesNumber, price, counter;
    protected String maker;

    protected Data(int id, int seriesNumber, String maker, int price, int counter){
        this.id = id;
        this.seriesNumber = seriesNumber;
        this.maker = maker;
        this.price = price;
        this.counter = counter;
    }

    protected Data(int seriesNumber, String maker, int price, int counter){
        id = 0;
        this.seriesNumber = seriesNumber;
        this.maker = maker;
        this.price = price;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public int getPrice() {
        return price;
    }

    public String getMaker() {
        return maker;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public int getId() {
        return id;
    }
}
