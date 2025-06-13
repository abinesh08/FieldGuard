package com.abinesh.fieldguard.workers.service;

import com.abinesh.fieldguard.event.GpsAttendanceRequestEvent;
import com.abinesh.fieldguard.event.GpsAttendanceResponseEvent;
import com.abinesh.fieldguard.workers.model.Worker;
import com.abinesh.fieldguard.workers.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GpsAttendanceRequestListener {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @EventListener
    public void onGpsAttendanceRequest(GpsAttendanceRequestEvent event){
        List<Worker> allWorkers= workerRepository.findAll();
        List<Long> matchedWorkers= new ArrayList<>();
        for(Worker worker : allWorkers){
            if(worker.getLongitude() !=null && worker.getLatitude()!=null ){
                double distance= calculateDistance(event.getLatitude(), event.getLongitude(),worker.getLatitude(), worker.getLongitude());
                if(distance<=event.getRadiusInKm()){
                    matchedWorkers.add(worker.getId());
                }
            }
        }
        GpsAttendanceResponseEvent response= new GpsAttendanceResponseEvent(matchedWorkers, event.getOfficerId(),
                event.getLatitude(), event.getLongitude(), event.getStatus());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        final int R = 6371; // Earth radius in KM
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
