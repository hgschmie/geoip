package com.maxmind.geoip;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

@Test(groups = "test")
public class CountryLookupTest {

    public static final String GEOIP_FILE = "src/main/resources/GeoIP.dat";

    public void testCountry() throws Exception
    {
        LookupService cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_MEMORY_CACHE );

        Country c1 = cl.getCountry("151.38.39.114");
        Country c2 = cl.getCountry("12.25.205.51");
        Country c3 = cl.getCountry("64.81.104.131");
        Country c4 = cl.getCountry("200.21.225.82");

        assertEquals(c1.getCode(), "IT");
        assertEquals(c1.getName(), "Italy");

        assertEquals(c2.getCode(), "US");
        assertEquals(c2.getName(), "United States");

        assertEquals(c3.getCode(), "US");
        assertEquals(c3.getName(), "United States");

        assertEquals(c4.getCode(), "CO");
        assertEquals(c4.getName(), "Colombia");

        cl.close();
    }
}
