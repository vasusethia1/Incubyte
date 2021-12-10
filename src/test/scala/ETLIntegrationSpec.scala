import org.apache.spark.sql.SparkSession
import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSpec}
class ETLIntegrationSpec extends FunSpec{
  describe("Should load data from postgres") {
    val spark=SparkSession.builder()
      .appName("Data sources and formats")
      .config("spark.master","local")
      .getOrCreate()
    it("should have size 5"){
      val dfFromDb = spark.read
        .format("jdbc")
        .option("driver","org.postgresql.Driver")
        .option("url","jdbc:postgresql://localhost:5432/customers")
        .option("user","hosadmin")
        .option("password","")
        .option("dbtable","customers_info")
        .load()
      dfFromDb.show()
      dfFromDb.count() shouldBe 5
    }
  }
}
