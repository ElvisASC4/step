// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;



public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {

    //If no attendees are able to attend
    if(request.getAttendees().isEmpty() && events.isEmpty()){
        return Arrays.asList(TimeRange.WHOLE_DAY);
    }

    //If greater than lenght of day
    if(request.getDuration() > TimeRange.WHOLE_DAY.duration()){
        return Arrays.asList();
    }

    //return freetimes based on attendees
    if(events.isEmpty() == false && request.getAttendees().isEmpty() == false){ //correct conditions?
        ArrayList<TimeRange> bookedTimes = new ArrayList();
        ArrayList<TimeRange> unbookedTimes = new ArrayList();
        int earliestFreeTime = TimeRange.START_OF_DAY;
        int latestEndTime = TimeRange.END_OF_DAY;
        TimeRange wholeday = TimeRange.WHOLE_DAY;

        for(Event event: events){
            int startEvent = event.getWhen().start();
            int endEvent = event.getWhen().end();
            //REDUNDANT??
            bookedTimes.add(TimeRange.fromStartEnd(startEvent, endEvent, true));
        }

        //How to sort bookedTimes by start time? --- Are we assuming they are allready ordered, seems like it
        for(TimeRange bookedTime: bookedTimes){
            System.out.println(bookedTime + " /////////////////////////////////////////");
            if(earliestFreeTime < latestEndTime){
                if(earliestFreeTime == bookedTime.start()){ // In case earlisest tiime is the start of day
                    earliestFreeTime = bookedTime.end();
                }else{ 
                    unbookedTimes.add(TimeRange.fromStartEnd(earliestFreeTime, bookedTime.start(), false));
                    earliestFreeTime = bookedTime.end();
                }
            
                //use .Equals()?
                if(bookedTime == bookedTimes.get(bookedTimes.size() - 1)){
                    System.out.println("ENTERED IF STATEMENT !!!!!!!!!!!!!!!");
                    unbookedTimes.add(TimeRange.fromStartEnd(earliestFreeTime, latestEndTime, true));
                }
            }
        }

        for(TimeRange openSpace: unbookedTimes){
            System.out.println(openSpace + "????????????????????????");
        }
        return unbookedTimes;
    }

    //split day into before and after meeting time for 1 event
    if(events.isEmpty() == false){
        for(Event event: events){
            int startEvent = event.getWhen().start();
            int endEvent = event.getWhen().end();

            return Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, startEvent, false),
                TimeRange.fromStartEnd(endEvent, TimeRange.END_OF_DAY, true));
        }
    }

    return null;


    //request.getAttendees();
    //request.getDuration();

   // events.getAttendees();
    //events.getWhen(); // TimeRange obj.start(), obj.duration(), obj.end(), obj.overlaps()

  }
}
