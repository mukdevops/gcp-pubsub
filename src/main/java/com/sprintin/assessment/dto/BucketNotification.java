package com.sprintin.assessment.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class BucketNotification implements Serializable {

  @Serial private static final long serialVersionUID = 1605289961730674444L;

  private String kind;

  private String id;

  private String selfLink;

  private String name;

  private String bucket;

  private String generation;

  private String metageneration;

  private String contentType;

  private String timeCreated;

  private String updated;

  private String storageClass;

  private String timeStorageClassUpdated;

  private String size;

  private String md5Hash;

  private String mediaLink;

  private String crc32c;

  private String etag;
}
