package com.tripco.t03.server;

import com.tripco.t03.planner.Calculate;
import com.tripco.t03.planner.LineDistance;
import com.tripco.t03.planner.Match;
import com.tripco.t03.planner.Plan;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

/**
 * A simple micro-server for the web.  Just what we need, nothing more.
 */
public class MicroServer {

    private int    port;
    private String name;
    private String result;
    
    /**
     * Creates a micro-server to load static files and provide REST APIs.
     * @param port Which port to start the server on
     * @param name Name of the server
     */
    MicroServer(int port, String name) {
        this.port = port;
        this.name = name;

        port(port);

        // serve the static files: index.html and bundle.js
        String path = "/public/";
        Spark.staticFileLocation(path);
        get("/", (req, res) -> {res.redirect("index.html");
            return null;
        });

        // register all micro-services and the function that services them.
        // start with HTTP GET
        get("/about", this::about);
        get("/echo", this::echo);
        get("/hello/:name", this::hello);
        get("/team", this::team);
        get("/map", this::map);
        // client is sending data, so a HTTP POST is used instead of a GET
        get("/config", this::config);
        post("/plan", this::plan);
        post("/distance", this::distance);
        post("/search", this::search);
        
    }

    /**
     * A REST API that describes the server.
     * @param request Client request.
     * @param response Server response.
     * @return Info about this server
     */
    private String about(Request request, Response response) {
        response.type("text/html");
        response.header("Access-Control-Allow-Origin", "*");
        try{
            result = "<html><head></head><body><h1>"+name+" Micro-server on port "
                    +port+"</h1></body></html>";
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * A REST API that returns the current server configuration.
     * @param request Client request.
     * @param response Server response.
     * @return What this server can do
     */
    private String config(Request request, Response response) {
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
        try{
            result = Config.getConfig();
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    private String map(Request request, Response response) {
        String result;
        response.type("text/plain");
        response.header("Access-Control-Allow-Origin", "*");
        try{
            result = LineDistance.getDefaultMap();
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * A REST API that echos the client request.
     * @param request Client request.
     * @param response Server response.
     * @return Echoes back the request to the client
     */
    private String echo(Request request, Response response) {
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
        try{
            result = HTTP.echoRequest(request);
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }
    
    /**
     * A REST API demonstrating the use of a parameter.
     * @param request Client request.
     * @param response Server response.
     * @return A message saying hello.
     */
    private String hello(Request request, Response response) {
        response.type("text/html");
        response.header("Access-Control-Allow-Origin", "*");
        try{
            result =Greeting.html(request.params(":name"));
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * A REST API that returns the team information associated with the server.
     * @param request Client request.
     * @param response Server response.
     * @return The team name
     */
    private String team(Request request, Response response) {
        response.type("text/plain");
        response.header("Access-Control-Allow-Origin", "*");
        try{
            result = name;
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * A REST API to support trip planning.
     * @param request Client request.
     * @param response Server response.
     * @return The planned trip
     */
    private String plan(Request request, Response response) {
        setAppJsonResponse(response);
        try{
            result = new Plan(request).getTrip();
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * A REST API for distance.
     * @param request Request Object.
     * @param response Response Object.
     * @return String.
     */
    private String distance(Request request, Response response) {
        setAppJsonResponse(response);
        try{
            result = new Calculate(request).getDistance();
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * A REST API for search.
     * @param request should be {a single string}.
     * @param response no idea what is this.
     * @return should be a list of places.
     */
    private String search(Request request, Response response) {
        setAppJsonResponse(response);
        try{
            result = new Match(request).getMatch();
        }catch(Exception err){
            result = "{}";
        }
        return result;
    }

    /**
     * Helper method to assign application/json to response.
     * Assigns Access-Control- Allow-Origin to response header.
     * @param response Response.
     */
    private void setAppJsonResponse(Response response){
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
    }
}
