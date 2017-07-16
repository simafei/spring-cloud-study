package cn.simafei;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by fengpj at 2017/7/16
 *
 * @version 1.0
 */
@Component
public class TenantRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("Tenant = " + TenantHolder.holder.get());
    }
}
