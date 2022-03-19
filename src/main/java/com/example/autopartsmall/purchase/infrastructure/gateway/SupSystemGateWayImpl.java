package com.example.autopartsmall.purchase.infrastructure.gateway;

import com.example.autopartsmall.purchase.application.dto.InboundTOLineDTO;
import com.example.autopartsmall.purchase.application.dto.SubmitInboundTORequest;
import com.example.autopartsmall.purchase.domain.PurchaseOrder;
import com.example.autopartsmall.purchase.domain.SupSystemGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class SupSystemGateWayImpl implements SupSystemGateway {
    private Environment environment;

    @Override
    public void placeOrder(PurchaseOrder order) {
        // 模拟下单成功
        log.info("下单成功");

        // 模拟5s后收到交货单， 此处做了很多简化，如MQ消息监听， 防腐层设计等
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String uri = "http://localhost:" + environment.getProperty("local.server.port") + "/inbound-tos";
                SubmitInboundTORequest request = new SubmitInboundTORequest(order.getId().getValue(), order.getOrderLines().stream()
                        .map(l -> new InboundTOLineDTO(l.getId().getValue(), l.getMaterialId().getValue(), l.getQuantity()))
                        .collect(Collectors.toList()));
                HttpEntity<SubmitInboundTORequest> entity = new HttpEntity<SubmitInboundTORequest>(
                        request);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.postForLocation(uri, entity);
            }
        }, 5000L);
    }
}
