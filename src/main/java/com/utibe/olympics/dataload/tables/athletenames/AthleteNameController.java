package com.utibe.olympics.dataload.tables.athletenames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/olympics")
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

    @PostMapping(path = "/athletename/create",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <AthleteNames> addAnAthlete(@RequestParam(value="id") String id,
                                                      @RequestParam(value="name") String name ){
        AthleteNames athleteNames = new AthleteNames();
        athleteNames.setID(Integer.parseInt(id));
        athleteNames.setName(name);

        athleteNamesRepository.saveAll(List.of(athleteNames));

        return new ResponseEntity<>(athleteNames, HttpStatus.CREATED);
    }


    //return new ResponseEntity<>(HttpStatus.OK);
}
