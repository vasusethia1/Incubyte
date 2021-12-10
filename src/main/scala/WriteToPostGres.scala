import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
object WriteToPostGres {
  def writeToPostGres(df: DataFrame)(implicit  spark: SparkSession): Unit = {
    if(df.count() > 0) {
     df.write
        .format("jdbc")
        .option("driver","org.postgresql.Driver")
        .option("url","jdbc:postgresql://localhost:5432/customers")
        .option("user","hosadmin")
        .option("password","")
        .option("dbtable","customers_info")
        .mode(SaveMode.Append)
       .save()
    }
  }
}
