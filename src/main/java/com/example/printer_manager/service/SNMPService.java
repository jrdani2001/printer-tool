package com.example.printer_manager.service;

import org.snmp4j.*;
import org.snmp4j.smi.*;
import org.snmp4j.mp.*;
import org.snmp4j.transport.*;
import org.snmp4j.event.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SNMPService {
    private Snmp snmp;

    public SNMPService() throws IOException {
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    public String getAsString(String ipAddress, String oid) throws IOException {
        Address targetAddress = GenericAddress.parse("udp:" + ipAddress + "/161");
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);

        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);

        ResponseEvent response = snmp.send(pdu, target);
        if (response != null && response.getResponse() != null) {
            return response.getResponse().get(0).getVariable().toString();
        }
        return null;
    }
}
