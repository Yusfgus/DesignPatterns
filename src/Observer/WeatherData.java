package Observer;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

interface Observer {
    void update();
}

//interface DisplayElement {
//    void display();
//}

abstract class DisplayElement implements Observer {
    protected WeatherData weatherData;

    public DisplayElement(WeatherData weatherData){
        this.weatherData = weatherData;
        this.subscribe();
    }
    abstract void display();

    void subscribe(){
        weatherData.registerObserver(this);
    }

    void unSubscribe(){
        weatherData.removeObserver(this);
    }
}

class CurrentConditionsDisplay extends DisplayElement{
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        super(weatherData);
    }

    public void update(){
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        this.display();
    }

    @Override
    public void display(){
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

class StatisticsDisplay extends DisplayElement {
    private float maxTemp;
    private float minTemp;
    private float avgTemp;

    public StatisticsDisplay(WeatherData weatherData) {
        super(weatherData);
        maxTemp = Float.MIN_VALUE;
        minTemp = Float.MAX_VALUE;
    }

    public void update(){
        float temperature = weatherData.getTemperature();
        if(temperature > maxTemp) maxTemp = temperature;
        if(temperature < minTemp) minTemp = temperature;
        avgTemp = (maxTemp + minTemp)/2;
        this.display();
    }

    @Override
    public void display(){
        System.out.println("Avg/Max/Min temperature = " + avgTemp + "/" + maxTemp + "/" + minTemp);
    }
}

//===================================================================================================================

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}

public class WeatherData implements Subject{
    List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<Observer>();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void measurementsChanged(){
        this.notifyObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
