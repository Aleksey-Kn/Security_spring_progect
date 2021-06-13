package ru.example.Authority.dataManagers;

public class Desktop extends Data {
    protected String formFactor;

    public Desktop(int id, int seriesNumber, String maker, int price, int counter, String formFactor){
        super(id, seriesNumber, maker, price, counter);
        this.formFactor = formFactor;
    }

    public Desktop(int seriesNumber, String maker, int price, int counter, String formFactor) {
        super(seriesNumber, maker, price, counter);
        this.formFactor = formFactor;
    }

    @Override
    public String toString() {
        return "Desktop{" +
                "id=" + id +
                ", seriesNumber=" + seriesNumber +
                ", price=" + price +
                ", counter=" + counter +
                ", maker='" + maker + '\'' +
                ", formFactor='" + formFactor + '\'' +
                '}';
    }

    public String getFormFactor() {
        return formFactor;
    }
}
