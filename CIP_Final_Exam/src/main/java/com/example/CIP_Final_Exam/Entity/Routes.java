package com.example.CIP_Final_Exam.Entity;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Routes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int number;
    private String originStop;
    private String destinationStop;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "routes")
    private List<Trips> trips;


    public Routes(){}

    public Routes(int number, String originStop, String destinationStop){
        this.number=number;
        this.originStop=originStop;
        this.destinationStop=destinationStop;
    }
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number=number;
    }

    public String getOriginStop(){
        return originStop;
    }

    public void setOriginStop(String originStop){
        this.originStop=originStop;
    }

    public String getDestinationStop(){
        return destinationStop;
    }

    public void setDestinationStop(String destinationStop){
        this.destinationStop=destinationStop;
    }
}
