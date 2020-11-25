package com.wsl.shoppingKill.common.config;

import com.esotericsoftware.minlog.Log;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.net.InetAddress;


/**
 * @author wsl
 */
@Configuration
public class CuratorFrameworkConfig {

    @Resource
    private Environment env;

    @Bean
    public CuratorFramework curatorFramework(){
        // ExponentialBackoffRetry是种重连策略，每次重连的间隔会越来越长,1000毫秒是初始化的间隔时间,3代表尝试重连次数。
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        // 创建client
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(env.getProperty("zookeeper-hosts"), retry);
        // 添加watched 监听器
        curatorFramework.getCuratorListenable().addListener(new MyCuratorListener() {});

        try {

            curatorFramework.start();
            InetAddress localHost = InetAddress.getLocalHost();
            String ip = localHost.getHostAddress();

            curatorFramework.create().creatingParentsIfNeeded()
                    //指定节点类型
                    .withMode(CreateMode.EPHEMERAL)
                    //指定设置节点权限信息
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    //指定节点名称
                    .forPath("/monitor/"+ip);
        }catch (Exception e){
            curatorFramework.close();
            Log.warn("zk启动异常{}尝试重新启动",e.getLocalizedMessage());
            curatorFramework.start();
            Log.info("zk正常启动");
        }

        return curatorFramework;
    }


    public static class MyCuratorListener implements CuratorListener {
        @Override
        public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
            CuratorEventType type = event.getType();
            if(type == CuratorEventType.WATCHED){
                WatchedEvent watchedEvent = event.getWatchedEvent();
                String path = watchedEvent.getPath();
                // 重新设置改节点监听
                if(null != path){
                    client.checkExists().watched().forPath(path);
                }
            }
        }
    }

}
