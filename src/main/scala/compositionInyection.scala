
case class User(name: String, mail: String)

trait Model {
  def getUser(id: Int): User
}

object ModelReal extends Model {
  override def getUser(id: Int): User = User("real", "real@")
}

object ModelMock extends Model {
  override def getUser(id: Int): User = User("mock", "mock@")
}

trait Inyected {
  val userModel: Model

  def printUser(id: Int) = println(userModel.getUser(id))
}

object CompositionInyectionExample extends App {

  val realObject = new Inyected {
    override val userModel: Model = ModelReal
  }

  realObject.printUser(1)

  val testObject = new Inyected {
    override val userModel: Model = ModelMock
  }

  testObject.printUser(1)

}