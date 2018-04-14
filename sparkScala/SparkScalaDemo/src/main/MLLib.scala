package main
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import javax.script.ScriptContext
import org.apache.spark.mllib.recommendation._
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec

object MLLib {
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
  
  //case class Rating(USERID: Int, MOVIEID: Int, Rating:Int, timestamp:Int)
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR);
    
    val sc = new SparkContext("local[*]", "ML");
    
    val dataFile = sc.textFile("../ml-100k/u.data");
    
    val structuredData = dataFile.map(x => x.split("\t")).map(x => Rating(x(0).toInt, x(1).toInt,x(2).toDouble)).cache();
    
    val nameDict = sc.broadcast(movieNames);
    
    val rank = 8;
    val numOfInteration = 20;
    
    // take the rdd and create a model.
    val model = ALS.train(structuredData, rank, numOfInteration);
    
    // recommend movie to which user. Input from commandline.
    val userID = args(0).toInt;
    println(userID)
    val filterUserData = structuredData.filter(x => x.user == userID).collect();
    
    // display results.
    
    for (movie <- filterUserData){
      println(nameDict.value(movie.product.toInt) + "rating " + movie.rating.toString());
    }
    
    // Apply the model to recommend top 10 movies to the user.
    val recommendations = model.recommendProducts(userID, 10);
    
    // print the recommendations.
    println("PRITING THE RECOMMENDATIONS")
    for(rating <- recommendations) {
      println(nameDict.value(rating.product.toInt) + "rating " + rating.rating.toString());
    }
  }
}