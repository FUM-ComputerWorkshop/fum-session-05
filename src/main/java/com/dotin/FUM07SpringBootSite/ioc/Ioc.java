package com.dotin.FUM07SpringBootSite.ioc;

public class Ioc {
    public static void main(String[] args) {
        Car carWithMichelin = new Car(new SportWheel(), new WindowDark());
        carWithMichelin.move();

        Car carWithBridgestone = new Car(new RegularWheel(), new WindowRegular());
        carWithBridgestone.move();
    }
}

interface Wheel {
    void rotate();
}

class SportWheel implements Wheel {
    @Override
    public void rotate() {
        System.out.println("Michelin wheel rotating...");
    }
}

class RegularWheel implements Wheel {
    @Override
    public void rotate() {
        System.out.println("Bridgestone wheel rotating...");
    }
}

interface Window {

}

class WindowDark implements Window {

}

class WindowRegular implements Window {

}


class Car {
    private Wheel wheel;
    private Window window;

    // Constructor Injection
    public Car(Wheel wheel, Window window) {
        this.wheel = wheel;
        this.window = window;
    }

    public void move() {
        wheel.rotate();
        System.out.println("Car is moving...");
    }
}



