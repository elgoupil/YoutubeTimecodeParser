/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubeTimecodeParser;

/**
 *
 * @author virus
 */
public class youtubeTimecodeParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String timeCode = "";
        String[] hours = timeCode.split("h");
        String[] minutes;
        String[] seconds;
        String hour = null;
        String minute = null;
        String second = null;
        long time = 0;
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
                }else{
                    hour = hours[0];
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
        System.out.println(time);
    }

}
