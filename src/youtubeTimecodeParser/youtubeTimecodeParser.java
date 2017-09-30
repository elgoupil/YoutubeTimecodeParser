/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubeTimecodeParser;

import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author virus
 */
public class youtubeTimecodeParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URIBuilder builder = new URIBuilder("https://youtu.be/pm36k08jQ0M?t=1h57m13s");
            List<NameValuePair> list = builder.getQueryParams();
            if (list != null) {
                for (NameValuePair nameValuePair : list) {
                    if (nameValuePair.getName().equals("t")) {
                        String timeCode = nameValuePair.getValue();
                        System.out.println(timeCode);
                    }
                }
            }
        } catch (URISyntaxException ex) {
            System.out.println("Url error");
        }
    }

    public Long splitTimeCode(String timeCode) {
        long time = 0;
        if (timeCode != null) {
            if (!timeCode.isEmpty()) {
                String[] hours = timeCode.split("h");
                String[] minutes;
                String[] seconds;
                String hour = null;
                String minute = null;
                String second = null;
                switch (hours.length) {
                    case 2: //1,2m3s
                        hour = hours[0];
                        minutes = hours[1].split("m");
                        switch (minutes.length) {
                            case 2: //2,3s
                                minute = minutes[0];
                                seconds = minutes[1].split("s");
                                switch (seconds.length) {
                                    case 1: //3
                                        second = seconds[0];
                                        break;
                                }
                                break;
                            case 1: //3s
                                if (minutes[0].contains("s")) {
                                    seconds = minutes[0].split("s");
                                    switch (seconds.length) {
                                        case 1: //3
                                            second = seconds[0];
                                            break;
                                    }
                                } else {
                                    minute = minutes[0];
                                }
                        }
                        break;
                    case 1: //2m3s
                        if (hours[0].contains("m") || hours[0].contains("s")) {
                            minutes = hours[0].split("m");
                            switch (minutes.length) {
                                case 2: //2,3s
                                    minute = minutes[0];
                                    seconds = minutes[1].split("s");
                                    switch (seconds.length) {
                                        case 1: //3
                                            second = seconds[0];
                                            break;
                                    }
                                    break;
                                case 1: //3s
                                    if (minutes[0].contains("s")) {
                                        seconds = minutes[0].split("s");
                                        switch (seconds.length) {
                                            case 1: //3
                                                second = seconds[0];
                                                break;
                                        }
                                    } else {
                                        minute = minutes[0];
                                    }
                                    break;
                            }
                        } else {
                            if (hours[0].contains("h")) {
                                hour = hours[0];
                            } else {
                                second = hours[0];
                            }
                        }
                        break;
                }
                if (hour != null) {
                    time += Long.parseLong(hour) * 3600;
                }
                if (minute != null) {
                    time += Long.parseLong(minute) * 60;
                }
                if (second != null) {
                    time += Long.parseLong(second);
                }
                time = time * 1000;
            }
        }
        return time;
    }

}
