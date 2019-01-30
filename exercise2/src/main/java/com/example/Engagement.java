package com.example;

import java.util.Date;

import com.ibm.cloud.functions.BaseCloudantObject;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class Engagement extends BaseCloudantObject {
    private @NonNull String name;
    private @NonNull String location;
    private @NonNull Date date;
    private String[] tags;
}
