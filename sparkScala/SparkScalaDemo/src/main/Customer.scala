package main

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import javax.script.ScriptContext

object Customer {
  
  def main(args: Array[String]){
    
    Logger.getLogger("org").setLevel(Level.ERROR);
    
    val sc = new SparkContext("local[*]","CustomerSpending");
    
    val datardd = sc.textFile("../customer-orders.csv");
    
    val reqData = datardd.map(parseInput).reduceByKey((x, y) => x+y).map(x => (x._2, x._1)).sortByKey();
    
    val results = reqData.collect();
    
    results.foreach(println)
    
  }
  
  def parseInput (line : String) ={
    val fields = line.split(",");
    val customer_id = fields(0).toInt;
    val amtSpent = fields(2).toFloat;
    (customer_id, amtSpent);
  }
}