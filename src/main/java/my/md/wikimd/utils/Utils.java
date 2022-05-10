package my.md.wikimd.utils;

import java.time.Duration;

public class Utils {

    public static String calculateDurationToString(Duration duration) {
        if(duration.toDays() > 0) {
            return duration.toDays() + (duration.toDays() == 1 ? " day ago" : "days ago");
        } else if(duration.toHours() > 0) {
            return duration.toHours() + (duration.toHours() == 1 ? " hour ago" : "hours ago");
        } else if(duration.toMinutes() > 0) {
            return duration.toMinutes() + (duration.toMinutes() == 1 ? "minute ago" : " minutes ago");
        }
        return duration.toSeconds() + (duration.toSeconds() == 1 ? " second ago" : "seconds ago");
    }

}
