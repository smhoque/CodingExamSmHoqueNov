package KeyWord;

/**
 * Created by riponctg on 12/15/2016.
 */
public class CarTest {
    public static void main(String[] args) {
        Car Toyata = new Car();
        System.out.println(Toyata.CarTransmission);
        SuperCar supercar = new SuperCar();
        supercar.start();
        supercar.stop();
        supercar.CarTransmission = 2;
        Car.CarSteering();
        System.out.println(Car.CarEngine);
    }

}
