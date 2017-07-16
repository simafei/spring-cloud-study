package cn.simafei;

/**
 * Created by fengpj at 2017/7/16
 *
 * @version 1.0
 */
class TenantHolder {

    static ThreadLocal<String> holder = new ThreadLocal<>();
}
