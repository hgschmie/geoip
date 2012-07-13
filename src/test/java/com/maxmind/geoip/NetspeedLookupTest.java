package com.maxmind.geoip;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

@Test(groups = "require-purchase")
public class NetspeedLookupTest
{

    public static final String GEOIP_FILE = "src/main/resources/GeoIPNetspeed.dat";

    public void testNetspeed() throws Exception
    {
        LookupService cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_MEMORY_CACHE);

        int speed1 = cl.getID("151.38.39.114");
        int speed2 = cl.getID("12.25.205.51");
        int speed3 = cl.getID("64.81.104.131");
        int speed4 = cl.getID("200.21.225.82");

        assertEquals(speed1, LookupService.GEOIP_CORPORATE_SPEED);
        assertEquals(speed2, LookupService.GEOIP_CABLEDSL_SPEED);
        assertEquals(speed3, LookupService.GEOIP_UNKNOWN_SPEED);
        assertEquals(speed4, LookupService.GEOIP_DIALUP_SPEED);

        cl.close();
    }
}
