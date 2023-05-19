# Alticci-Sequence

## Description

This repository contains a Quarkus application that calculates the Alticci sequence for a given number. The sequence can be defined as the following:
- n=0 => a(0) = 0
- n=1 => a(1) = 1
- n=2 => a(2) = 1
- n>2 => a(n) = a(n-3) + a(n-2)

The application contains the endpoint **{...}/api/alticci/{value}** that can be used to calculate the sequence for the given value.
Example:
- http://localhost:8080/api/alticci/10

It also contains **{...}/docs** which opens an OpenAPI UI page where the previous endpoint can be tested.

## Algorithm

The sequence algorithm was implemented using recursion. 

    public int calc_alticci_seq(int value) {
        
        //Method implements a recursive method to calculate the sequence
        //Stopping conditions for the algorithm
        if(value == 0) {
            return 0;
        } else if(value == 1 || value == 2) {
            return 1;
        }

        //adds the results of the intermediate calculations and calls the next iteration
        return calc_alticci_seq(value-3) + calc_alticci_seq(value-2);
    }
   
## Caching

This request makes use of the caching functions of quarkus in order to improve the performance of the requests.

## How to run

The application is dockerized, so in order to run the application simply navigate to the root directory of project (Alticci-Sequence\alticci-sequence) and run the followind command:
-  docker run -i --rm --name alticci-seq -p 8080:8080 quarkus/alticci-sequence-jvm
