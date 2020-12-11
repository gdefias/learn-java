package JavaRelease.Java8;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * 日期时间API（JSR310）
 * 新的java.time包包含了所有关于日期、时间、日期时间、时区、Instant（跟日期类似但精确到纳秒）、duration（持续时间）和时钟操作的类
 * 如果需要修改时间对象，会返回一个新的实例
 *
 */


public class Test10DateTime {

    public static void main(String[] args) {
        // Get the system clock as UTC offset
        // Clock使用时区来访问当前的instant, date和time。Clock类可以替换System.currentTimeMillis()和TimeZone.getDefault()
        final Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );
        System.out.println();

        // Get the local date and local time
        // LocalDate只保存有ISO-8601日期系统的日期部分，有时区信息，相应地，LocalTime只保存ISO-8601日期系统的时间部分，没有时区信息
        // LocalDate和LocalTime都可以从Clock对象创建
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println( date );
        System.out.println( dateFromClock );

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);
        System.out.println();

        // Get the local date and local time
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now( clock );
        System.out.println( time );
        System.out.println( timeFromClock );

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
        System.out.println();

        // Get the local date/time
        // LocalDateTime类合并了LocalDate和LocalTime，它保存有ISO-8601日期系统的日期和时间，但是没有时区信息
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );
        System.out.println( datetime );
        System.out.println( datetimeFromClock );

        LocalDate date1 = datetime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = datetime.getMonth();
        int day = datetime.getDayOfMonth();
        int seconds = datetime.getSecond();
        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = datetime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
        System.out.println();


        // Get the zoned date/time
        // 如果需要一个类持有日期时间和时区信息，可以使用ZonedDateTime，它保存有ISO-8601日期系统的日期和时间，而且有时区信息
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );
        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );

        ZonedDateTime date11 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date11);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
        System.out.println();


        // Get duration between two dates
        // Duration持有的时间精确到纳秒，很容易计算两个日期中间的差异
        final LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        final LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );

        final Duration duration = Duration.between( from, to );
        System.out.println( "Duration in days: " + duration.toDays() );
        System.out.println( "Duration in hours: " + duration.toHours() );
        System.out.println();

    }
}
