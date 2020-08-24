package dto
// AUTO-GENERATED Slick data model for table Monsters
trait MonstersTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Monsters
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(text)
   *  @param life Database column life SqlType(bigserial), AutoInc
   *  @param defensePower Database column defense_power SqlType(bigserial), AutoInc
   *  @param offensePower Database column offense_power SqlType(bigserial), AutoInc
   *  @param material Database column material SqlType(text) */
  case class MonstersRow(id: Long, name: String, life: Long, defensePower: Long, offensePower: Long, material: String)
  /** GetResult implicit for fetching MonstersRow objects using plain SQL queries */
  implicit def GetResultMonstersRow(implicit e0: GR[Long], e1: GR[String]): GR[MonstersRow] = GR{
    prs => import prs._
    MonstersRow.tupled((<<[Long], <<[String], <<[Long], <<[Long], <<[Long], <<[String]))
  }
  /** Table description of table monsters. Objects of this class serve as prototypes for rows in queries. */
  class Monsters(_tableTag: Tag) extends profile.api.Table[MonstersRow](_tableTag, "monsters") {
    def * = (id, name, life, defensePower, offensePower, material) <> (MonstersRow.tupled, MonstersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(life), Rep.Some(defensePower), Rep.Some(offensePower), Rep.Some(material))).shaped.<>({r=>import r._; _1.map(_=> MonstersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(text) */
    val name: Rep[String] = column[String]("name")
    /** Database column life SqlType(bigserial), AutoInc */
    val life: Rep[Long] = column[Long]("life", O.AutoInc)
    /** Database column defense_power SqlType(bigserial), AutoInc */
    val defensePower: Rep[Long] = column[Long]("defense_power", O.AutoInc)
    /** Database column offense_power SqlType(bigserial), AutoInc */
    val offensePower: Rep[Long] = column[Long]("offense_power", O.AutoInc)
    /** Database column material SqlType(text) */
    val material: Rep[String] = column[String]("material")
  }
  /** Collection-like TableQuery object for table Monsters */
  lazy val Monsters = new TableQuery(tag => new Monsters(tag))
}
