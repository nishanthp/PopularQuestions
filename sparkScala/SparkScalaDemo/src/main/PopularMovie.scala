package main

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import javax.script.ScriptContext
import scala.io.Source
import scala.io.Codec
import java.nio.charset.CodingErrorAction
import shapeless.ops.tuple.Length

object PopularMovie {
  def movieNames() : Map[Int, String] ={
    implicit val codec = Codec("UTF-8");
    codec.onMalformedInput(CodingErrorAction.REPLACE);
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE);
    
    var movieMap:Map[Int, String] = Map();
    
    val lines = Source.fromFile("../ml-100k/u.item").getLines();
    
    for (line <- lines) {
      var fields = line.split('|');
      if (fields.length > 1) {
      movieMap += (fields(0).toInt -> fields(1));
    }
      
      
    }
    
     return movieMap;
  }
  
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR);
    
    val sc = new SparkContext("local[*]", "popularMovie");
    
    val movieMap = sc.broadcast(movieNames);
    
    val movieListrdd = sc.textFile("../ml-100k/u.data");
    
    val movieIdsrdd = movieListrdd.map(x => (x.split("\t")(1).toInt, 1));
    
    val popularMovies = movieIdsrdd.reduceByKey((x,y) => x + y).map(x => (x._2, x._1)).sortByKey();
    
    val popularByName = popularMovies.map(x => (movieMap.value(x._2), x._1));
    
     popularByName.collect().foreach(println); 
    
  }
}