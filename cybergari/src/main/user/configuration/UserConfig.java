package main.user.configuration;

import javax.persistence.Embeddable;

@Embeddable
public class UserConfig {

    private boolean fileExtension;
    private boolean fileSize;
    private boolean tags;
    private boolean numVisualizations;
    private boolean autExclusion;
    private boolean autCompression;
    private Periodicity periodicityScale;
    private Integer periodicityTime;
    private String maxLimit;
}
