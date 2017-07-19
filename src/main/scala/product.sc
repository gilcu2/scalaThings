sealed trait PriceType
object PriceType {
  case object Red extends PriceType
  case object White extends PriceType
  case object Purchase extends PriceType
}

case class Amount(value:Double)
case class Currency(isoCode:String)
case class Price(currency: Currency,amount: Amount,priceType: PriceType)
case class Variant(name:String,id:String,price:Price)
case class Product(name:String,variant: Variant)

val product=Product("Hugo Boss",
  Variant("30ml",1,Price(Currency("EUR"),Amount(2.3),PriceType.White)))

