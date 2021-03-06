package nescala.boston2015

import java.util.{ Calendar, Date, TimeZone }

object Schedule {

  sealed trait Slot {
    def time: Date
  }

  case class Open(time: Date) extends Slot

  case class Intro(time: Date) extends Slot

  case class Lunch(time: Date) extends Slot

  case class Break(time: Date, length: Int) extends Slot

  case class Talk(p: Proposal) extends Slot {
    def time = p.time.get
  }

  case class Close(time: Date) extends Slot

  case class Party(time: Date) extends Slot

  def slots = (misc ++ Proposal.talks.map(Talk(_))).sortBy(_.time.getTime)

  val misc = {
    val cal = {
      val c = Calendar.getInstance()
      c.setTimeZone(TimeZone.getTimeZone(
        "US/Eastern"))
      c
    }

    def time(hour: Int, min: Int = 0) = {
      cal.set(2015, 0, 30, hour, min, 0)
      cal.getTime
    }

    Seq(
      Open(time(8)),
      Intro(time(8, 50)),
      Break(time(9, 45), 5),
      Break(time(10, 20), 20),
      Break(time(11, 25), 5),
      Break(time(12), 5),
      Lunch(time(12, 20)),
      Break(time(12 + 2, 20), 5),
      Break(time(12 + 3, 10), 5),
      Break(time(12 + 3, 30), 20),
      Break(time(12 + 4, 20), 5),
      Break(time(12 + 4, 55), 5),
      Close(time(12 + 5, 30)),
      Party(time(12 + 6))
    )
  }
}
