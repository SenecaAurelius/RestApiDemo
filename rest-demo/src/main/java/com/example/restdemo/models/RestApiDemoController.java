package com.example.restdemo.models;

import com.example.restdemo.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
                new Coffee("Coffee 1"),
                new Coffee("Coffee 2"),
                new Coffee("Coffee 3"),
                new Coffee("Coffee 4")
                ));
    }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        for (Coffee c : coffees) {
            if(c.getId().equals(id)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id,
                                     @RequestBody Coffee coffee) {
        int coffeeIndex = -1;

        for(Coffee c : coffees){
            if (c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<Coffee>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}

//@RestController
//public class RestApiDemoController {
//    private List<Coffee> coffees = new ArrayList<>();
//
//    public RestApiDemoController() {
//        coffees.addAll(List.of(
//                new Coffee("Cafe Cereza"),
//                new Coffee("Cafe Ganador"),
//                new Coffee("Cafe Lareno"),
//                new Coffee("Cafe Tres Pontas")
//        ));
//    }
//
////    @RequestMapping(value = "/coffees", method = RequestMethod.GET)
////    Iterable<Coffee> getCoffees() {
////            return coffees;
////        }
////    }
//
//    @GetMapping("/coffees")
//    Iterable<Coffee> getCoffees() {
//        return coffees;
//    }
//
//    @GetMapping("/coffees/{id}")
//    Optional<Coffee> getCoffeeById(@PathVariable String id) {
//        for(Coffee c : coffees) {
//            if (c.getId().equals(id)){
//                return Optional.of(c);
//            }
//        }
//        return Optional.empty();
//    }
//
//    @PostMapping("/coffees")
//    Coffee postCoffee(@RequestBody Coffee coffee) {
//        coffees.add(coffee);
//        return coffee;
//    }
//
//    //Put request are used to update existing resources with known URIs.
//    @PutMapping("/coffees/{id}")
//    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
//        int coffeeIndex = -1;
//
//        for(Coffee c : coffees){
//            if (c.getId().equals(id)){
//                coffeeIndex = coffees.indexOf(c);
//                coffees.set(coffeeIndex, coffee);
//            }
//        }
//
//        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
//    }
//
//    //HTTP DELETE requests
//    @DeleteMapping("/coffees/{id}")
//    void deleteCoffee(@PathVariable String id){
//        coffees.removeIf(c -> c.getId().equals(id));
//    }
//
//}
