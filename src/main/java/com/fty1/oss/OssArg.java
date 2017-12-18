package com.fty1.oss;

public class OssArg {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private BucketEnum type = BucketEnum.none;       //未指定 bucketName,指定appoint
    private String bucketName;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public BucketEnum getType() {
        return type;
    }

    public void setType(BucketEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OssArg{" +
                "endpoint='" + endpoint + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", type=" + type +
                ", bucketName='" + bucketName + '\'' +
                '}';
    }
}
