package org.ls;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api")
public class AlticciSequence {

    @POST                                   //Type of the request
    @Path("/alticci/{value}")               //Url for the endpoint
    @Consumes(MediaType.TEXT_PLAIN)         //Receives parameters in TEXT_PLAIN format
    @Produces(MediaType.TEXT_PLAIN)         //Produces parameters in TEXT_PLAIN format
    @APIResponse(responseCode = "200")      //Successful request response
    @APIResponse(responseCode = "400", description = "Value must be equal or greater than 0") //Bad request response
    public Response calc_request(@PathParam("value") int value) {
        

        //Checks if received value is valid, returns 400-Bad Request if not
        if(value < 0){
            return Response.status(400).entity("Value must be equal or greater than 0").build();
        } 

        //Uses auxiliary method to calculate the sequence
        long seq_value = calc_alticci_seq(value);
        return Response.ok(seq_value).build();
    }

    @CacheResult(cacheName = "seq-cache")   //Line creates a cache called "seq-cache" which stores the results of the method to improve performance
    public long calc_alticci_seq(int value) {
        
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
}
