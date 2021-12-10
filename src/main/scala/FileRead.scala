import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.types.{DataType, DataTypes, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.io.File

object FileRead extends App {
  def getSchema():StructType = {
    val simpleSchema: StructType = StructType(Array(
      StructField("first_col",StringType,nullable = false),
      StructField("Type",StringType,nullable = false),
      StructField("Customer_Name",StringType,nullable = false),
      StructField("Customer_Id",StringType,false),
      StructField("Open_Date",StringType, false),
      StructField("Last_Consulted_Date", StringType, true),
      StructField("Vaccination_Id", StringType, false),
      StructField("Dr_Name", StringType, false),
      StructField("State", StringType, false),
      StructField("Country", StringType, false),
      StructField("DOB", StringType, false),
      StructField("Is_Active", StringType, true)
    ))
    simpleSchema
  }


  def readFile(fileName: String, filePath: String)(implicit spark: SparkSession): Either[Exception, DataFrame] = {
    if (new File(s"$filePath/$fileName").exists()){
      Right(spark.read.option("header", "true")
        .option("delimiter", "|")
        .schema(getSchema())
        .csv(s"$filePath/$fileName")
      )
    }else{
      throw new RuntimeException("FileNotFound")
    }
  }
}
