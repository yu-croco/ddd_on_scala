package infrastructure.helper.ops

trait UuidGeneratorOps {
  def genUUID = java.util.UUID.randomUUID.toString
}
