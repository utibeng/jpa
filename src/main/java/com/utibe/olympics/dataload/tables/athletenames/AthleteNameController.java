package com.utibe.olympics.dataload.tables.athletenames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AthleteNameController {

    @Autowired
    private AthleteNamesRepository athleteNamesRepository;

    @GetMapping("/athletename")
    public ResponseEntity <AthleteNames> test1(){
        AthleteNames athleteNames1 = new AthleteNames();
        athleteNames1.setID(1);
        athleteNames1.setName("Effiong");

        AthleteNames athleteNames2 = new AthleteNames();
        athleteNames2.setID(2);
        athleteNames2.setName("Utibe Emile");

        athleteNamesRepository.saveAll(List.of(athleteNames1, athleteNames2));


        return new ResponseEntity<>(athleteNames1, HttpStatus.OK);
    }



    //return new ResponseEntity<>(HttpStatus.OK);
}
