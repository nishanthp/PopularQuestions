package main

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._

object TwitterData {
  
  // set up logging.
  def setUpLogging()={
    import org.apache.log4j.{Level, Logger};
    val logger = Logger.getRootLogger();
    logger.setLevel(Level.ERROR);
  }
  
  // set up twitter access on the system using java.lang.system.
  def setUpTwitterAccess()={
    import scala.io.Source;
    for (line <- Source.fromFile("../twitter.txt").getLines()){
      val fields = line.split(" ");
      if (fields.length == 2){
        System.setProperty("twitter4j.oath" + fields(0), fields(1));
      }
    }
  }
  
  def main(args: Array[String]){
    
    setUpTwitterAccess();
    //Processing done every 1 second.
    val sparkStreamContext = new StreamingContext("local[*]", "PopularTwitterHashtags", Seconds(1));
    setUpLogging();
    val twitterDStream = TwitterUtils.createStream(sparkStreamContext, None);
    
    val text_tweets = twitterDStream.map(x => x.getText());
    
    val words = text_tweets.flatMap(x => x.split(" "));
    
    val onlyHashTags = words.filter(x => x.startsWith("#"));
    
    val toCount = onlyHashTags.map(x => (x, 1));
    
    val hashTagWords = toCount.reduceByKeyAndWindow((x,y) => x + y, (x,y) => x - y, Seconds(300), Seconds(1));
    
    val popularHashTags = hashTagWords.transform(x => x.sortBy(x => x._2, false));
    
    popularHashTags.print();
    
    sparkStreamContext.checkpoint("/Users/nishanthprakash/Desktop/checkpoint/")
    sparkStreamContext.start();  
    sparkStreamContext.awaitTermination();
    
  }
}