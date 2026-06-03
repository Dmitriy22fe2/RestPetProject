package org.example.restpetproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sensor_name")
    private String sensorName;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor() {}

    public Sensor(int id, String sensorName) {
        this.id = id;
        this.sensorName = sensorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
