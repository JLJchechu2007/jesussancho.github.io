package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy{
	private int TimeSlot;

    public MostCrowdedStrategy(int timeSlot) {
        this.TimeSlot = timeSlot;
    }

    @Override
    public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {
        if (roads.isEmpty()) {
            return -1;
        }
        if (currGreen == -1) {
            return findLongestQueue(qs, 0);
        }
        if (currTime - lastSwitchingTime < TimeSlot) {
            return currGreen;
        }
        return findLongestQueue(qs, (currGreen + 1) % roads.size());
    }

    private int findLongestQueue(List<List<Vehicle>> qs, int start) {
        int maxSize = -1;
        int index = start;
        for (int i = 0; i < qs.size(); i++) {
            int currentIndex = (start + i) % qs.size();
            if (qs.get(currentIndex).size() > maxSize) {
                maxSize = qs.get(currentIndex).size();
                index = currentIndex;
            }
        }
        return index;
    }
}
