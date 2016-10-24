package com.simpletour.test;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-10 下午7:50
 * @E-mail: wangcan@simpletour.com
 */
@Component("STAuditorAware")
public class STAuditorAware implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        return 2L;
    }
}
