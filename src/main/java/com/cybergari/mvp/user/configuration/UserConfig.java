package com.cybergari.mvp.user.configuration;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class UserConfig {

    private boolean fileExtension;
    private boolean fileSize;
    private boolean tags;
    private boolean numVisualizations;
    private boolean autExclusion;
    private boolean autCompression;
    private Periodicity periodicityScale;
    private Integer periodicityTime;
    private String maxLimitScale;
    private Integer maxLimitValue;
    private boolean lastSeen;
    private boolean otherData;
}
