package cn.simafei;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Created by fengpj at 2017/7/16
 *
 * @version 1.0
 */
//@Component
public class TenantHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    private static final Log log = LogFactory
            .getLog(TenantHystrixConcurrencyStrategy.class);

    private HystrixConcurrencyStrategy delegate;

    public TenantHystrixConcurrencyStrategy() {
        try {
            this.delegate = HystrixPlugins.getInstance().getConcurrencyStrategy();
            if (this.delegate instanceof TenantHystrixConcurrencyStrategy) {
                // Welcome to singleton hell...
                return;
            }
            HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins
                    .getInstance().getCommandExecutionHook();
            HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance()
                    .getEventNotifier();
            HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance()
                    .getMetricsPublisher();
            HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance()
                    .getPropertiesStrategy();
            logCurrentStateOfHysrixPlugins(eventNotifier, metricsPublisher,
                    propertiesStrategy);
            HystrixPlugins.reset();
            HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
            HystrixPlugins.getInstance()
                    .registerCommandExecutionHook(commandExecutionHook);
            HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
            HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
            HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        }
        catch (Exception e) {
            log.error("Failed to register Sleuth Hystrix Concurrency Strategy", e);
        }
    }

    private void logCurrentStateOfHysrixPlugins(HystrixEventNotifier eventNotifier,
                                                HystrixMetricsPublisher metricsPublisher,
                                                HystrixPropertiesStrategy propertiesStrategy) {
        if (log.isDebugEnabled()) {
            log.debug("Current Hystrix plugins configuration is [" + "concurrencyStrategy ["
                    + this.delegate + "]," + "eventNotifier [" + eventNotifier + "],"
                    + "metricPublisher [" + metricsPublisher + "]," + "propertiesStrategy ["
                    + propertiesStrategy + "]," + "]");
            log.debug("Registering Abc Hystrix Concurrency Strategy.");
        }
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        if (callable instanceof HystrixTraceCallable) {
            return callable;
        }
        Callable<T> wrappedCallable = this.delegate != null
                ? this.delegate.wrapCallable(callable) : callable;
        if (wrappedCallable instanceof HystrixTraceCallable) {
            return wrappedCallable;
        }
        return new HystrixTraceCallable<>(TenantHolder.holder.get(), wrappedCallable);
    }

    // Visible for testing
    static class HystrixTraceCallable<S> implements Callable<S> {

        private String value;
        private Callable<S> callable;

        HystrixTraceCallable(String value, Callable<S> callable) {
            this.value = value;
            this.callable = callable;
        }

        @Override
        public S call() throws Exception {
            TenantHolder.holder.set(value);
            return this.callable.call();
        }

    }
}
