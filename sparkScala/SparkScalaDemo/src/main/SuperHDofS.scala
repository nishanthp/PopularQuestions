package main

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._
import breeze.linalg.split
import scala.collection.mutable.ListBuffer

object SuperHDofS {
  
  def parseNames(line: String):Option[(VertexId, String)]={
    val fields = line.split("\"");
    if(fields.length > 1){
      if(fields(0).trim().toLong < 6487){
        return Some (fields(0).trim().toLong, fields(1).toString());
      }
    }
    return None;
  }
  
  def parseConnections(line: String):List[Edge[Int]]={
    val fields = line.split(" ");
    val origin = fields(0);
    var buffer = new ListBuffer[Edge[Int]]();
    for(x <- 1 to fields.length-1) {
      buffer += Edge(origin.toLong, fields(x).toInt, 0);
    }
    return buffer.toList;
  }
  
 def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR);
    
    val sc = new SparkContext("local[*]", "DegreeOfSepration");
    
    
    val names = sc.textFile("../Marvel-names.txt");
    
    val vertices = names.flatMap(x => parseNames(x));
    
    val connections = sc.textFile("../Marvel-graph.txt");
    
    val edges = connections.flatMap(parseConnections);
    
    val initialGraph = Graph(vertices, edges).cache();
    
    // What is a degree of a map.
    //initialGraph.degrees.collect().foreach(println)
    
    initialGraph.degrees.join(vertices).sortBy(x => x._2._1, false).collect().take(10).foreach(println);
  }
}