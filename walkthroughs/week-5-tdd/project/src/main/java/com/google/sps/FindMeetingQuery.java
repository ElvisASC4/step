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
        Event previousEvent = events.iterator().next();
        for(Event event: events){
            int startEvent = event.getWhen().start();
            int endEvent = event.getWhen().end();
            if(event.getWhen().overlaps(previousEvent.getWhen())){
                TimeRange combined = combine(event, previousEvent);
                bookedTimes.add(TimeRange.fromStartEnd(combined.start(), combined.end(), false));
            }else{
                bookedTimes.add(TimeRange.fromStartEnd(startEvent, endEvent, false));
            }
            previousEvent = event;
        }

        for(TimeRange bookedTime: bookedTimes){
            if(earliestFreeTime < latestEndTime){
                if(earliestFreeTime == bookedTime.start()){ // In case earliest time is the start of day
                    earliestFreeTime = bookedTime.end();
                }else{
                    if(earliestFreeTime < bookedTime.start()){ // In case double booked
                        unbookedTimes.add(TimeRange.fromStartEnd(earliestFreeTime, bookedTime.start(), false));
                        earliestFreeTime = bookedTime.end();
                    }
                }
            
                if(bookedTime == bookedTimes.get(bookedTimes.size() - 1) && earliestFreeTime != latestEndTime + 1){
                    unbookedTimes.add(TimeRange.fromStartEnd(earliestFreeTime, latestEndTime, true));
                }
            }
        }

        //In case freeTimes not long enough for requested events
        if(unbookedTimes.size() == 1 && unbookedTimes.get(0).duration() < request.getDuration()){ 
            unbookedTimes.remove(0);
            return unbookedTimes;
        }else{
            return unbookedTimes;
        }
    } 

    if(events.isEmpty() == true && request.getAttendees().isEmpty() == false){
        return Arrays.asList(TimeRange.WHOLE_DAY);
    }
    return null;
  }

  public TimeRange combine(Event event, Event event2){
      int duration = (event.getWhen().start() - event2.getWhen().start()) + event.getWhen().duration();
      TimeRange combined = new TimeRange(event2.getWhen().start(), duration);
      return combined;
  }
}
