package online.ms20151114;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinConferenceRooms {
	public int minConfRooms(List<Meeting> list) {
		if (list == null || list.size() == 0) {
			return 0;
		}
		List<MeetingMoment> momentList = new ArrayList<MeetingMoment>();
		for (Meeting m : list) {
			momentList.add(new MeetingMoment(m.start, -1));
			momentList.add(new MeetingMoment(m.end, 1));
		}
		Collections.sort(momentList);
		int maxRooms =  0;
		int roomCount = 0;
		for (MeetingMoment mo : momentList) {
			if (mo.type == -1) {
				roomCount ++;
				maxRooms = Math.max(roomCount, maxRooms);
			} else {
				roomCount--;
			}
		}
		return maxRooms;
	}
}

class Meeting {
	public int start;
	public int end;
}

class MeetingMoment implements Comparable<Object> {
	public int time;
	public int type; //-1 for start, 1 for end
	
	public MeetingMoment(int time, int type) {
		this.time = time;
		this.type = type;
	}
	
	@Override
	public int compareTo(Object o) {
		MeetingMoment m = (MeetingMoment) o;
		if (this.time != m.time) {
			return this.time - m.time;
		} else {
			return this.type - m.type;
		}
	}
}