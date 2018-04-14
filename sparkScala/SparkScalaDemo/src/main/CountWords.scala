package main

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import javax.script.ScriptContext

object CountWords {
  
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR);
  
  val stopWords = List("on", "for", "it", "in", "to", "but", "or", "this");
  
  val sc = new SparkContext("local[*]", "WordCount");
  
  val lines = sc.textFile("../book.txt");
  
  val wordsrdd = lines.flatMap(x => x.split("\\W+"));
  
  // Not filtering. TODO. 
  val withoutStopWords = wordsrdd.filter(x => !stopWords.contains());
  
  val lowercase = withoutStopWords.map(x => x.toLowerCase());
  
  
  val popularWords = lowercase.map(x => (x, 1)).reduceByKey((x,y) => x+y);
  
  val flip = popularWords.map(x => (x._2, x._1)).sortByKey();
  
  //action. This is an rdd.
  val results = flip.collect();
  
  
  
  // action. This is a scala map.
  //val counter = lowercase.countByValue();
  
  results.sorted.foreach(println);
  }
}