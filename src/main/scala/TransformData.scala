import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, to_date}
import org.apache.spark.sql.types.DateType

object TransformData {

  def columnsRenamed(dataFrame: DataFrame): DataFrame = {
    dataFrame.withColumnRenamed("Customer_Name", "name")
      .withColumnRenamed("Customer_Id", "cust_i")
      .withColumnRenamed("Open_Date","open_dt")
      .withColumnRenamed("Last_Consulted_Date", "consul_dt")
      .withColumnRenamed("Vaccination_Id", "vac_id")
      .withColumnRenamed("Dr_Name", "dr_name")
      .withColumnRenamed("State", "state")
      .withColumnRenamed("Country", "country")
      .withColumnRenamed("DOB", "dob")
      .withColumnRenamed("Is_Active", "flag")
  }
  def transformDataType(dataFrame: DataFrame): DataFrame = {
    dataFrame.select(
      col("Customer_Name"),
      dataFrame.col("Customer_Id").cast("string"),
      to_date(col("Open_Date").cast("string"), "yyyymmdd").as("Open_Date"),
      to_date(col("Last_Consulted_Date").cast("string"), "yyyymmdd").as("Last_Consulted_Date"),
      col("Vaccination_Id"),
      col("Dr_Name"),
      col("State"),
      col("Country"),
      to_date(col("DOB"), "ddmmyyyy").as("DOB"),
      col("Is_Active")
    )
  }

}
