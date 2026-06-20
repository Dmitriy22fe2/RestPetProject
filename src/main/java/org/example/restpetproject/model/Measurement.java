package org.example.restpetproject.model;

import jakarta.persistence.*;


@Entity
@Table(name = "sensors_data")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "is_raining")
    private boolean isRaining;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "sensor_name")
    private Sensor sensor;

    public Measurement() {}

    public Measurement(int id, double temperature, boolean isRaining) {
        this.id = id;
        this.temperature = temperature;
        this.isRaining = isRaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean isRaining) {
        this.isRaining = isRaining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
