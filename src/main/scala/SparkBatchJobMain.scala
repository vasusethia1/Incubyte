import FileRead.readFile
import org.apache.spark.sql.SparkSession
import TransformData.{columnsRenamed, transformDataType}
import WriteToPostGres.writeToPostGres
case object SparkBatchJobMain extends App {

      implicit val spark=SparkSession.builder()
        .appName("Hospital")
        .config("spark.master","local")
        .getOrCreate()
      val filePath = s"/home/zero-gravity/Incubyte/src/main/resources"
      val fileName = s"customer_info.csv"
      for {
        hospitalDf <- readFile(fileName, filePath)
        _ = hospitalDf.show()
        transformedDf =  transformDataType(hospitalDf.drop())
        _ = transformedDf.show(100, false)
        columnRenamedDf =  columnsRenamed(transformedDf)
        _ = println(transformedDf.schema)
            _ = columnRenamedDf.show(100, false)
      } yield writeToPostGres(columnRenamedDf)

}
