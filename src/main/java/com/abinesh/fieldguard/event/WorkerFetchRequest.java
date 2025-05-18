package com.abinesh.fieldguard.event;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WorkerFetchRequest {
    private OfficerAccessEvent accessInfo;
    private List<WorkerResponseEvent> workerResponses = new ArrayList<>();

    public WorkerFetchRequest(OfficerAccessEvent accessInfo) {
        this.accessInfo = accessInfo;
    }
}
