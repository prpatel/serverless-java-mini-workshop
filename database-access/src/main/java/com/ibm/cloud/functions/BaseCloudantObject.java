package com.ibm.cloud.functions;

import lombok.Data;

@Data
public class BaseCloudantObject {
    private String _id;
    private String _rev;
    private String type = this.getClass().getName();
}
